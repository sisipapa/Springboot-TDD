package com.sisipapa.study.tdd.repository;

import com.sisipapa.study.tdd.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRespository;

    @Test
    void save(){
        int midNum = (int)(Math.random()*9000)+1000;
        int endNum = (int)(Math.random()*9000)+1000;

        Member paramMember = Member.builder().name("name1").gender("W").phone("010-" + midNum + "-" + endNum).build();
        Member resultMember = memberRespository.save(paramMember);

        Assertions.assertNotNull(resultMember.getId());
    }

    @Test
    void findById(){
        Member resultMember = memberRespository.findById(5L).get();
        assertThat(resultMember.getName()).isEqualTo("name5");
    }

    @Test
    void deleteById(){
        Member deleteMember = Member.builder().id(1l).build();
        memberRespository.delete(deleteMember);

        assertThat(memberRespository.count()).isEqualTo(5L);
    }
}
