package com.sisipapa.study.tdd.service;

import com.sisipapa.study.tdd.dto.MemberDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    void saveMember(){
        MemberDTO memberDTO = MemberDTO.builder()
                .name("name1")
                .phone("010-1111-1111")
                .gender("W")
                .build();
        memberDTO = memberService.saveMember(memberDTO);
        Long id = memberDTO.getId();

        Assertions.assertNotNull(id);
    }

    @Test
    void findOneMember(){
        MemberDTO memberDTO = memberService.findOneMember(5L);
        assertThat(memberDTO.getName()).isEqualTo("name5");
    }

    @Test
    void deleteMember(){
        Long totalCount = memberService.deleteMember(1L);
        assertThat(totalCount).isEqualTo(4L);
    }
}
