package ua.skorobahatyi.lesson13_servlet;

import lombok.SneakyThrows;

import java.io.*;
import java.net.Socket;

public class MorningClient {

    @SneakyThrows
    public static void main(String[] args) {
        try (var socket=new Socket("192.168.0.109",8080)){

            //sendRequestFromManual(socket);
            sendRequest(socket);
        }

    }

    private static void sendRequest(Socket socket) throws IOException {
        var writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        writer.write("GET /test/morning HTTP/1.1\n");
        //writer.write("GET /test/morning?name=Petro HTTP/1.1\n");
        writer.write("Host: 192.168.0.109\n");
        writer.write("\n");
        writer.flush();

        var reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String outStr;

        //Prints each line of the response
        while((outStr = reader.readLine()) != null){
            System.out.println(outStr);
        }

        reader.close();
        writer.close();

    }

    private static void sendRequestFromManual(Socket socket) throws IOException {
        //Instantiates a new PrintWriter passing in the sockets output stream
        PrintWriter wtr = new PrintWriter(socket.getOutputStream());

        //Prints the request string to the output stream
        //wtr.println("GET / HTTP/1.1");
        //wtr.println("GET /test/morning HTTP/1.1");
        wtr.println("GET /test/morning?name=Mykola HTTP/1.1");
        wtr.println("Host: 192.168.0.109");
        wtr.println("");
        wtr.flush();

        //Creates a BufferedReader that contains the server response
        BufferedReader bufRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String outStr;

        //Prints each line of the response
        while((outStr = bufRead.readLine()) != null){
            System.out.println(outStr);
        }


        //Closes out buffer and writer
        bufRead.close();
        wtr.close();
    }
}
