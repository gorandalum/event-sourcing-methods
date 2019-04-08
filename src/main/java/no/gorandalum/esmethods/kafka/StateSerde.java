package no.gorandalum.esmethods.kafka;

import no.gorandalum.esmethods.state.State;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class StateSerde implements Serde<State> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {}

    @Override
    public Serializer<State> serializer() {
        return new StateSerializer();
    }

    @Override
    public Deserializer<State> deserializer() {
        return new StateDeserializer();
    }

    @Override
    public void close() {

    }
}
