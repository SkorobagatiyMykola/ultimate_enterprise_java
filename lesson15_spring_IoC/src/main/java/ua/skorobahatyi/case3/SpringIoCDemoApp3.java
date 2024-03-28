package ua.skorobahatyi.case3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.skorobahatyi.case3.client.NasaPicturesClient3;

// Created Spring context used package потрібно додати анотації
public class SpringIoCDemoApp3 {
    private static String URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=12&api_key=DEMO_KEY";
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext("ua.skorobahatyi.case2");
        var nasaClient = context.getBean(NasaPicturesClient3.class);
        nasaClient.getAllPictures()
                .forEach(System.out::println);

    }
}
