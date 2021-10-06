package com.sisipapa.study.tdd;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class StreamSample {

    private Random ageRandom = new Random();

    @Test
    public void Test() {
        List<Person> person = createPerson(
                Arrays.asList("Jhon", "Yeom", "Takenaka", "Hikari"));

        person.forEach(System.out::println);
    }

    public List<Person> createPerson(List<String> names) {
        return names.stream()
                .parallel() // 추가된 곳
                .map(Person::new)
                .map(person -> person.updateAge(findAge(person.getName())))
                .collect(Collectors.toList());
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