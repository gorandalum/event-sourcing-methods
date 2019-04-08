package no.gorandalum.esmethods.events;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import no.gorandalum.esmethods.state.State;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public interface Event {

    void updateState(State state);
}
