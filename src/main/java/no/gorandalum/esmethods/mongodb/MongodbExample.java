package no.gorandalum.esmethods.mongodb;

import no.gorandalum.esmethods.events.Event;
import no.gorandalum.esmethods.state.Customer;
import no.gorandalum.esmethods.state.State;

import java.util.List;

public class MongodbExample {

    private final EventRepository eventRepository;

    public MongodbExample(EventRepository eventRepository, List<Event> originalEvents) {
        this.eventRepository = eventRepository;
        eventRepository.deleteAll();
        originalEvents.forEach(eventRepository::save);
    }

    public Customer getCustomer(String ssn) {
        State state = new State();
        eventRepository.findAll().forEach(event -> event.updateState(state));
        return state.findCustomer(ssn);
    }
}
