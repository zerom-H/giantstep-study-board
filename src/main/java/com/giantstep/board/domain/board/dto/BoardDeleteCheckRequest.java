package com.giantstep.board.domain.board.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardDeleteCheckRequest {
    private Long boardId;
    private String boardPassword;

}
