package ua.skorobahatyi.case2.client;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class NasaPicturesClient2 {
    private static String URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=12&api_key=DEMO_KEY";
    @Autowired
    private RestTemplate restTemplate;

    public List<String> getAllPictures(){
        System.out.println("Calling Nasa....");
        var json=restTemplate.getForObject(URL, JsonNode.class);

        return StreamSupport.stream(json.get("photos").spliterator(),false)
                .map(p->p.get("img_src"))
                .map(JsonNode::asText)
                .collect(Collectors.toList());

    }
}
