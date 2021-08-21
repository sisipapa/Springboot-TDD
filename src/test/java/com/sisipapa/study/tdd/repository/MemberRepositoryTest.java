package com.sisipapa.study.tdd.repository;

import com.sisipapa.study.tdd.dto.MemberDTO;
import com.sisipapa.study.tdd.entity.Member;
import org.junit.jupiter.api.Assertions;
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

//    @BeforeEach
//    void setUp(){
//        LongStream.rangeClosed(1,10).forEach(index -> {
//            String gender = "M";
//
//            if(index % 2 == 0){
//                gender = "W";
//            }
//
//            int midNum = (int)(Math.random()*9000)+1000;
//            int endNum = (int)(Math.random()*9000)+1000;
//
//            Member member = Member.of(index,"name"+index, gender,"010-" + midNum + "-" + endNum);
//            memberRespository.save(member);
//        });
//
//    }

    @Test
    void 저장(){
        int midNum = (int)(Math.random()*9000)+1000;
        int endNum = (int)(Math.random()*9000)+1000;

        Member paramMember = Member.builder().name("name1").gender("W").phone("010-" + midNum + "-" + endNum).build();
        Member resultMember = memberRespository.save(paramMember);

        Assertions.assertNotNull(resultMember.getId());
    }

    @Test
    void 단건조회(){
        MemberDTO resultMember = memberRespositoryCustom.findOne(1L);
        assertThat(resultMember.getId()).isEqualTo(1L);
    }

    @Test
    void 단건삭제(){
        Member deleteMember = Member.builder().id(1l).build();
        memberRespository.delete(deleteMember);

        assertThat(memberRespository.count()).isEqualTo(0L);
    }
}
