package ru.hh.consul.model.health;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Optional;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style(jdkOnly = true, jakarta = true)
@JsonSerialize(as = ImmutableHealthCheck.class)
@JsonDeserialize(as = ImmutableHealthCheck.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class HealthCheck {

    @JsonProperty("Node")
    public abstract String getNode();

    @JsonProperty("CheckID")
    public abstract String getCheckId();

    @JsonProperty("Name")
    public abstract String getName();

    @JsonProperty("Status")
    public abstract String getStatus();

    @JsonProperty("Notes")
    public abstract Optional<String> getNotes();

    @JsonProperty("Output")
    public abstract Optional<String> getOutput();

    @JsonProperty("ServiceID")
    public abstract Optional<String> getServiceId();

    @JsonProperty("ServiceName")
    public abstract Optional<String> getServiceName();

    @JsonProperty("ServiceTags")
    @JsonDeserialize(as = List.class, contentAs = String.class)
    public abstract List<String> getServiceTags();

}
