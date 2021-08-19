package com.orbitz.consul.model.agent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Map;
import javax.annotation.Nullable;
import org.immutables.value.Value;

@Value.Immutable
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(as = ImmutableAgent.class)
@JsonDeserialize(as = ImmutableAgent.class)
public abstract class Agent {

    @JsonProperty("Config")
    public abstract Config getConfig();

    @Nullable
    @JsonProperty("DebugConfig")
    public abstract DebugConfig getDebugConfig();

    @JsonProperty("Member")
    public abstract Member getMember();

    @JsonProperty("Meta")
    public abstract Map<String, String> getMeta();
}