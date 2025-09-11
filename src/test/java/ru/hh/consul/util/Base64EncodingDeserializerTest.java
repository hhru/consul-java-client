package ru.hh.consul.util;

import java.io.IOException;
import java.util.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import ru.hh.consul.model.event.Event;
import ru.hh.consul.model.event.ImmutableEvent;

public class Base64EncodingDeserializerTest {

    @Test
    public void shouldDeserialize() throws IOException {
        String value = RandomStringUtils.secure().nextAlphabetic(12);
        Event event = ImmutableEvent.builder()
                .id("1")
                .lTime(1L)
                .name("name")
                .version(1)
                .payload(Base64.getEncoder().encodeToString(value.getBytes()))
                .build();

        String serializedEvent = Jackson.MAPPER.writeValueAsString(event);
        Event deserializedEvent = Jackson.MAPPER.readValue(serializedEvent, Event.class);

        assertEquals(value, deserializedEvent.getPayload().get());
    }
}
