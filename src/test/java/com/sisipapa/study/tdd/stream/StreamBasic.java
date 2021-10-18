package com.sisipapa.study.tdd.stream;

import com.sisipapa.study.tdd.dto.MongoConnection;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamBasic {

    @Test
    void peek(){
        int sum = IntStream.of(1,2,3,4,5)
                .peek(System.out::println)
                .sum();

        assertEquals(15, sum);
    }

    @Test
    void map(){
        List<String> list = Arrays.asList("Java", "Scala", "Groovy", "Python", "Go", "Swift", "Java");

        list.stream()
                .map(str -> str.toLowerCase(Locale.ROOT))
                .forEach(System.out::println);

    }

    @Test
    void filter(){
        List<String> list = Arrays.asList("Java", "Scala", "Groovy", "Python", "Go", "Swift", "Java");

        list.stream()
                .filter(str -> str.startsWith("S"))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    @Test
    void sorted(){
        List<String> list = Arrays.asList("Java", "Scala", "Groovy", "Python", "Go", "Swift", "Java");

        System.out.println("### BEFORE");
        list.stream().forEach(System.out::println);

        System.out.println("### AFTER");
        list.stream().sorted().forEach(System.out::println);
//        Stream<String> stream = list.stream().sorted();

    }

    @Test
    void distinct(){
        List<String> list = Arrays.asList("Java", "Scala", "Groovy", "Python", "Go", "Swift", "Java");
        Stream<String> stream = list.stream().distinct();

        assertEquals(7, list.size());
        assertEquals(6, stream.count());
//        Assertions.assertNotEquals(list.size(), stream.count());
    }

    @Test
    void customSorted(){
        JSONParser parser = new JSONParser();
        try{
            Reader reader = new FileReader("C:\\projects\\Springboot-TDD\\src\\test\\java\\com\\sisipapa\\study\\tdd\\stream\\connection.json");
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            List<String> keys = (List<String>) jsonObject.keySet().stream().sorted().collect(Collectors.toList());
            List<MongoConnection> sortList = keys.stream().map(key -> MongoConnection.builder().host(key).count(((Long)jsonObject.get(key)).intValue()).build()).collect(Collectors.toList());
            sortList.stream().forEach(System.out::println);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}



