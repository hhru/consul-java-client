package ru.hh.consul.model.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Optional;
import org.immutables.value.Value;
import ru.hh.consul.model.health.HealthCheck;
import ru.hh.consul.model.health.Node;
import ru.hh.consul.model.health.Service;

@Value.Immutable
@Value.Style(jdkOnly = true, jakarta = true)
@JsonSerialize(as = ImmutableQueryResult.class)
@JsonDeserialize(as = ImmutableQueryResult.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class QueryResult {

    @JsonProperty("Node")
    public abstract Node getNode();

    @JsonProperty("Service")
    public abstract Service getService();

    @JsonProperty("Checks")
    @JsonDeserialize(as = List.class, contentAs = HealthCheck.class)
    public abstract List<HealthCheck> getChecks();
    @JsonProperty("DNS")
    public abstract Optional<DnsQuery> getDns();

    @JsonProperty("Datacenters")
    public abstract Optional<String> datacenters();

    @JsonProperty("Failovers")
    public abstract Optional<Integer> failovers();
}
