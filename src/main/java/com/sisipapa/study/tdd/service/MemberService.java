package com.sisipapa.study.tdd.service;

import com.sisipapa.study.tdd.dto.MemberDTO;
import com.sisipapa.study.tdd.entity.Member;
import com.sisipapa.study.tdd.repository.MemberRespository;
import com.sisipapa.study.tdd.repository.MemberRespositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {

    private final MemberRespository respository;
    private final MemberRespositoryCustom respositoryCustom;

    /**
     * 사용자등록
     * @param memberDTO
     * @return
     */
    public MemberDTO saveMember(MemberDTO memberDTO) {

        // DTO TO ENTITY
        Member newMember = Member.builder()
                .id(memberDTO.getId())
                .name(memberDTO.getName())
                .phone(memberDTO.getPhone())
                .gender(memberDTO.getGender())
                .build();

        newMember = respository.save(newMember);

        // ENTITY TO DTO
        memberDTO = MemberDTO.builder()
                .id(newMember.getId())
                .name(newMember.getName())
                .phone(newMember.getPhone())
                .gender(newMember.getGender())
                .build();

        return memberDTO;
    }

    /**
     * 회원조회
     * @param l
     * @return
     */
    public MemberDTO findOneMember(long l) {
        MemberDTO findMember = respositoryCustom.findOne(l);
        return findMember;
    }


    public Long deleteMember(long l) {
        respository.deleteById(l);
        return respository.count();
    }
}
