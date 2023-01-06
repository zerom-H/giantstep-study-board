package com.giantstep.board.domain.board.entity;

import com.giantstep.board.global.audit.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class BoardComment extends BaseTimeEntity {

    /** 댓글 번호 */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 댓글 작성자 */
    private String writer;

    /** 댓글 내용 */
    private String contents;

    /** 댓글 비밀번호 */
    private String password;

    /** 댓글의 게시 물 번호 */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Board board;

    /** 댓글 상태 */
    private String deletedYn;

    @Builder(builderClassName = "createBoardComment", builderMethodName = "createBoardComment")
    public BoardComment(Long boardCommentId, String boardCommentWriter,
                        String boardCommentContents, String boardCommentPassword, Board board,
                        String deletedYn){
        this.id = boardCommentId;
        this.writer = boardCommentWriter;
        this.contents = boardCommentContents;
        this.password = boardCommentPassword;
        this.board = board;
        this.deletedYn = deletedYn;
    }

    public BoardComment updateBoardComment(BoardComment updateBoardComment) {
        this.contents = updateBoardComment.getContents();
        return updateBoardComment;
    }

    public void deleteBoardComment() {
        this.deletedYn = "Y";
    }

}
