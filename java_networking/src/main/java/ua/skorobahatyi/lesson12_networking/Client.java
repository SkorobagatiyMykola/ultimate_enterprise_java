package ua.skorobahatyi.lesson12_networking;

import lombok.SneakyThrows;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    @SneakyThrows
    public static void main(String[] args) {
        System.out.println(InetAddress.getLocalHost().getHostAddress());

        try (var socket=new Socket(InetAddress.getLocalHost().getHostAddress(),8855)){
            var writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            var message = "Hello Nick, how are you?";
            writer.write(message);
            writer.flush();
        }
    }
}

// 192.168.0.109
