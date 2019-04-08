package no.gorandalum.esmethods.kafka;

import no.gorandalum.esmethods.events.Event;
import no.gorandalum.esmethods.state.Customer;
import no.gorandalum.esmethods.state.State;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.QueryableStoreTypes;

import java.util.List;
import java.util.Properties;

public class KafkaExample {

    private static final String TOPIC_NAME = "es_kafka_example_topic";
    private static final String STORE_NAME = "es_kafka_state_store";

    private final KafkaStreams kafkaStreams;

    public KafkaExample(List<Event> originalEvents) {
        produceOriginalEvents(originalEvents);
        kafkaStreams = setupKafkaStreams();
        start();
    }

    private void produceOriginalEvents(List<Event> originalEvents) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "kafka-event-sourcing-producer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, EventSerializer.class);

        Producer<String, Event> producer = new KafkaProducer<>(props);

        originalEvents
                .stream()
                .map(event -> new ProducerRecord<>(TOPIC_NAME, 0, "1234", event))
                .forEach(producer::send);
    }

    private KafkaStreams setupKafkaStreams() {
        Properties props = new Properties();
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-event-sourcing-example");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, EventSerde.class);

        StreamsBuilder builder = new StreamsBuilder();
        builder
                .<String, Event>stream(TOPIC_NAME)
                .groupByKey()
                .aggregate(
                        State::new,
                        (key, event, state) -> {
                            event.updateState(state);
                            return state;
                        },
                        Materialized.<String, State, KeyValueStore<Bytes, byte[]>>as(STORE_NAME)
                                .withValueSerde(new StateSerde()));

        return new KafkaStreams(builder.build(), props);
    }

    private void start() {
        new Thread(kafkaStreams::start).start();
    }

    public Customer getCustomer(String ssn) {
        return kafkaStreams
                .store(STORE_NAME, QueryableStoreTypes.<String, State>keyValueStore())
                .get(ssn)
                .findCustomer(ssn);
    }
}
