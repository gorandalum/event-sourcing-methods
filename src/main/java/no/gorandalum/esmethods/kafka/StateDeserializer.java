package no.gorandalum.esmethods.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.gorandalum.esmethods.events.Event;
import no.gorandalum.esmethods.state.State;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class StateDeserializer implements Deserializer<State> {

    @Override
    public void configure(Map<String, ?> map, boolean b) {
    }

    @Override
    public State deserialize(String topic, byte[] arg1) {
        try {
            return new ObjectMapper().readValue(arg1, State.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
    }
}
