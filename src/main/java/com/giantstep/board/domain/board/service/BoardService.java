package com.giantstep.board.domain.board.service;

import com.giantstep.board.domain.board.dto.BoardListDto;
import com.giantstep.board.domain.board.dto.BoardOneDetailDto;
import com.giantstep.board.domain.board.dto.BoardUpdateCheckPwdCondition;
import com.giantstep.board.domain.board.entity.Board;
import com.giantstep.board.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long saveBoard(Board board) {
        return boardRepository.save(board).getId();
    }

    public List<BoardListDto> findAllBoardList() {
        return boardRepository.findAllByBoardListDto();
    }

    public BoardOneDetailDto findByBoardId(Long boardId) {
        return boardRepository.findByBoardOneDetailDto(boardId);
    }

    @Transactional
    public Long updateBoard(Board updateBoard) {

        Board findUpdateBoard = boardRepository.findById(updateBoard.getId()).get().updateBoardOne(updateBoard);
        return findUpdateBoard.getId();
    }

    public Long checkUpdateBoardPwd(BoardUpdateCheckPwdCondition boardUpdateCheckPwdCondition) {
        return boardRepository.checkBoardPwd(boardUpdateCheckPwdCondition);
    }
}
