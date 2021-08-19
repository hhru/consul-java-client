package ru.hh.consul.model.catalog;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Optional;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableTaggedAddresses.class)
@JsonDeserialize(as = ImmutableTaggedAddresses.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class TaggedAddresses {

    @JsonProperty("wan")
    public abstract String getWan();

    @JsonProperty("lan")
    public abstract Optional<String> getLan();
}
