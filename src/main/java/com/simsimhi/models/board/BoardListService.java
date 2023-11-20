package com.simsimhi.models.board;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.simsimhi.entities.BoardData;
import com.simsimhi.entities.QBoardData;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BoardListService {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private BoardListService listService;


    public List<BoardData> getList(){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QBoardData boardData = QBoardData.boardData;
        JPAQuery<BoardData> query = queryFactory.selectFrom(boardData)
                .leftJoin(boardData.member)
                .fetchJoin();

        List<BoardData> items = query.fetch();

        return items;
    }



}
