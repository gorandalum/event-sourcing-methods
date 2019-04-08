package no.gorandalum.esmethods.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.gorandalum.esmethods.state.State;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class StateSerializer implements Serializer<State> {

    @Override
    public void configure(Map<String, ?> map, boolean b) {
    }

    @Override
    public byte[] serialize(String topic, State arg1) {
        try {
            return new ObjectMapper().writeValueAsBytes(arg1);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
    }
}
