package com.sisipapa.study.tdd;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamBasic {

    @Test
    void filter(){
        List<String> list = Arrays.asList("Java", "Scala", "Groovy", "Python", "Go", "Swift", "Java");

        System.out.println("### BEFORE");
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
}
