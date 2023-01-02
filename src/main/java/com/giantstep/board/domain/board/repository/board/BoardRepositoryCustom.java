package com.giantstep.board.domain.board.repository.board;

import com.giantstep.board.domain.board.dto.board.BoardListDto;
import com.giantstep.board.domain.board.dto.board.BoardOneDetailDto;
import com.giantstep.board.domain.board.dto.board.BoardSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepositoryCustom {

    List<BoardListDto> findAllByBoardListDto();

    Page<BoardListDto> findAllByBoardListDtoAddPaging(Pageable pageable, BoardSearchCondition boardSearchCondition);

    BoardOneDetailDto findByBoardOneDetailDto(Long boardId);
}
