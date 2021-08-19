package com.orbitz.consul.option;

import static com.orbitz.consul.option.Options.optionallyAdd;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.immutables.value.Value;

/**
 * Container for common query options used by the Consul API.
 */
@Value.Immutable
public abstract class QueryParameterOptions implements ParamAdder {

    public static final QueryParameterOptions BLANK = ImmutableQueryParameterOptions.builder().build();

    public abstract Optional<Boolean> getReplaceExistingChecks();
    public abstract Optional<Boolean> getPrune();

    @Override
    public List<String> toQueryParameters() {
        List<String> result = new LinkedList<>();

        optionallyAdd(result, "replace-existing-checks", getReplaceExistingChecks());
        optionallyAdd(result, "prune", getPrune());

        return result;
    }
}
