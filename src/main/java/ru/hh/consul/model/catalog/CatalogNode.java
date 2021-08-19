package ru.hh.consul.model.catalog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Map;
import org.immutables.value.Value;
import ru.hh.consul.model.health.Node;
import ru.hh.consul.model.health.Service;

@Value.Immutable
@JsonSerialize(as = ImmutableCatalogNode.class)
@JsonDeserialize(as = ImmutableCatalogNode.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class CatalogNode {

    @JsonProperty("Node")
    public abstract Node getNode();

    @JsonProperty("Services")
    public abstract Map<String, Service> getServices();

}
