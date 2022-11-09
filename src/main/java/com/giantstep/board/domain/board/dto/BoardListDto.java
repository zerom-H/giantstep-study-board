package com.giantstep.board.domain.board.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardListDto {

    private Long boardId;
    private String boardWriter;
    private String boardTitle;
    private LocalDateTime boardCreateDate;

    public BoardListDto(Long boardId, String boardWriter, String boardTitle, LocalDateTime boardCreateDate) {
        this.boardId = boardId;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardCreateDate = boardCreateDate;
    }
}
