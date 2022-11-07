package com.giantstep.board.domain.board.controller.dto;

import com.giantstep.board.domain.board.entity.Board;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class BoardAddFormDto {

    private Long boardId;

    @NotBlank(message = "작성자는 공백일 수 없습니다.")
    private String writer;

    @NotBlank(message = "글 제목은 공백일 수 없습니다.")
    private String title;

    @NotBlank(message = "글 내용은 공백일 수 없습니다.")
    private String contents;

    @Pattern(regexp = "[0-9]{4}", message = "비밀번호는 숫자조합으로 4자리 입력하셔야 합니다.")
    private String password;

    public Board toEntity() {
        Board board = Board.builder()
                .id(boardId)
                .writer(writer)
                .title(title)
                .contents(contents)
                .password(password)
                .build();
        return board;
    }


}
