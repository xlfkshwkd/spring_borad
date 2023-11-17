package com.simsimhi.japex;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.simsimhi.entities.BoardData;
import com.simsimhi.entities.QBoardData;
import com.simsimhi.repositories.BoardDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@SpringBootTest
@TestPropertySource(properties = "spring.profiles.active=test")
public class Ex05 {
    @Autowired
    private BoardDataRepository repository;

    @BeforeEach
    void init(){
        List<BoardData> items = new ArrayList<>();
        for(int i =1; i<=10;i++) {
            BoardData item = BoardData.builder()
                    .subject("제목" + i)
                    .content("내용" + i)
                    .build();
            items.add(item);
        }

        repository.saveAllAndFlush(items);
    }

    @Test
    void test1() {
        QBoardData boardData = QBoardData.boardData;
        // BooleanExpression cond1 = boardData.subject.contains("목");

        List<BoardData> items = (List<BoardData>)repository.findAll(boardData.subject.contains("목"));
        items.stream().forEach(System.out::println);
    }

    @Test
    void test2(){
        QBoardData boardData =QBoardData.boardData;
        BooleanBuilder andBuilder =new BooleanBuilder();
        BooleanBuilder orBuilder =new BooleanBuilder();

        orBuilder.or(boardData.subject.contains("목"))
                .or(boardData.content.contains("용"));

        andBuilder.and(orBuilder)
                .and(boardData.seq.in(2,4,6,8));

        /*
            builder.and(boardData.subject.contains("목"))
                .and(boardData.content.contains("용"))
                .and(boardData.seq.in(2,4,6,8));
        */

        List<BoardData> items = (List<BoardData>)repository.findAll(andBuilder);
        items.stream().forEach(System.out::println);
        //게시글 -회원 many쪽 엔티티로 만들어진 테이블에 One의 컬럼의 기본키가 외래키 추가



    }
}