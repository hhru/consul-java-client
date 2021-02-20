package ru.hh.consul.failover;

import ru.hh.consul.BaseIntegrationTest;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.common.net.HostAndPort;
import ru.hh.consul.Consul;
import ru.hh.consul.Consul.Builder;

import junitparams.JUnitParamsRunner;
import junitparams.naming.TestCaseName;

@RunWith(JUnitParamsRunner.class)
public class FailoverTest extends BaseIntegrationTest {


  @Test
  @TestCaseName("Failover Check")
  public void testFailover() throws InterruptedException {

    // Create a set of targets
    final Collection<HostAndPort> targets = new ArrayList<>();
    targets.add(HostAndPort.fromParts("1.2.3.4", consulContainer.getFirstMappedPort()));
    targets.add(HostAndPort.fromParts(consulContainer.getHost(), consulContainer.getFirstMappedPort()));

    // Create our consul instance
    Builder c = Consul.builder();
    c.withMultipleHostAndPort(targets, 5000);
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
