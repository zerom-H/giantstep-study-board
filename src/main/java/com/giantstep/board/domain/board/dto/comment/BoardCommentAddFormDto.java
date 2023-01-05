package com.giantstep.board.domain.board.dto.comment;

import com.giantstep.board.domain.board.entity.Board;
import com.giantstep.board.domain.board.entity.BoardComment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardCommentAddFormDto {

    private String addBoardCommentWriter;
    private String addBoardCommentPassword;
    private String addBoardCommentContents;
    private Board board;

    public BoardComment toEntity() {
        BoardComment boardComment = BoardComment.createBoardComment()
                .boardCommentWriter(addBoardCommentWriter)
                .boardCommentContents(addBoardCommentContents)
                .boardCommentPassword(addBoardCommentPassword)
                .board(board)
                .build();
        return boardComment;
    }

}
