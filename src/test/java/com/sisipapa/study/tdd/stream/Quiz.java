package com.sisipapa.study.tdd.stream;

import com.sisipapa.study.tdd.dto.quiz.Trader;
import com.sisipapa.study.tdd.dto.quiz.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Quiz {

    List<Transaction> transactions;

    @BeforeEach
    void init(){

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    @Test
    void q1(){
        // Q1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리
        List<Transaction> answer1 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .collect(Collectors.toList());
        answer1.forEach(System.out::println);
    }

    @Test
    void q2(){
        // Q2. 거래자가 근무하는 모든 도시를 중복 없이 나열
        List<String> answer2 = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        answer2.forEach(System.out::println);
    }

    @Test
    void q3(){
        // Q3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬
        List<Trader> answer3 = transactions.stream()
                .map(transaction -> transaction.getTrader())
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        answer3.forEach(System.out::println);
    }

    @Test
    void q4(){
        // Q4. 모든 거래자의 이름을 알파벳순으로 정렬해서 반환
        String answer4 = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .reduce("", (name1, name2) -> name1 + " " + name2);
        System.out.println(answer4);

        String answer4Opt = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining(" "));
        System.out.println(answer4Opt);
    }

    @Test
    void q5(){
        // Q5. 밀라노에 거래자가 있는지
    }

    @Test
    void q6(){
        // Q6. 케임브리지에 거주하는 거래자의 모든 트랜잭션 값 출력
    }

    @Test
    void q7(){
        // Q7. 전체 트랜잭션 중 최댓값
    }

    @Test
    void q8(){
        // Q8. 전체 트랜잭션 중 최솟값
    }
}
