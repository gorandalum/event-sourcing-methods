package no.gorandalum.esmethods.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.gorandalum.esmethods.events.Event;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class EventDeserializer implements Deserializer<Event> {

    @Override
    public void configure(Map<String, ?> map, boolean b) {
    }

    @Override
    public Event deserialize(String topic, byte[] arg1) {
        try {
            return new ObjectMapper().readValue(arg1, Event.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
    }
}
