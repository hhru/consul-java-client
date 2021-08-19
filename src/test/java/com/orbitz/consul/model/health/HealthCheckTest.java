package com.orbitz.consul.model.health;

import java.util.Collections;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class HealthCheckTest {

    @Test
    public void serviceTagsAreNotNullWhenNotSpecified() {
        HealthCheck check = ImmutableHealthCheck.builder()
                .name("name")
                .node("node")
                .checkId("id")
                .status("passing")
                .build();

        assertEquals(Collections.emptyList(), check.getServiceTags());
    }

    @Test
    public void serviceTagsCanBeAddedToHealthCheck() {
        HealthCheck check = ImmutableHealthCheck.builder()
                .name("name")
                .node("node")
                .checkId("id")
                .status("passing")
                .addServiceTags("myTag")
                .build();

        assertEquals(Collections.singletonList("myTag"), check.getServiceTags());
    }
}
