package ua.skorobahatyi.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ua.skorobahatyi.dto.Message;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ClientToConnect {
    private static String TASK_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=12&api_key=DEMO_KEY";
    private static String URL = "http://localhost:8080/v16/messages";
    private static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {

         sendMessage();
        //getAllMessages();
        //solvedTask();

    }

    private static void sendMessage() {

        // Added emoji pig
        String pig1 = ":pig:";
        String pig2 = ":pig2:";
        Message message2 = new Message(null, EmojiParser.parseToUnicode(pig1), EmojiParser.parseToUnicode(pig2));

        Message message = new Message(null, "What do you think about free time?", "Olga Sk");

        HttpEntity<Message> request = new HttpEntity<>(message2);
        Message foo = restTemplate.postForObject(URL, request, Message.class);
    }

    private static void getAllMessages() {

        ResponseEntity<Message[]> response = restTemplate.getForEntity(URL, Message[].class);
        Message[] messages = response.getBody();
        Arrays.stream(messages).forEach(System.out::println);
    }

    private static void solvedTask() {
        var json = restTemplate.getForObject(TASK_URL, JsonNode.class);

        List<String> urls = StreamSupport.stream(json.get("photos").spliterator(), false)
                .map(p -> p.get("img_src"))
                .map(JsonNode::asText)
                .collect(Collectors.toList());

        for (String url : urls) {
            var httpHeaders = restTemplate.headForHeaders(url);

            httpHeaders.entrySet().forEach(el -> System.out.println(el.getKey() + ": " + el.getValue()));
        }

        System.out.println(json);
    }
}
