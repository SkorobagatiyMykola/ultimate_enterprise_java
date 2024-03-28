package ua.skorobahatyi.case2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DemoAppConfig2 {

    @Bean
    //@Scope("prototype") I tested scope prototype
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
