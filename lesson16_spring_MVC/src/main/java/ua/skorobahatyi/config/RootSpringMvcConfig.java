package ua.skorobahatyi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("ua.skorobahatyi")
@EnableWebMvc
public class RootSpringMvcConfig implements WebMvcConfigurer {

    /*@Override
    public void configureViewResolvers(ViewResolverRegistry registry) { //case1
        registry.jsp("/WEB-INF/classes",".jsp");
    }*/

    @Bean
    public ViewResolver viewResolver(){  //case1
        var viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }
}
