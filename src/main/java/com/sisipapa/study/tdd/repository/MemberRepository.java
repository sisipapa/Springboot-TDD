package com.sisipapa.study.tdd.repository;

import com.sisipapa.study.tdd.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
