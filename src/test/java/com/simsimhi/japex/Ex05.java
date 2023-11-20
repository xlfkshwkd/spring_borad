package com.simsimhi.japex;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.simsimhi.entities.BoardData;
import com.simsimhi.entities.QBoardData;
import com.simsimhi.repositories.BoardDataRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.function.Predicate;

@SpringBootTest
@TestPropertySource(properties = "spring.profiles.active=test")
public class Ex05 {
    @Autowired
    private BoardDataRepository repository;

    @Test
    void test1() {
        QBoardData boardData = QBoardData.boardData;
        // BooleanExpression cond1 = boardData.subject.contains("목");

        List<BoardData> items = (List<BoardData>)repository.findAll(boardData.subject.contains("목"));
        items.stream().forEach(System.out::println);

    }
}