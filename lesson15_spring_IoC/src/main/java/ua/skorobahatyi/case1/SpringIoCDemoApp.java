package ua.skorobahatyi.case1;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;
import ua.skorobahatyi.case1.client.NasaPicturesClient;

// Created Spring context used Class
public class SpringIoCDemoApp {
    private static String URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=12&api_key=DEMO_KEY";
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(NasaPicturesClient.class, RestTemplate.class);
      //  var context2 = new AnnotationConfigApplicationContext(NasaPicturesClient.class, RestTemplate.class); test for creation two Spring context
        var nasaClient = context.getBean(NasaPicturesClient.class);
        //var nasaClient2 = context2.getBean(NasaPicturesClient.class);
        //System.out.println(nasaClient.equals(nasaClient2)); // Equals two bean
        nasaClient.getAllPictures();
        var restTemplate = context.getBean(RestTemplate.class);
        var json=restTemplate.getForObject(URL, JsonNode.class);
        //System.out.println(json.asText());
        json.get("photos")
                .forEach(p-> System.out.println(p.get("img_src").asText()));



    }
}
