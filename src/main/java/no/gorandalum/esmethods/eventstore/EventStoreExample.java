package no.gorandalum.esmethods.eventstore;

import no.gorandalum.esmethods.events.Event;
import no.gorandalum.esmethods.state.Customer;
import no.gorandalum.esmethods.state.State;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

public class EventStoreExample {

    private final RestTemplate restTemplate;

    public EventStoreExample(List<Event> originalEvents) {
        restTemplate = new RestTemplate();
        originalEvents.forEach(this::postEvent);
    }

    private void postEvent(Event event) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("ES-EventType", event.getClass().getSimpleName());
        httpHeaders.set("ES-EventId", UUID.randomUUID().toString());
        httpHeaders.set("Content-Type", "application/json");
        httpHeaders.set("Accept", "application/json");
        HttpEntity<Event> entity = new HttpEntity<>(event, httpHeaders);
        restTemplate.postForLocation("http://localhost:2113/streams/eventstore-example", entity);
    }

    public Customer getCustomer(String ssn) {
        return restTemplate
                .getForObject("http://localhost:2113/projection/state_projection/state", State.class)
                .findCustomer(ssn);
    }
}
