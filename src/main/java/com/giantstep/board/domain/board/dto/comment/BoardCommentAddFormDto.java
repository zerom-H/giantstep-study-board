package com.giantstep.board.domain.board.dto.comment;

import com.giantstep.board.domain.board.entity.Board;
import com.giantstep.board.domain.board.entity.BoardComment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardCommentAddFormDto {

    private String boardCommentWriter;
    private String boardCommentPassword;
    private String boardCommentContents;
    private Board board;

    public BoardComment toEntity() {
        BoardComment boardComment = BoardComment.createBoardComment()
                .boardCommentWriter(boardCommentWriter)
                .boardCommentContents(boardCommentContents)
                .boardCommentPassword(boardCommentPassword)
                .board(board)
                .build();
        return boardComment;
    }

}
