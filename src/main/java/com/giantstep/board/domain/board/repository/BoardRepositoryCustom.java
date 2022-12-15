package com.giantstep.board.domain.board.repository;

import com.giantstep.board.domain.board.dto.BoardListDto;
import com.giantstep.board.domain.board.dto.BoardOneDetailDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepositoryCustom {

    List<BoardListDto> findAllByBoardListDto();

    Page<BoardListDto> findAllByBoardListDtoAddPaging(Pageable pageable);

    BoardOneDetailDto findByBoardOneDetailDto(Long boardId);
}
