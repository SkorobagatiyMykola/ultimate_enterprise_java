package ua.skorobahatyi.case3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("ua.skorobahatyi.case3")
public class DemoAppConfig3 {

    @Bean
    //@Scope("prototype") // I tested scope prototype
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
