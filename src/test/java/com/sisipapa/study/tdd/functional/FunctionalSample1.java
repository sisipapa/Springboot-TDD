package com.sisipapa.study.tdd.functional;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

public class FunctionalSample1 {

    @Test
    void test1(){
        Function<String, Function<String, String>> greeting = (greetingText) -> {
            return (name) -> {
                return greetingText + " " + name;
            };
        };

        Function<String, String> hello = greeting.apply("Hello");
        Function<String, String> hi = greeting.apply("HI");

        System.out.println(hello.apply("sisipapa!!"));
        System.out.println(hi.apply("sisipapa!!"));
    }
}
