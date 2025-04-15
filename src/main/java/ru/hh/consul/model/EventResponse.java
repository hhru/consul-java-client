package ru.hh.consul.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.math.BigInteger;
import java.util.List;
import org.immutables.value.Value;
import ru.hh.consul.model.event.Event;

@Value.Immutable
@Value.Style(jdkOnly = true, jakarta = true)
@JsonSerialize(as = ImmutableEventResponse.class)
@JsonDeserialize(as = ImmutableEventResponse.class)
public abstract class EventResponse {

    @Value.Parameter
    public abstract List<Event> getEvents();
    @Value.Parameter
    public abstract BigInteger getIndex();

}
