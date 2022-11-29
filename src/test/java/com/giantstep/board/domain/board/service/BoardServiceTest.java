package com.giantstep.board.domain.board.service;

import com.giantstep.board.domain.board.dto.BoardListDto;
import com.giantstep.board.domain.board.dto.BoardOneDetailDto;
import com.giantstep.board.domain.board.dto.BoardUpdateCheckPwdCondition;
import com.giantstep.board.domain.board.dto.BoardUpdateFormDto;
import com.giantstep.board.domain.board.entity.Board;
import com.giantstep.board.domain.board.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        Board insert_board = Board.builder()
                .id(116L)
                .writer("이정준")
                .title("단건조회 테스트1")
                .contents("단건조회 테스트1 입니다.")
                .password("1234")
                .build();

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
    void 게시물_전체조회_페이징처리추가() {

        //give
        Pageable pageable = PageRequest.of(0, 10);
        int boardListCount = boardRepository.findAll().size();

        //when
        Page<BoardListDto> boardListDtoAddPaging = boardRepository.findAllByBoardListDtoAddPaging(pageable);

        //then
        assertEquals(boardListDtoAddPaging.getTotalElements(), boardListCount);
        assertEquals(boardListDtoAddPaging.getTotalPages(), 6);
        assertEquals(boardListDtoAddPaging.isFirst(), true);
        assertEquals(boardListDtoAddPaging.hasNext(), true);
        assertEquals(boardListDtoAddPaging.getNumber(), 0);
        assertEquals(boardListDtoAddPaging.getSize(), 10);

    }

    @Test
    void 게시물_단건조회() {

        //give
        Board boardTest = Board.builder()
                .id(100L)
                .writer("이정준")
                .title("단건조회 테스트1")
                .contents("단건조회 테스트1 입니다.")
                .password("1234")
                .build();
        Board saveBoardTest = boardRepository.save(boardTest);

        //when
        BoardOneDetailDto findBoardOneDetailDto = boardRepository.findByBoardOneDetailDto(saveBoardTest.getId());

        //then
        assertEquals(saveBoardTest.getId(), findBoardOneDetailDto.getBoardId());
    }

    @Test
    void 게시물단건_수정_비밀번호_검증() {

        //give
        Board boardTest = Board.builder()
                .id(100L)
                .writer("이정준")
                .title("단건조회 테스트1")
                .contents("단건조회 테스트1 입니다.")
                .password("1234")
                .build();

        Board saveBoardTest = boardRepository.save(boardTest);

        String updateBoardInsertPassword = "1234";
        Integer updateBoardId = (boardRepository.findByBoardOneDetailDto(saveBoardTest.getId()).getBoardId()).intValue();

        BoardUpdateCheckPwdCondition boardUpdateCheckPwdCondition = BoardUpdateCheckPwdCondition.builder()
                .boardId(updateBoardId)
                .boardPassword(updateBoardInsertPassword)
                .build();

        //when
        Long checkBoardPwd = boardRepository.checkBoardPwd(boardUpdateCheckPwdCondition);

        //then
        assertEquals(checkBoardPwd, 1);
    }

    @Test
    void 게시물단건_수정하기() {

        //give
        Board boardTest = Board.builder()
                .id(100L)
                .writer("이정준")
                .title("단건조회 테스트1")
                .contents("단건조회 테스트1 입니다.")
                .password("1234")
                .build();
        Board saveBoardTest = boardRepository.save(boardTest);

        String updateBoardTitle = "수정한 단건조회 테스트1";
        String updateBoardContents = "수정한 단건조회 테스트1 입니다.";

        BoardUpdateFormDto boardUpdateFormDto = BoardUpdateFormDto.builder()
                .boardId(saveBoardTest.getId())
                .boardWriter(saveBoardTest.getWriter())
                .boardTitle(updateBoardTitle)
                .boardContents(updateBoardContents)
                .boardPassword(saveBoardTest.getPassword())
                .build();

        //when
        Board findUpdateBoard = boardRepository.findById(boardUpdateFormDto.getBoardId()).get().updateBoardOne(boardUpdateFormDto.toEntity());

        //then
        assertEquals(findUpdateBoard.getTitle(), updateBoardTitle);
        assertEquals(findUpdateBoard.getContents(), updateBoardContents);
    }
}