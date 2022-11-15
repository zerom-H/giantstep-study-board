package com.giantstep.board.domain.board.dto;

import com.giantstep.board.domain.board.entity.Board;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardOneDetailDto {

    private Long boardId;
    private String boardWriter;
    private String boardTitle;
    private String boardContents;
    private LocalDateTime boardUpdateDate;

    public BoardOneDetailDto(Board board) {
        this.boardId = board.getId();
        this.boardWriter = board.getWriter();
        this.boardTitle = board.getTitle();
        this.boardContents = board.getContents();
        this.boardUpdateDate = board.getUpdateDate();
    }
}
