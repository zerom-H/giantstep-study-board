package com.giantstep.board.domain.board.dto.comment;

import com.giantstep.board.domain.board.entity.BoardComment;
import lombok.Data;

@Data
public class BoardCommentUpdateFormDto {
    private Long updateBoardCommentID;
    private String boardCommentContents;
    private String boardCommentPassword;

    public BoardComment toEntity() {
        BoardComment boardComment = BoardComment.createBoardComment()
                .boardCommentId(updateBoardCommentID)
                .boardCommentContents(boardCommentContents)
                .build();
        return boardComment;
    }
}
