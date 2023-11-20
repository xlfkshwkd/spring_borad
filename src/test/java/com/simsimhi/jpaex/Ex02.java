package com.simsimhi.jpaex;

import com.simsimhi.commons.constants.MemberType;
import com.simsimhi.entities.BoardData;
import com.simsimhi.entities.Member;
import com.simsimhi.models.board.BoardListService;
import com.simsimhi.repositories.BoardDataRepository;
import com.simsimhi.repositories.MemberRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@TestPropertySource(properties = "spring.profiles.active=test")
public class Ex02 {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BoardDataRepository boardDataRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private BoardListService listService;

    @BeforeEach
    void init(){
        Member member = Member.builder()
                .email("user01@test.org")
                .password("12345678")
                .userNm("사용자01")
                .mtype(MemberType.USER)
                .mobile("01000000000")
                .build();
        memberRepository.saveAndFlush(member);

        List<BoardData> items =new ArrayList<>();
        for (int i =1 ;i<10;i++){
            BoardData item =BoardData.builder()
                    .subject("제목"+i)
                    .content("내용"+i)
                    .member(member)
                    .build();
            items.add(item);
        }

        boardDataRepository.saveAllAndFlush(items);
        em.clear();

    }
    @Test
    void test1(){
        List<BoardData> items =boardDataRepository.findAll();
        for(BoardData item :items){
            Member member =item.getMember();
            String email =member.getEmail(); //2차 쿼리 +10
            System.out.println(email);
        }
    }
        // -> 지연로딩 목록 조회시 n+1 발생 일부 테이블을 조인 -> 처음부터 조인
        // -> Fetch Join (JPQL)
        //Fetch Join
        @Test
        void test2(){
           List<BoardData> items =boardDataRepository.getList2();
        }

        @Test
        void test3() {
            List<BoardData> items = listService.getList();
        }

        @Test
        void test4() {
            List<BoardData> items = boardDataRepository.findBySubjectContaining("목");
        }







}
