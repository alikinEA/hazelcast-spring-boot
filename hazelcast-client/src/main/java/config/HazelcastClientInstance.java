package config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alikin E.A. on 17.02.18.
 */
public class HazelcastClientInstance {

    @Value("${hazelCastNodes}")
    private String hazelCastNodes;

    private static final int TIMEOUT = 1000;

    public HazelcastInstance getHCInstance() {
        ClientConfig clientConfig = new ClientConfig();
        String[] addresses ;
        if (hazelCastNodes.contains(",")) {
            addresses = hazelCastNodes.split(",");
        } else {
            addresses = new String[1];
            addresses[0] = hazelCastNodes;
        }
        clientConfig.getNetworkConfig().addAddress(addresses)
                .setConnectionTimeout(TIMEOUT);
        HazelcastInstance hzClient
                = HazelcastClient.newHazelcastClient(clientConfig);
        return hzClient;
    }
}
