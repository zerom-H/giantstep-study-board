package com.giantstep.board.domain.board.service;

import com.giantstep.board.domain.board.dto.BoardListDto;
import com.giantstep.board.domain.board.entity.Board;
import com.giantstep.board.domain.board.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@Rollback(value = true)
class BoardServiceTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    void 게시물_작성() {

        //give
        Board insert_board = new Board(31L, "이정준", "테스트 작성", "게시 물 저장 테스트 입니다.", "1234");

        //when
        Long saveBoardId = boardRepository.save(insert_board).getId();

        //then
        Board findSaveBoard = boardRepository.findById(saveBoardId).get();
        assertEquals(insert_board.getId(), findSaveBoard.getId());
        assertEquals(insert_board.getWriter(), findSaveBoard.getWriter());
        assertEquals(insert_board.getTitle(), findSaveBoard.getTitle());
        assertEquals(insert_board.getContents(), findSaveBoard.getContents());
        assertEquals(insert_board.getPassword(), findSaveBoard.getPassword());

    }

    @Test
    void 게시물_전체조회() {

        //give
        long boardListCount = boardRepository.count();

        //when
        List<BoardListDto> boardListDtoList = boardRepository.findAllByBoardListDto();

        //then
        assertEquals(boardListCount, boardListDtoList.size());
    }

    @Test
    void 게시물_단건조회() {

        //give
        Board boardTest = new Board(
                100L, "이정준", "단건조회 테스트1",
                "단건조회 테스트1 입니다.", "1234");
        Board saveBoardTest = boardRepository.save(boardTest);

        //when
        Board findBoardOne = boardRepository.findById(saveBoardTest.getId()).get();

        //then
        assertEquals(saveBoardTest, findBoardOne);
    }
}