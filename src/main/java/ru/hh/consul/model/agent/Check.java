package ru.hh.consul.model.agent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Optional;

import org.immutables.value.Value;

import java.util.List;
import static ru.hh.consul.util.Checks.checkState;

@Value.Immutable
@Value.Style(jdkOnly = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(as = ImmutableCheck.class)
@JsonDeserialize(as = ImmutableCheck.class)
public abstract class Check {

    @JsonProperty("ID")
    public abstract String getId();

    @JsonProperty("Name")
    public abstract String getName();

    @JsonProperty("Notes")
    public abstract Optional<String> getNotes();

    @JsonProperty("Output")
    public abstract Optional<String> getOutput();

    @JsonProperty("Args")
    public abstract Optional<List<String>> getArgs();

    @JsonProperty("Interval")
    public abstract Optional<String> getInterval();

    @JsonProperty("TTL")
    public abstract Optional<String> getTtl();

    @JsonProperty("HTTP")
    public abstract Optional<String> getHttp();

    @JsonProperty("TCP")
    public abstract Optional<String> getTcp();

    @JsonProperty("GRPC")
    public abstract Optional<String> getGrpc();

    @JsonProperty("GRPCUseTLS")
    public abstract Optional<Boolean> getGrpcUseTls();

    @JsonProperty("ServiceID")
    public abstract Optional<String> getServiceId();

    @JsonProperty("ServiceTags")
    @JsonDeserialize(as = List.class, contentAs = String.class)
    public abstract List<String> getServiceTags();

    @JsonProperty("DeregisterCriticalServiceAfter")
    public abstract Optional<String> getDeregisterCriticalServiceAfter();

    @Value.Check
    protected void validate() {

        checkState(getHttp().isPresent() || getTtl().isPresent()
            || getArgs().isPresent() || getTcp().isPresent() || getGrpc().isPresent(),
                "Check must specify either http, tcp, ttl, grpc or args");

        if (getHttp().isPresent() || getArgs().isPresent() || getTcp().isPresent() || getGrpc().isPresent()) {
            checkState(getInterval().isPresent(),
                    "Interval must be set if check type is http, tcp, grpc or args");
        }

    }

}
