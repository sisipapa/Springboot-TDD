package com.sisipapa.study.tdd.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Data
public class Person {

    private String country; // 나라
    private String city; // 도시
    private String name; // 이름
    private boolean female; // 성별
}
