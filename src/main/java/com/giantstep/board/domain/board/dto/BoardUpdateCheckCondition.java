package com.giantstep.board.domain.board.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardUpdateCheckCondition {
    private Integer boardId;
    private String boardPassword;
}
