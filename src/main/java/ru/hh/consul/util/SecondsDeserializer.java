package ru.hh.consul.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

/**
 * Deserializes Consul time values with "s" suffix to {@link Long} objects.
 */
public class SecondsDeserializer extends JsonDeserializer<Long> {

    @Override
    public Long deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();

        if (StringUtils.isNotEmpty(value)) {
            value = value.replaceAll("[a-zA-Z]", "");
            return Long.valueOf(value);
        } else {
            return null;
        }
    }
}
