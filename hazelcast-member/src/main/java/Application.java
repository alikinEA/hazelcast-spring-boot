import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.config.TcpIpConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Alikin E.A. on 11.02.18.
 */
@Configuration
@EnableAutoConfiguration
public class Application {

    @Value("${nodes}")
    private String nodes;

    @Value("${pubaddress}")
    private String pubaddress;

    @Value("${mancenter}")
    private String mancenter;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Config config() {
        Config config = new Config();
        config.getManagementCenterConfig().setUrl(mancenter).setEnabled(true);
        NetworkConfig network = config.getNetworkConfig();
        JoinConfig join = network.getJoin();
        join.getMulticastConfig().setEnabled(false);
        String[] nodesArr = nodes.split(",");
        TcpIpConfig tcpIpConf = join.getTcpIpConfig();
        for (String ip : nodesArr) {
            tcpIpConf.addMember(ip);
        }
        tcpIpConf.addMember(pubaddress);
        tcpIpConf.setEnabled(true);
        network.setPublicAddress(pubaddress);
        return config;
    }
}
