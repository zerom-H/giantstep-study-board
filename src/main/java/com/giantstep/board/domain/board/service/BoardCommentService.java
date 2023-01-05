package com.giantstep.board.domain.board.service;

import com.giantstep.board.domain.board.dto.comment.BoardCommentAddFormDto;
import com.giantstep.board.domain.board.entity.Board;
import com.giantstep.board.domain.board.entity.BoardComment;
import com.giantstep.board.domain.board.repository.board.BoardRepository;
import com.giantstep.board.domain.board.repository.comment.BoardCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BoardCommentService {

    private final BoardRepository boardRepository;
    private final BoardCommentRepository boardCommentRepository;

    public Long saveBoardComment(Long boardId, BoardCommentAddFormDto boardCommentAddFormDto) {

        Board findBoard = boardRepository.findById(boardId).get();
        boardCommentAddFormDto.setBoard(findBoard);
        BoardComment boardComment = boardCommentAddFormDto.toEntity();
        Long saveBoardCommentId = boardCommentRepository.save(boardComment).getId();

        return saveBoardCommentId;
    }

    @Transactional
    public Long updateBoardComment(Long boardCommentId, BoardComment updateBoardComment) {
        return boardCommentRepository.findById(boardCommentId).get().updateBoardComment(updateBoardComment).getId();
    }
}
