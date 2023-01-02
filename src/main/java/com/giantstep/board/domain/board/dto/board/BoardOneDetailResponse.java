package com.giantstep.board.domain.board.dto.board;

import com.giantstep.board.domain.board.dto.comment.BoardCommentListDto;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class BoardOneDetailResponse {
    private BoardOneDetailDto boardOneDetailDto;
    private Page<BoardCommentListDto> boardCommentListDtos;

    public BoardOneDetailResponse(BoardOneDetailDto boardOneDetailDto, Page<BoardCommentListDto> boardCommentListDtos){
        this.boardOneDetailDto = boardOneDetailDto;
        this.boardCommentListDtos = boardCommentListDtos;
    }
}
