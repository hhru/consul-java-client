package ru.hh.consul.failover;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import junitparams.JUnitParamsRunner;
import junitparams.naming.TestCaseName;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.hh.consul.BaseIntegrationTest;
import ru.hh.consul.Consul;
import ru.hh.consul.Consul.Builder;
import ru.hh.consul.util.Address;

@RunWith(JUnitParamsRunner.class)
public class FailoverTest extends BaseIntegrationTest {


  @Test
  @TestCaseName("Failover Check")
  public void testFailover() throws InterruptedException {

    // Create a set of targets
    final Collection<Address> targets = new ArrayList<>();
    targets.add(new Address("1.2.3.4", consulContainer.getFirstMappedPort()));
    targets.add(new Address(consulContainer.getHost(), consulContainer.getFirstMappedPort()));

    // Create our consul instance
    Builder c = Consul.builder();
    c.withMultipleAddress(targets, 5000);
    c.withConnectTimeoutMillis(500);

    // Create the client
    Consul client = c.build();

    // Get the peers (should fail through 1.2.3.4 into localhost)
    List<String> peers = client.statusClient().getPeers();
    assertNotNull(peers);

    Thread.sleep(5000);

    // Get the peers( should fail through 1.2.3.4 into localhost since the 5000 millisecond blacklist has expired)
    peers = client.statusClient().getPeers();
    assertNotNull(peers);
  }
}
