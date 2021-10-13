package com.sisipapa.study.tdd.stream;

import com.sisipapa.study.tdd.dto.Person;
import com.sisipapa.study.tdd.dto.PersonGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamSample3 {

    List<Person> personList = new ArrayList<>();

    @BeforeEach
    void prepare(){
        personList.add(Person.builder().country("한국").city("서울").female(false).name("유저1").build());
        personList.add(Person.builder().country("한국").city("서울").female(true).name("유저2").build());
        personList.add(Person.builder().country("한국").city("서울").female(false).name("유저3").build());
        personList.add(Person.builder().country("한국").city("서울").female(true).name("유저4").build());
        personList.add(Person.builder().country("한국").city("부산").female(true).name("유저5").build());
        personList.add(Person.builder().country("한국").city("부산").female(false).name("유저6").build());
        personList.add(Person.builder().country("중국").city("베이징").female(false).name("유저7").build());
        personList.add(Person.builder().country("중국").city("베이징").female(true).name("유저8").build());
        personList.add(Person.builder().country("미국").city("뉴욕").female(true).name("유저9").build());
        personList.add(Person.builder().country("미국").city("뉴욕").female(false).name("유저10").build());
        personList.add(Person.builder().country("미국").city("보스턴").female(false).name("유저11").build());
        personList.add(Person.builder().country("미국").city("보스턴").female(true).name("유저12").build());
        personList.add(Person.builder().country("미국").city("보스턴").female(true).name("유저13").build());
        personList.add(Person.builder().country("미국").city("엘에이").female(false).name("유저14").build());
    }

    @Test
    void groupBy(){
        var result = personList.stream().collect(Collectors.groupingBy(PersonGroup::new));

        for(PersonGroup group : result.keySet()){
            System.out.println(String.format("%s/%s/%s : %s",
                    group.country,
                    group.city,
                    group.female ? "여" : "남",
                    result.get(group)));
        }
    }

}
