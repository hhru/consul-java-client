package ru.hh.consul.option;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style(jdkOnly = true, jakarta = true)
public abstract class EventOptions implements ParamAdder {

    public static final EventOptions BLANK = ImmutableEventOptions.builder().build();

    public abstract Optional<String> getDatacenter();
    public abstract Optional<String> getNodeFilter();
    public abstract Optional<String> getServiceFilter();
    public abstract Optional<String> getTagFilter();

    @Override
    public Map<String, Object> toQuery() {
        Map<String, Object> result = new HashMap<>();

        Options.optionallyAdd(result, "node", getNodeFilter());
        Options.optionallyAdd(result, "service", getServiceFilter());
        Options.optionallyAdd(result, "tag", getTagFilter());
        Options.optionallyAdd(result, "dc", getDatacenter());

        return result;
    }
}
