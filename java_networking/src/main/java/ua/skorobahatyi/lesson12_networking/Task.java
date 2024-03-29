package ua.skorobahatyi.lesson12_networking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;

import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.OptionalLong;

// https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=10&api_key=DEMO_KEY
// Знайти фото з найбільшим розміром
public class Task {
    private static String URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=10&api_key=DEMO_KEY";
    private static String URL2 = "https://mars.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/00010/soas/rdr/ccam/CR0_398380645PRCLF0030000CCAM04010L1.PNG";
    private static ObjectMapper objectMapper = new ObjectMapper();

    private static final int port = 443;
    private static final String host = "api.nasa.gov";
    private static final String host2 = "mars.nasa.gov";
    @SneakyThrows
    public static void main(String[] args) {
        SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();


        try (var socket=ssf.createSocket(host,port);
             var writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             var reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))){

            writer.write("GET "+URL+" HTTP/1.1\n");
            writer.write("Host: "+host+"\n");
            writer.write("Content-Type: application/json\n");
            writer.write("User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36\n");
            writer.write("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\n");
            writer.write("Accept-Encoding: gzip, deflate, br, zstd\n");
            writer.write("\n");
            writer.flush();

            String outStr;

            //Prints each line of the response
            while((outStr = reader.readLine()) != null){
                System.out.println(outStr);
            }


            System.out.println("=============");
            //sendRequestFromManual(socket);
            //sendRequest(socket);
        }

        //resievedResponse();

    }

    private static void resievedResponse() {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = null;
            try {
                request = HttpRequest.newBuilder()
                        .uri(new URI(URL))
                        .GET()
                        .build();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }

            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());



            String res = response.body().toString();
            Photos photos = objectMapper.readValue(res, Photos.class);
            System.out.println(photos.getPhotos().size());

            for (Photo photo : photos.getPhotos()) {
                String url = photo.getImg_src();

                request = HttpRequest.newBuilder()
                        .uri(new URI(url))
                        .GET()
                        .build();
                HttpResponse responseNew = client.send(request, HttpResponse.BodyHandlers.ofString());

                //Optional<String> h=responseNew.headers().firstValue("Content-Length");
                HttpHeaders headers = responseNew.headers();
                OptionalLong h=responseNew.headers().firstValueAsLong("Content-Length");

                System.out.println(h.getAsLong());


            }


            System.out.println("===========================================================");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Opps..");
        }
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Photo {
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("img_src")
        private String img_src;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Photos {
        @JsonProperty("photos")
        private List<Photo> photos;
    }
}
