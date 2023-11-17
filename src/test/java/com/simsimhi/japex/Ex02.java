package com.simsimhi.japex;

import com.simsimhi.commons.constants.MemberType;
import com.simsimhi.entities.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class Ex02 {

    @PersistenceContext
    private EntityManager em;

    @Test
    void test1(){
        Member member =Member.builder()
                .email("user01@test.org")
                .password("123456")
                .userNm("사용자01")
                .mobile("010")
                .mtype(MemberType.USER)
                .build();

        em.persist(member); //영속성 상태로 만듬
        em.flush(); //쿼리 실행

        Member member2 =em.find(Member.class,member.getUserNo());
        System.out.println(member2);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        member2.setUserNm("수정 사용자01");
        em.flush();

        member2=em.find(Member.class,member.getUserNo());
        System.out.println(member2); //수정 후

        //ordinal : 정의된 상수의 위치 번호 (0,1,2)
        //name()

    }
    @Test
    void test2(){


    }
}
