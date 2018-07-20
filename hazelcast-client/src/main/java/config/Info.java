package config;

import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alikin E.A. on 20.07.18.
 */
@Component
public class Info implements InfoContributor {

    @Override
    public void contribute(org.springframework.boot.actuate.info.Info.Builder builder) {
        Map<String,String> data= new HashMap<>();
        data.put("description", "application info");
        builder.withDetail("info", data);
    }
}
