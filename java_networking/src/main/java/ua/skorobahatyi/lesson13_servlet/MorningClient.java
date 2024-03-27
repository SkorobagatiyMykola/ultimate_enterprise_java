package ua.skorobahatyi.lesson13_servlet;

import lombok.SneakyThrows;

import java.io.*;
import java.net.Socket;

public class MorningClient {

    @SneakyThrows
    public static void main(String[] args) {
        try (var socket = new Socket("192.168.0.109", 8080)) {

           // sendRequestFromManual(socket);
            sendRequest(socket);
        }

    }

    private static void sendRequest(Socket socket) throws IOException {
        try (var writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             var reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {


            writer.write("GET /test/morning HTTP/1.1\n");
            //writer.write("GET /test/morning?name=Mykola555 HTTP/1.1\n"); // request with parameters
            writer.write("Host: 192.168.0.109\n");
            writer.write("X-Mood: cool\n");
            writer.write("Cookie: JSESSIONID=89587B6A84661C04441B811F08D4EDAD\n");
            writer.write("\n");
            writer.flush();

            //Prints each line of the response
            reader.lines().forEach(System.out::println); // this is easy way
//            String outStr;
//        while((outStr = reader.readLine()) != null){
//            System.out.println(outStr);
//        }
            System.out.println("==================");
        }
    }

    private static void sendRequestFromManual(Socket socket) throws IOException {
        //Instantiates a new PrintWriter passing in the sockets output stream
        try (PrintWriter wtr = new PrintWriter(socket.getOutputStream());
             BufferedReader bufRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            //Prints the request string to the output stream
            //wtr.println("GET / HTTP/1.1");
            //wtr.println("GET /test/morning HTTP/1.1");
            wtr.println("GET /test/morning?name=Mykola HTTP/1.1");
            wtr.println("Host: 192.168.0.109");
            wtr.println("X-Mood: Skorobagatiy Mykola cool");
            wtr.println("Cookie: JSESSIONID=ED1468F06F414052DCD6D6A9D1C6BB59");
            wtr.println("");
            wtr.flush();

            //Prints each line of the response
            bufRead.lines().forEach(System.out::println);

        }
    }
}
