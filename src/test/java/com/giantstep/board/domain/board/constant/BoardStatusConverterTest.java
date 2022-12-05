package com.giantstep.board.domain.board.constant;

import com.giantstep.board.domain.board.entity.Board;
import com.giantstep.board.domain.board.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = true)
class BoardStatusConverterTest {

    @Autowired
    private BoardRepository boardRepository;

    @PersistenceContext
    private EntityManager em;

    /** convert 테스트하기 위해서 리포지토리에 별도의 Querydsl 메소드를 만들지 않고 EntityManager 를 활용한 native query 를 활용해서 컨버터 검증 */
    @Test
    void attribute_컨버터_테스트() {

        //give
        Board boardTest = Board.builder()
                .writer("이정준")
                .title("테스트1")
                .contents("테스트1 입니다.")
                .password("1234")
                .boardStatus(BoardStatus.ALIVE)
                .build();
        Board saveBoardTest = boardRepository.save(boardTest);

        String convertCode = saveBoardTest.getBoardStatus().getCode();
        Long condId = saveBoardTest.getId();

        //when
        Query query = em.createNativeQuery("select * from board where id= :condId and board_status = :code", Board.class);
        query.setParameter("condId", condId);
        query.setParameter("code", "1");
        Board result = (Board) query.getSingleResult();


        //then
        String resultDesc = result.getBoardStatus().getDesc();
        assertEquals(resultDesc, "작성");
    }

}