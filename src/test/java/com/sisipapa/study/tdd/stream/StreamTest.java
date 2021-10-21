package com.sisipapa.study.tdd.stream;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class StreamTest {

    @Test
    void test1(){
        List<List<String>> persons = new ArrayList<>();
        persons.add(List.of("이름", "취미", "소개"));
        persons.add(List.of("정프로", "개발:당구:축구", "개발하는데 뛰긴 싫어"));
        persons.add(List.of("김프로", "축구:농구:야구", "구기종목 좋아요"));
        persons.add(List.of("앙몬드", "피아노", " 죠르디가 좋아요 좋아좋아너무좋아"));
        persons.add(List.of("죠르디", "스포츠댄스:개발", "개발하는 죠르디 좋아"));
        persons.add(List.of("박프로", "골프:야구", "운동이 좋아요"));
        persons.add(List.of("정프로", "개발:축구:농구", "개발도 좋고 운동도 좋아"));

        persons.remove(0);
        //결과를 담을 해시맵 구성
        Map<String, Integer> result = new HashMap<>();
        persons.stream()
                .flatMap(member -> Arrays.stream(member.get(1).split(":"))) //취미를 플랫하게 스트림으로 분리
                .forEach(hobby -> result.merge(hobby, 1, (oldValue, newValue)->++oldValue));
        //출력
        result.entrySet().forEach(entry-> System.out.println(entry.getKey() + " " + entry.getValue()));

    }

    @Test
    void test2(){
        List<List<String>> persons = new ArrayList<>();
        persons.add(List.of("이름", "취미", "소개"));
        persons.add(List.of("정프로", "개발:당구:축구", "개발하는데 뛰긴 싫어"));
        persons.add(List.of("김프로", "축구:농구:야구", "구기종목 좋아요"));
        persons.add(List.of("앙몬드", "피아노", " 죠르디가 좋아요 좋아좋아너무좋아"));
        persons.add(List.of("죠르디", "스포츠댄스:개발", "개발하는 죠르디 좋아"));
        persons.add(List.of("박프로", "골프:야구", "운동이 좋아요"));
        persons.add(List.of("정프로", "개발:축구:농구", "개발도 좋고 운동도 좋아"));

        persons.remove(0);
        //결과를 담을 해시맵 구성
        Map<String, Integer> result = new HashMap<>();
        persons.stream()
                .filter(person -> person.get(0).startsWith("정"))
                .flatMap(person -> Arrays.stream(person.get(1).split(":")) )
                .forEach(hobboy -> result.merge(hobboy, 1, (oldValue, newValue) -> ++ oldValue ));

        //출력
        result.entrySet().forEach(entry-> System.out.println(entry.getKey() + " " + entry.getValue()));
    }

    @Test
    void test3(){
        // List에 저장된 단어들의 접두사가 각각 몇개씩 있는지 Map<String, Integer>으로 변환하여라. ex) ("T", 1), ("a", 2) ...
        List<String> WORDS = Arrays.asList("TONY", "a", "hULK", "B", "america", "X", "nebula", "Korea");

        Map<String, Integer> result = new HashMap<>();
        WORDS.stream()
                .map(word -> String.valueOf(word.charAt(0)))
                .forEach(startChar -> result.merge(startChar, 1, (oldValue, newValue) -> ++ oldValue));

        //출력
        result.entrySet().forEach(entry-> System.out.println(entry.getKey() + " " + entry.getValue()));
    }

    @Test
    void test4(){
        // List에 저장된 단어들 중에서 단어의 길이가 2 이상인 경우에만, 모든 단어를 대문자로 변환하여
        // 스페이스로 구분한 하나의 문자열로 합한 결과를 반환하여라. ex) ["Hello", "a", "Island", "b"] -> “HI”
        List<String> WORDS = Arrays.asList("TONY", "a", "hULK", "B", "america", "X", "nebula", "Korea");
        String result = WORDS.stream()
                .filter(word -> word.length() >= 2)
                .map(String::toUpperCase)
                .reduce((a,b) -> a + " " + b).orElse("");
        System.out.println(result);
    }
}
























