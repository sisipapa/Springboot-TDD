package com.sisipapa.study.tdd;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class StreamSample {

    private Random ageRandom = new Random();

    // 로컬PC는 4CORE이고 병렬로 처리가 가능한 work Thread의 수도 4개이다. 5개를 처리하는데 걸리는 시간 6초
    @Test
    public void Test() {
        long start = System.currentTimeMillis();
        List<Person> person = createPerson(
                Arrays.asList("Jhon", "Yeom", "Takenaka", "Hikari", "Hikari2"));

        person.forEach(System.out::println);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    // ForkJoinPool Custom 생성시 병렬처리 가능한 최대 work Thread를 지정해서 처리 효율을 증가시킬 수 있다.
    @Test
    public void Test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        List<Person> person = createPerson2(
                Arrays.asList("Jhon", "Yeom", "Takenaka", "Hikari", "Hikari2"));

        person.forEach(System.out::println);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    public List<Person> createPerson(List<String> names) {
        return names.stream()
                .parallel() // 추가된 곳
                .map(Person::new)
                .map(person -> person.updateAge(findAge(person.getName())))
                .collect(Collectors.toList());
    }

    public List<Person> createPerson2(List<String> names) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(5);
        return forkJoinPool.submit(() -> {
            return names.stream()
                    .parallel()
                    .map(Person::new)
                    .map(person -> person.updateAge(findAge(person.getName())))
                    .collect(Collectors.toList());
        }).get();
    }

    public Integer findAge(String name) {
        Integer age = ageRandom.nextInt(name.length() * 3) + 1;
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ignore) {
        }

        return age;
    }
}