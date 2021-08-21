package com.sisipapa.study.tdd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sisipapa.study.tdd.dto.MemberDTO;
import com.sisipapa.study.tdd.entity.Member;
import com.sisipapa.study.tdd.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class MemberControllerTest {

    @Autowired
    private MemberRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .alwaysDo(print())
                .build();
    }

    @Test
    void getPerson() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/member/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("name5"))
                .andExpect(jsonPath("$.id").value(5L));
    }

    @Test
    void creatgePerson() throws Exception {
        MemberDTO dto = MemberDTO.builder().name("name100").gender("M").phone("010-2222-3333").build();

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/member")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(toJsonString(dto)))
                .andExpect(status().isCreated());

        Member result = repository.findAll(Sort.by(Sort.Direction.DESC, "id")).get(0);

        assertAll(
                () -> assertThat(result.getName()).isEqualTo("name100"),
                () -> assertThat(result.getGender()).isEqualTo("M"),
                () -> assertThat(result.getPhone()).isEqualTo("010-2222-3333")
        );
    }

    @Test
    void deleteMember() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/api/member/1"))
                .andExpect(status().isOk());

        Optional<Member> member = repository.findById(1L);
        assertThat(member).isEqualTo(Optional.empty());
    }

    private String toJsonString(MemberDTO dto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(dto);
    }

}
