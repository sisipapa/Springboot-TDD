package com.sisipapa.study.tdd.controller;

import com.sisipapa.study.tdd.dto.MemberDTO;
import com.sisipapa.study.tdd.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService service;

    @GetMapping("/{id}")
    public MemberDTO getPerson(@PathVariable Long id){
        return service.findOneMember(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDTO createPerson(@RequestBody MemberDTO dto){
        return service.saveMember(dto);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id){
        service.deleteMember(id);
    }
}
