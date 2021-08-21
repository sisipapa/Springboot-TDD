package com.sisipapa.study.tdd.service;

import com.sisipapa.study.tdd.dto.MemberDTO;
import com.sisipapa.study.tdd.entity.Member;
import com.sisipapa.study.tdd.repository.MemberRepository;
import com.sisipapa.study.tdd.repository.MemberRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {

    private final MemberRepository respository;
    private final MemberRepositoryCustom respositoryCustom;

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

    /**
     * l 회원삭제 후 전체회원 카운트 리턴
     * @param l
     * @return
     */
    public void deleteMember(long l) {
        respository.deleteById(l);
    }
}
