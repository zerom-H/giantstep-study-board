package com.giantstep.board.domain.board.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardUpdateCheckRequest {
    private Integer boardId;
    private String boardPassword;
}
