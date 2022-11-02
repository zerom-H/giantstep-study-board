package com.giantstep.board.domain.board.entity;

import com.giantstep.board.global.audit.BaseTimeEntity;
import lombok.AccessLevel;
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
    private int password;

    /** 댓글의 게시 물 번호 */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Board board;

}
