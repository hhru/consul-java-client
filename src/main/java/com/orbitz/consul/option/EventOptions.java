package com.orbitz.consul.option;

import static com.orbitz.consul.option.Options.optionallyAdd;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.immutables.value.Value;

@Value.Immutable
public abstract class EventOptions implements ParamAdder {

    public static final EventOptions BLANK = ImmutableEventOptions.builder().build();

    public abstract Optional<String> getDatacenter();
    public abstract Optional<String> getNodeFilter();
    public abstract Optional<String> getServiceFilter();
    public abstract Optional<String> getTagFilter();

    @Override
    public Map<String, Object> toQuery() {
        Map<String, Object> result = new HashMap<>();

        optionallyAdd(result, "node", getNodeFilter());
        optionallyAdd(result, "service", getServiceFilter());
        optionallyAdd(result, "tag", getTagFilter());
        optionallyAdd(result, "dc", getDatacenter());

        return result;
    }
}
