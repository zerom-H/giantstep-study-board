package com.giantstep.board.domain.board.dto;

import com.giantstep.board.domain.board.constant.BoardStatus;
import com.giantstep.board.domain.board.entity.Board;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardDeleteCheckRequest {
    private Integer boardId;
    private String boardPassword;

    @Builder
    public BoardDeleteCheckRequest(Integer boardId, String boardPassword) {
        this.boardId = boardId;
        this.boardPassword = boardPassword;
    }

    /** 변경감지를 활용하여 해당 게시 물의 상태값을 DELETE 로 변경 */
    public Board toEntity() {
        Board board = Board.builder()
                .id(Long.valueOf(boardId))
                .boardStatus(BoardStatus.DELETE)
                .build();
        return board;
    }
}
