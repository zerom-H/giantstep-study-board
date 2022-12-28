package com.giantstep.board.domain.board.service;

import com.giantstep.board.domain.board.dto.comment.BoardCommentListDto;
import com.giantstep.board.domain.board.entity.Board;
import com.giantstep.board.domain.board.entity.BoardComment;
import com.giantstep.board.domain.board.repository.board.BoardRepository;
import com.giantstep.board.domain.board.repository.comment.BoardCommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = true)
class BoardCommentServiceTest {

    @Autowired
    private BoardCommentRepository boardCommentRepository;
    @Autowired
    private BoardRepository boardRepository;

    @BeforeEach
    void 댓글_초기_데이터() {

        long boardId = 177;
        Board findBoard = boardRepository.findById(boardId).get();
        BoardComment boardComment = BoardComment.createBoardComment()
                .boardCommentWriter("이정준")
                .boardCommentContents("댓글 테스트 데이터 입니다.")
                .boardCommentPassword("1234")
                .board(findBoard)
                .build();

        boardCommentRepository.save(boardComment);
    }

    @Test
    void 댓글_조회() {

        //give
        long boardId = 177;
        Pageable pageable = PageRequest.of(0, 5);

        //when
        Page<BoardCommentListDto> byBoardCommentListDtoAddPaging = boardCommentRepository.findAllByBoardCommentListDtoAddPaging(pageable, boardId);

        //then
        assertEquals(byBoardCommentListDtoAddPaging.getTotalElements(), boardCommentRepository.findAll().size()); // 댓글 총개수 비교
        assertEquals(byBoardCommentListDtoAddPaging.getSize(), pageable.getPageSize());

    }

}