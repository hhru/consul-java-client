package ru.hh.consul.option;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.immutables.value.Value;

/**
 * Container for common query options used by the Consul API.
 */
@Value.Immutable
@Value.Style(jdkOnly = true)
public abstract class RoleOptions implements ParamAdder {

    public static final RoleOptions BLANK = ImmutableRoleOptions.builder().build();

    public abstract Optional<String> getPolicy();
    public abstract Optional<String> getNamespace();


    @Override
    public Map<String, Object> toQuery() {
        Map<String, Object> result = new HashMap<>();

        Options.optionallyAdd(result, "policy", getPolicy());
        Options.optionallyAdd(result, "ns", getNamespace());

        return result;
    }
}
