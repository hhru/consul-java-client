package ru.hh.consul.model.session;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Optional;
import org.immutables.value.Value;
import ru.hh.consul.util.SecondsDeserializer;
import ru.hh.consul.util.SecondsSerializer;

@Value.Immutable
@Value.Style(jdkOnly = true, jakarta = true)
@JsonSerialize(as = ImmutableSessionInfo.class)
@JsonDeserialize(as = ImmutableSessionInfo.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class SessionInfo {

    @JsonProperty("CreateIndex")
    public abstract long getCreateIndex();

    @JsonProperty("LockDelay")
    @JsonSerialize(using = SecondsSerializer.class)
    @JsonDeserialize(using = SecondsDeserializer.class)
    public abstract Long getLockDelay();

    @JsonProperty("Name")
    public abstract Optional<String> getName();

    @JsonProperty("Node")
    public abstract String getNode();

    @JsonProperty("Checks")
    public abstract List<String> getChecks();

    @JsonProperty("Behavior")
    public abstract String getBehavior();

    @JsonProperty("TTL")
    public abstract Optional<String> getTtl();

    @JsonProperty("ID")
    public abstract String getId();

}
