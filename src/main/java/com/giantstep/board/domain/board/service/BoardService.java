package com.giantstep.board.domain.board.service;

import com.giantstep.board.domain.board.entity.Board;
import com.giantstep.board.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long saveBoard(Board board) {
        return boardRepository.save(board).getId();
    }

    public List<Board> findAllBoardList() {
        return boardRepository.findAll();
    }

    public Board findByBoardId(Long boardId) {
        return boardRepository.findById(boardId).get();
    }
}
