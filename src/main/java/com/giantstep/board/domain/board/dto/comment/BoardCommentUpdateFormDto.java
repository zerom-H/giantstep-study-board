package com.giantstep.board.domain.board.dto.comment;

import com.giantstep.board.domain.board.entity.BoardComment;
import lombok.Data;

@Data
public class BoardCommentUpdateFormDto {
    private String boardCommentContents;
    private String boardCommentPassword;

    public BoardComment toEntity() {
        BoardComment boardComment = BoardComment.createBoardComment()
                .boardCommentContents(boardCommentContents)
                .build();
        return boardComment;
    }
}
