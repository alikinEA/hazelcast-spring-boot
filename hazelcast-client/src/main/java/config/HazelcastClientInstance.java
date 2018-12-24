package config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alikin E.A. on 17.02.18.
 */
public class HazelcastClientInstance {

    @Value("${hazelCastNodes}")
    private String hazelcastNodes;

    private static final int TIMEOUT = 1000;

    private HazelcastInstance hzClient;

    private HazelcastInstance init() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getNetworkConfig().addAddress(hazelcastNodes.split(","))
                .setConnectionTimeout(TIMEOUT);
        HazelcastInstance hzClient
                = HazelcastClient.newHazelcastClient(clientConfig);
        return hzClient;
    }

    protected HazelcastInstance getHZInstance() {
        synchronized(this) {
            if (hzClient == null) {
                hzClient = init();
            } else {
                if (!hzClient.getLifecycleService().isRunning()) {
                    hzClient.shutdown();
                    hzClient = init();
                }
            }
            return hzClient;
        }
    }
}
