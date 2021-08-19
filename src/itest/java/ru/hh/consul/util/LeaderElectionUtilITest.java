package ru.hh.consul.util;

import ru.hh.consul.BaseIntegrationTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class LeaderElectionUtilITest extends BaseIntegrationTest {

    @Test
    public void testGetLeaderInfoForService() throws Exception {
        LeaderElectionUtil leutil = new LeaderElectionUtil(client);
        final String serviceName = "myservice100";
        final String serviceInfo = "serviceinfo";

        leutil.releaseLockForService(serviceName);
        assertFalse(leutil.getLeaderInfoForService(serviceName).isPresent());
        assertEquals(serviceInfo, leutil.electNewLeaderForService(serviceName, serviceInfo).get());
        assertTrue(leutil.releaseLockForService(serviceName));
    }
}