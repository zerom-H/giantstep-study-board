package com.giantstep.board.domain.board.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class BoardUpdateFormDto {

    private Long boardId;

    private String boardWriter;

    @NotBlank(message = "글 제목은 공백일 수 없습니다.")
    private String boardTitle;

    @NotBlank(message = "글 내용은 공백일 수 없습니다.")
    private String boardContents;

    public BoardUpdateFormDto(Long boardId, String boardWriter, String boardTitle, String boardContents) {
        this.boardId = boardId;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardContents = boardContents;
    }
}
