package ru.hh.consul;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import org.junit.After;
import org.junit.BeforeClass;
import org.testcontainers.containers.GenericContainer;
import ru.hh.consul.config.CacheConfig;
import ru.hh.consul.config.ClientConfig;
import ru.hh.consul.util.Address;

public abstract class BaseIntegrationTest {

    private final List<String> deregisterServices = new CopyOnWriteArrayList<>();

    protected static Consul client;

    public static GenericContainer<?> consulContainer;
    static {
        // use latest image after fix issue https://github.com/hashicorp/consul/issues/10945
        consulContainer = new GenericContainer<>("consul:1.9")
            .withCommand("agent", "-dev", "-client", "0.0.0.0", "--enable-script-checks=true")
            .withExposedPorts(8500);
        consulContainer.start();
    }
    public static GenericContainer<?> consulContainerAcl;
    static {
        consulContainerAcl = new GenericContainer<>("consul:1.9")
            .withCommand("agent", "-dev", "-client", "0.0.0.0", "--enable-script-checks=true")
            .withExposedPorts(8500)
            .withEnv("CONSUL_LOCAL_CONFIG",
                    "{\n" +
                    "  \"acl\": {\n" +
                    "    \"enabled\": true,\n" +
                    "    \"default_policy\": \"deny\",\n" +
                    "    \"tokens\": {\n" +
                    "      \"master\": \"aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee\"\n" +
                    "    }\n" +
                    "  }\n" +
                    "}"
            );
        consulContainerAcl.start();
    }

    protected static Address defaultClientAddress;

    @BeforeClass
    public static void beforeClass() {
        defaultClientAddress = new Address(consulContainer.getHost(), consulContainer.getFirstMappedPort());
        client = Consul.builder()
                .withAddress(defaultClientAddress)
                .withClientConfiguration(new ClientConfig(CacheConfig.builder().withWatchDuration(Duration.ofSeconds(1)).build()))
                .withReadTimeoutMillis(Duration.ofSeconds(2).toMillis())
                .withWriteTimeoutMillis(Duration.ofMillis(500).toMillis())
                .build();
    }

    @After
    public void after() {
        deregisterServices.forEach(client.agentClient()::deregister);
        deregisterServices.clear();
    }

    protected String createAutoDeregisterServiceId() {
        String serviceId = UUID.randomUUID().toString();
        deregisterServices.add(serviceId);

        return serviceId;
    }
}
