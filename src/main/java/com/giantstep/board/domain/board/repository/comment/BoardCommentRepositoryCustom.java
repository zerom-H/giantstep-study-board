package com.giantstep.board.domain.board.repository.comment;

import com.giantstep.board.domain.board.dto.comment.BoardCommentListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardCommentRepositoryCustom {

    Page<BoardCommentListDto> findAllByBoardCommentListDtoAddPaging(Pageable pageable, Long boardId);
}
