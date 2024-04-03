package ua.skorobahatyi.lesson8_stream;

import java.util.Arrays;

public class TestStream {
    private static String[] arr = {"one", "two", "three", "four", "five", "six", "seven", "eight"};

    public static void main(String[] args) {
        Arrays.stream(arr)
                .forEach(System.out::println);
    }
}
