package com.giantstep.board.domain.board.repository;

import com.giantstep.board.domain.board.dto.BoardListDto;
import com.giantstep.board.domain.board.dto.BoardOneDetailDto;
import com.giantstep.board.domain.board.dto.BoardUpdateCheckPwdCondition;

import java.util.List;

public interface BoardRepositoryCustom {

    List<BoardListDto> findAllByBoardListDto();

    BoardOneDetailDto findByBoardOneDetailDto(Long boardId);

    Long checkBoardPwd(BoardUpdateCheckPwdCondition boardUpdateCheckPwdCondition);
}
