package ru.hh.consul.model.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.hh.consul.model.health.ServiceHealth;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@Value.Style(jdkOnly = true)
@JsonSerialize(as = ImmutableQueryResults.class)
@JsonDeserialize(as = ImmutableQueryResults.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class QueryResults {

    @JsonProperty("Service")
    public abstract String service();

    @JsonProperty("Nodes")
    public abstract List<ServiceHealth> nodes();
}
