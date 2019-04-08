package no.gorandalum.esmethods;

import no.gorandalum.esmethods.events.*;
import no.gorandalum.esmethods.eventstore.EventStoreExample;
import no.gorandalum.esmethods.kafka.KafkaExample;
import no.gorandalum.esmethods.mongodb.EventRepository;
import no.gorandalum.esmethods.mongodb.MongodbExample;
import no.gorandalum.esmethods.state.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@EnableMongoAuditing
@RestController
@RequestMapping("api")
public class EventSourcingMethodsApplication {

    private final MongodbExample mongodbExample;
    private final KafkaExample kafkaExample;
    private final EventStoreExample eventStoreExample;

    public static void main(String[] args) {
        SpringApplication.run(EventSourcingMethodsApplication.class, args);
    }

    private static final List<Event> ORIGINAL_EVENTS = Collections.unmodifiableList(Arrays.asList(
            new CustomerCreatedEvent("1234", "Even Sårs", "evensaars@evry.com"),
            new AccountCreatedEvent("1234", "54321"),
            new TransactionRegisteredEvent("1234", "54321", new BigDecimal("99")),
            new ChangedEmailEvent("1234", "evensaars@kantega.no"),
            new TransactionRegisteredEvent("1234", "54321", new BigDecimal("-49"))
    ));

    @Autowired
    public EventSourcingMethodsApplication(EventRepository eventRepository) {
        mongodbExample = new MongodbExample(eventRepository, ORIGINAL_EVENTS);
        kafkaExample = new KafkaExample(ORIGINAL_EVENTS);
        eventStoreExample = new EventStoreExample(ORIGINAL_EVENTS);
    }

    @GetMapping("customers/mongo/{ssn}")
    public Customer getCustomerMongo(@PathVariable String ssn) {
        return mongodbExample.getCustomer(ssn);
    }

    @GetMapping("customers/kafka/{ssn}")
    public Customer getCustomerKafka(@PathVariable String ssn) {
        return kafkaExample.getCustomer(ssn);
    }

    @GetMapping("customers/eventstore/{ssn}")
    public Customer getCustomerEventStore(@PathVariable String ssn) {
        return eventStoreExample.getCustomer(ssn);
    }
}
