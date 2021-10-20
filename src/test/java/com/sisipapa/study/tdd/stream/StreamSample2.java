package com.sisipapa.study.tdd.stream;

import com.sisipapa.study.tdd.dto.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamSample2 {

    List<Person> personList = new ArrayList<>();

    @BeforeEach
    void prepare(){
        personList.add(Person.builder().country("한국").city("서울").name("유저1").build());
        personList.add(Person.builder().country("한국").city("서울").name("유저2").build());
        personList.add(Person.builder().country("한국").city("부산").name("유저3").build());
        personList.add(Person.builder().country("중국").city("베이징").name("유저4").build());
        personList.add(Person.builder().country("미국").city("보스턴").name("유저5").build());
        personList.add(Person.builder().country("미국").city("뉴욕").name("유저6").build());
        personList.add(Person.builder().country("미국").city("뉴욕").name("유저7").build());
    }

    @Test
    void groupBy(){
        Map<String, List<Person>> result = personList.stream()
                .collect(Collectors.groupingBy(Person::getCountry));

        System.out.println(result.get("한국"));
        System.out.println(result.get("중국"));
        System.out.println(result.get("미국"));
    }

    @Test
    void groupBy2(){
        Map<String, Map<String, List<Person>>> result = personList.stream()
                .collect(Collectors.groupingBy(Person::getCountry, Collectors.groupingBy(Person::getCity)));

        System.out.println(result.get("한국").get("서울"));
        System.out.println(result.get("한국").get("부산"));
        System.out.println(result.get("중국").get("베이징"));
        System.out.println(result.get("미국").get("보스톤"));
        System.out.println(result.get("미국").get("뉴욕"));
    }

    @Test
    void reduce(){
        OptionalInt oInt = IntStream.range(1,4)
                .reduce((a, b)->{
                    return Integer.sum(a, b);
                });

        oInt.ifPresent(result -> System.out.println(result));

        int oInt2 = IntStream.range(1,4)
                .reduce(10, (a, b)->{
                    return Integer.sum(a, b);
                });

        System.out.println(oInt2);

    }

    @Test
    void parallelStream(){

        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        System.out.println(commonPool.getParallelism());

        Arrays.asList("a", "b", "c", "d", "e")
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter: %s [%s]\n", s, Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    System.out.format("map: %s [%s]\n", s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .forEach(s ->
                        System.out.format("forEach: %s [%s]\n", s, Thread.currentThread().getName())
                );

    }
}
