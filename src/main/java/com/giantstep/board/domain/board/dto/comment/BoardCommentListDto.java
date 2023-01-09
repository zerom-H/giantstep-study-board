package com.giantstep.board.domain.board.dto.comment;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardCommentListDto {

    private Long boardCommentId;
    private String boardCommentWriter;
    private String boardCommentContents;
    private Long boardId;
    private String boardCommentPassword;
    private LocalDateTime boardCommentUpdateDate;

    @QueryProjection
    public BoardCommentListDto(Long boardCommentId, String boardCommentWriter, String boardCommentContents,
                               Long boardId, String boardCommentPassword, LocalDateTime boardCommentUpdateDate) {
        this.boardCommentId = boardCommentId;
        this.boardCommentWriter = boardCommentWriter;
        this.boardCommentContents = boardCommentContents;
        this.boardId = boardId;
        this.boardCommentPassword = boardCommentPassword;
        this.boardCommentUpdateDate = boardCommentUpdateDate;
    }
}
