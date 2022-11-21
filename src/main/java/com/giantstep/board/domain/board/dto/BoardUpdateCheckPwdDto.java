package com.giantstep.board.domain.board.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BoardUpdateCheckPwdDto {
    private Long boardId;

    @NotBlank(message = "글 비밀번호는 공백으로 입력할 수 없습니다.")
    private String boardPassword;

    public BoardUpdateCheckPwdDto(Long boardId, String boardPassword) {
        this.boardId = boardId;
        this.boardPassword = boardPassword;
    }
}
