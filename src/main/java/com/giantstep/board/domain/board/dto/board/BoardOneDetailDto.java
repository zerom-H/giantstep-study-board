package com.giantstep.board.domain.board.dto.board;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardOneDetailDto {

    private Long boardId;
    private String boardWriter;
    private String boardTitle;
    private String boardContents;
    private String boardPassword;
    private LocalDateTime boardUpdateDate;

    @Builder
    @QueryProjection
    public BoardOneDetailDto(Long boardId, String boardWriter, String boardTitle, String boardContents,String boardPassword, LocalDateTime boardUpdateDate) {
        this.boardId = boardId;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardContents = boardContents;
        this.boardPassword = boardPassword;
        this.boardUpdateDate = boardUpdateDate;
    }
}
