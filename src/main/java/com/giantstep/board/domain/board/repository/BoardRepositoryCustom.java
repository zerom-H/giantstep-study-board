package com.giantstep.board.domain.board.repository;

import com.giantstep.board.domain.board.dto.BoardDeleteCheckRequest;
import com.giantstep.board.domain.board.dto.BoardListDto;
import com.giantstep.board.domain.board.dto.BoardOneDetailDto;
import com.giantstep.board.domain.board.dto.BoardUpdateCheckRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepositoryCustom {

    List<BoardListDto> findAllByBoardListDto();

    Page<BoardListDto> findAllByBoardListDtoAddPaging(Pageable pageable);

    BoardOneDetailDto findByBoardOneDetailDto(Long boardId);

    Long checkUpdateBoardRequest(BoardUpdateCheckRequest boardUpdateCheckRequest);

    Long checkDeleteBoardRequest(BoardDeleteCheckRequest boardDeleteCheckRequest);
}
