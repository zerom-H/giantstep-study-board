package com.giantstep.board.domain.board.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardOneDetailDto {

    private Long boardId;
    private String boardWriter;
    private String boardTitle;
    private String boardContents;
    private LocalDateTime boardUpdateDate;

    public BoardOneDetailDto(Long boardId, String boardWriter, String boardTitle, String boardContents, LocalDateTime boardUpdateDate) {
        this.boardId = boardId;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardContents = boardContents;
        this.boardUpdateDate = boardUpdateDate;
    }
}
