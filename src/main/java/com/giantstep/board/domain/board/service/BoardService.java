package com.giantstep.board.domain.board.service;

import com.giantstep.board.domain.board.controller.dto.BoardAddFormDto;
import com.giantstep.board.domain.board.entity.Board;
import com.giantstep.board.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void saveBoard(BoardAddFormDto boardAddFormDto) {
        boardRepository.save(boardAddFormDto.toEntity());
    }
}
