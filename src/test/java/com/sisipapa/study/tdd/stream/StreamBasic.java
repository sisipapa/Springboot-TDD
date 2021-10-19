package com.sisipapa.study.tdd.stream;

import com.sisipapa.study.tdd.dto.MongoConnection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamBasic {

    @Test
    void basic(){
        OptionalInt min = IntStream.of(1, 3, 5, 7, 9).min();
        assertEquals(1,1);

        int max = IntStream.of().max().orElse(0);
        assertEquals(0,0);

        IntStream.of(1, 3, 5, 7, 9).average().ifPresent(System.out::println);
    }

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
    void collect(){
        List<Product> productList = Arrays.asList(
                new Product(23, "potatoes"),
                new Product(14, "orange"),
                new Product(13, "lemon"),
                new Product(23, "bread"),
                new Product(13, "sugar")
        );

        List<String> nameList = productList.stream()
                .map(Product::getName)
                .collect(Collectors.toList());
        assertEquals(nameList.get(0), "potatoes");

        String listToString = productList.stream()
                .map(Product::getName)
                .collect(Collectors.joining(", ", "<", ">"));
        System.out.println(listToString);

        Double averageAmount = productList.stream()
                .collect(Collectors.averagingInt(Product::getAmount));
        System.out.println(averageAmount);

        Integer summingAmount1 = productList.stream()
                .collect(Collectors.summingInt(Product::getAmount));
        System.out.println(summingAmount1);

        Integer summingAmount2 = productList.stream()
                .mapToInt(Product::getAmount).sum();
        System.out.println(summingAmount2);

        IntSummaryStatistics statistics = productList.stream()
                .collect(Collectors.summarizingInt(Product::getAmount));
        System.out.println(statistics);

        Map<Integer, List<Product>> groupByList = productList.stream()
                .collect(Collectors.groupingBy(Product::getAmount));
        System.out.println(groupByList);

        Map<Boolean, List<Product>> partitionList = productList.stream()
                .collect(Collectors.partitioningBy(product -> product.getAmount() > 15));
        System.out.println(partitionList);

        boolean allMatch = productList.stream()
                .allMatch(p -> p.getName().length() > 3);
        System.out.println(allMatch);

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

    @NoArgsConstructor
    static class Product{
        private int amount;
        private String name;

        public int getAmount(){
            return this.amount;
        }

        public String getName(){
            return this.name;
        }

        public Product(int amount, String name){
            this.amount = amount;
            this.name = name;
        }
    }
}



