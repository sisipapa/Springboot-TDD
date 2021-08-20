package com.sisipapa.study.tdd.repository;

import com.sisipapa.study.tdd.dto.MemberDTO;
import com.sisipapa.study.tdd.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRespository memberRespository;

    @Autowired
    MemberRespositoryCustom memberRespositoryCustom;

    @BeforeEach
    void setUp(){
        LongStream.rangeClosed(1,10).forEach(index -> {
            String gender = "M";

            if(index % 2 == 0){
                gender = "W";
            }

            int midNum = (int)(Math.random()*9000)+1000;
            int endNum = (int)(Math.random()*9000)+1000;

            Member member = Member.of(index,"name"+index, gender,"010-" + midNum + "-" + endNum);
            memberRespository.save(member);
        });

    }


    @Test
    void 단건조회(){
        MemberDTO findMember = MemberDTO.builder().id(1L).build();
        MemberDTO resultMember = memberRespositoryCustom.findOne(findMember);

        assertThat(resultMember.getId()).isEqualTo(1L);
    }

    @Test
    void 저장(){
        int midNum = (int)(Math.random()*9000)+1000;
        int endNum = (int)(Math.random()*9000)+1000;

        Member newMember = Member.builder().name("name-저장").gender("W").phone("010-" + midNum + "-" + endNum).build();
        memberRespository.save(newMember);

        assertThat(memberRespository.count()).isEqualTo(11);
    }

    @Test
    void 수정(){
        int midNum = (int)(Math.random()*9000)+1000;
        int endNum = (int)(Math.random()*9000)+1000;

        Member newMember = Member.builder().id(1L).name("name-수정").gender("W").phone("010-" + midNum + "-" + endNum).build();
        memberRespository.save(newMember);

        MemberDTO findMember = MemberDTO.builder().id(1L).build();
        MemberDTO resultMember = memberRespositoryCustom.findOne(findMember);
        assertThat(resultMember.getName()).isEqualTo("name-수정");
    }

    @Test
    void 단건삭제(){
        Member deleteMember = Member.builder().id(10l).build();
        memberRespository.delete(deleteMember);

        assertThat(memberRespository.count()).isEqualTo(10L);
    }
}
