package ua.skorobahatyi.lesson13_servlet;

import lombok.SneakyThrows;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class MessageClient {

    @SneakyThrows
    public static void main(String[] args) {
        try (var socket = new Socket("192.168.0.109", 8080)) {

            sendRequest(socket);
        }
    }

    private static void sendRequest(Socket socket) throws IOException {
        try (PrintWriter wtr = new PrintWriter(socket.getOutputStream());
             BufferedReader bufRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            //Prints the request string to the output stream
            //String jsonBody="{\"name\":\"OlgaSkorobagata\",\"message\":\"Hifamily\"}";
            String jsonBody="{name:OlgaSkorobagata,message:Hifamily}";

            wtr.println("POST /test/message HTTP/1.1");
            wtr.println("Host: 192.168.0.109");
            wtr.println("Content-type: application/json");
            wtr.println("Content-Length: "+jsonBody.length());
            //wtr.println("Content-Length: "+jsonBody.getBytes(StandardCharsets.UTF_8).length);
            //wtr.println("{\"name\":\"Olga Skorobagata\",\"message\":\"Hi family\"}");
            wtr.println(jsonBody);
            wtr.println();
            wtr.flush();

            //Prints each line of the response
           // bufRead.lines().forEach(System.out::println);
            System.out.println("================");

        }
    }

}
