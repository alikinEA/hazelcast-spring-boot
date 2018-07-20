import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Alikin E.A. on 11.02.18.
 */
@ComponentScan(basePackages = {"model","resource","service","config"})
@EnableAutoConfiguration
@Configuration
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
