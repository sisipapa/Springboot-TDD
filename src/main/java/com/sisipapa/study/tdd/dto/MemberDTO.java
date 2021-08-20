package com.sisipapa.study.tdd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MemberDTO {
    private Long id;
    private String name;
    private String gender;
    private String phone;
}
