package com.sisipapa.study.tdd;

import com.sisipapa.study.tdd.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.stream.LongStream;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit() {
            LongStream.rangeClosed(1,5).forEach(index ->{

                String gender = "M";

                if(index % 2 == 0){
                    gender = "W";
                }

                int midNum = (int)(Math.random()*9000)+1000;
                int endNum = (int)(Math.random()*9000)+1000;

                Member initMember = Member.builder().name("name"+index).phone("010-"+midNum+"-"+endNum).gender(gender).build();
                em.persist(initMember);

            });
        }
    }
}


