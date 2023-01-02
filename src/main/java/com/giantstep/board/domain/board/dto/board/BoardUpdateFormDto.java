package com.giantstep.board.domain.board.dto.board;

import com.giantstep.board.domain.board.entity.Board;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class BoardUpdateFormDto {

    private Long boardId;

    private String boardWriter;

    @NotBlank(message = "글 제목은 공백일 수 없습니다.")
    private String boardTitle;

    @NotBlank(message = "글 내용은 공백일 수 없습니다.")
    private String boardContents;

    private String boardPassword;

    public Board toEntity() {
        Board board = Board.builder()
                .id(boardId)
                .writer(boardWriter)
                .title(boardTitle)
                .contents(boardContents)
                .password(boardPassword)
                .build();
        return board;
    }
}
