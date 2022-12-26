package com.giantstep.board.domain.board.dto.board;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardUpdateCheckRequest {
    private Long boardId;
    private String boardPassword;
}
