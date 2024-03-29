package ua.skorobahatyi.lesson12_networking;

import lombok.Data;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class CustomJackson {
    public static void main(String[] args) {
        var json = "{\n" +
                "  \"firstName\": \"Mykola\",\n" +
                "  \"lastName\": \"Skorobahatyi\",\n" +
                "  \"email\": \"nikolays2001@ukr.net\"\n" +
                "}";

        // System.out.println(json.replaceAll("\\s+",""));
        var user = jsonToObjMy(json, User.class);
        System.out.println(user);


        var jsonPet = "{\n" +
                "  \"name\": \"Snow\",\n" +
                "  \"owner\": \"Skorobahatyi\",\n" +
                "  \"age\": \"1\"\n" +
                "}";
        var pet = jsonToObjMy(jsonPet, Pet.class);
        System.out.println(pet);

    }

    @SneakyThrows
    private static <T> T jsonToObjMy(String json, Class<T> userClass) {
        Map<String, String> map = new HashMap<>();
        String[] elements = json.replaceAll("\\s+", "")
                .replaceAll("\\{", "")
                .replaceAll("}", "")
                .replaceAll("\"", "")
                .split(",");

        for (String element : elements) {
            String[] pairs = element.split(":");
            // System.out.println(pairs[0] + "  -  " + pairs[1]);
            map.put(pairs[0], pairs[1]);
        }

        System.out.println(map.size());
        T instance = userClass.newInstance();

        for (String key : map.keySet()) {
            try {
                Field field = instance.getClass().getDeclaredField(key);
                field.setAccessible(true);
                field.set(instance, map.get(key));
            } catch (NoSuchFieldException ex) {
                continue;
            }
        }

//        for (Field field:fields) {
//            System.out.println(field.toString());
//        }
        return instance;
    }

    @SneakyThrows
    private static <T> T jsonToObj(String json, Class<T> userClass) {
        return null;
    }

    @Data
    static class User {
        private String firstName;
        private String lastName;
        private String email;
        private String city;
    }

    @Data
    static class Pet {
        private String name;
        private String owner;
        private String age;
        private String city;
    }
}
