package ua.skorobahatyi.lesson12_networking;

import lombok.SneakyThrows;

import java.net.HttpURLConnection;
import java.net.URL;

public class Task2 {

    private static String URL1 = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=10&api_key=DEMO_KEY";
    @SneakyThrows
    public static void main(String[] args) {


        URL url = new URL(URL1);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestProperty("Accept-Encoding", "gzip");

        System.out.println("Length : " + con.getContentLength());
    }
}
