package ru.hh.consul.model.catalog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style(jdkOnly = true)
@JsonSerialize(as = ImmutableServiceWeights.class)
@JsonDeserialize(as = ImmutableServiceWeights.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ServiceWeights {

    @JsonProperty("Passing")
    public abstract int getPassing();

    @JsonProperty("Warning")
    public abstract int getWarning();
}
