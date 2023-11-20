package com.simsimhi.jpaex;

import com.simsimhi.commons.constants.MemberType;
import com.simsimhi.entities.BoardData;
import com.simsimhi.entities.Member;
import com.simsimhi.repositories.BoardDataRepository;
import com.simsimhi.repositories.MemberRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;

@Transactional
@SpringBootTest
@TestPropertySource(properties = "spring.profiles.active=test")
public class Ex01 {
    @Autowired
    private BoardDataRepository boardDataRepository;

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private EntityManager em;




    //@BeforeEach
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
        em.clear();
    }
    @Test
    void test1() {

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

        BoardData data = boardDataRepository.findById(1L).orElse(null);

        Member member2 = data.getMember();
        String email = member2.getEmail(); // 2차 쿼리 실행
        System.out.println(email);
    }

    @ResponseBody
    @GetMapping("/info2")
    void init2() {
        Member member = Member.builder()
                .email("user01@test.org")
                .password("123456")
                .userNm("사용자01")
                .mtype(MemberType.USER)
                .build();
        memberRepository.saveAndFlush(member);
        List<BoardData> items =new ArrayList<>();
        for(int i =1;i<10;i++){
            BoardData item = BoardData.builder()
                    .subject("제목")
                    .content("내용")
                    .member(member)
                    .build();
            items.add(item);
        }
        boardDataRepository.saveAllAndFlush(items);



    }
}
