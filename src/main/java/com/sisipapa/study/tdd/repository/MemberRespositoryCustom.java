package com.sisipapa.study.tdd.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sisipapa.study.tdd.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.sisipapa.study.tdd.entity.QMember.member;

@Repository
public class MemberRespositoryCustom {

    @Autowired
    JPAQueryFactory factory;

    /**
     * id로 회원조회
     * @param findMember
     * @return
     */
    public MemberDTO findOne(MemberDTO findMember) {

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(member.id.eq(findMember.getId()));

        MemberDTO memberDTO = factory
                .select(Projections.constructor(MemberDTO.class,
                        member.id,
                        member.name,
                        member.gender,
                        member.phone
                ))
                .from(member)
                .where(builder)
                .fetchOne();

        return memberDTO;
    }

    public void save(MemberDTO member) {

    }

    public void delete(MemberDTO deleteMember) {
    }

}
