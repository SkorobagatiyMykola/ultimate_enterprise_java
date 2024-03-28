package ua.skorobahatyi.case2;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;
import ua.skorobahatyi.case2.client.NasaPicturesClient2;

// Created Spring context used package потрібно додати анотації
public class SpringIoCDemoApp2 {
    private static String URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=12&api_key=DEMO_KEY";
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext("ua.skorobahatyi.case2");
        var nasaClient = context.getBean(NasaPicturesClient2.class);
        nasaClient.getAllPictures()
                .forEach(System.out::println);

    }
}
