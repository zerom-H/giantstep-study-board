package com.giantstep.board.domain.board.dto.board;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardListDto {

    private Long boardId;
    private String boardWriter;
    private String boardTitle;
    private LocalDateTime boardUpdateDate;

    @QueryProjection
    public BoardListDto(Long boardId, String boardWriter, String boardTitle, LocalDateTime boardUpdateDate) {
        this.boardId = boardId;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardUpdateDate = boardUpdateDate;
    }
}
