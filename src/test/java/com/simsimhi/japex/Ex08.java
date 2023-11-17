package com.simsimhi.japex;

import com.fasterxml.classmate.MemberResolver;
import com.simsimhi.commons.constants.MemberType;
import com.simsimhi.entities.BoardData;
import com.simsimhi.entities.Member;
import com.simsimhi.repositories.BoardDataRepository;
import com.simsimhi.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "spring.profiles.active=test")
public class Ex08 {
    @Autowired
    private BoardDataRepository boardDataRepository;
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void init() {
        Member member = Member.builder()
                .email("user01@test.org")
                .password("123456")
                .userNm("사용자01")
                .mtype(MemberType.USER)
                .build();
        memberRepository.saveAndFlush(member);

        BoardData item = BoardData.builder()
                .subject("제목")
                .content("내용")
                .member(member)
                .build();
        boardDataRepository.saveAndFlush(item);
    }
/*
    @Test
    void test1() {
        BoardData data = boardDataRepository.findById(1L).orElse(null);

        Member member = data.getMember();
        String email = member.getEmail(); // 2차 쿼리 실행
        System.out.println(email);
    }
*/


}
