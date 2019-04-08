package no.gorandalum.esmethods.kafka;

import no.gorandalum.esmethods.events.Event;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class EventSerde implements Serde<Event> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public Serializer<Event> serializer() {
        return new EventSerializer();
    }

    @Override
    public Deserializer<Event> deserializer() {
        return new EventDeserializer();
    }

    @Override
    public void close() {

    }
}
