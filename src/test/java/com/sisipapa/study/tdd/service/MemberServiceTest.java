package com.sisipapa.study.tdd.service;

import com.sisipapa.study.tdd.dto.MemberDTO;
import com.sisipapa.study.tdd.entity.Member;
import com.sisipapa.study.tdd.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository repository;

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
        memberService.deleteMember(1L);

        Optional<Member> member = repository.findById(1L);
        assertThat(member).isEqualTo(Optional.empty());
    }
}
