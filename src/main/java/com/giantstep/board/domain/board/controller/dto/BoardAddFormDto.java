package com.giantstep.board.domain.board.controller.dto;

import com.giantstep.board.domain.board.entity.Board;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardAddFormDto {

    private Long id;
    private String writer;
    private String title;
    private String contents;
    private String password;

    private LocalDateTime createDate;

    public Board toEntity() {
        Board board = Board.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .contents(contents)
                .password(password)
                .build();
        return board;
    }


}
