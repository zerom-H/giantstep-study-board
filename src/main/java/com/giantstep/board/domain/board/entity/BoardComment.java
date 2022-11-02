package com.giantstep.board.domain.board.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board_comment")
@Entity
public class BoardComment {

    /** 댓글 번호 */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_no")
    private Long no;

    /** 댓글 작성자 */
    @Column(name = "comment_writer", nullable = false)
    private String writer;

    /** 댓글 내용 */
    @Column(name = "comment_contents", columnDefinition = "text", nullable = false)
    private String contents;

    /** 댓글 비밀번호 */
    @Column(name = "comment_password", nullable = false)
    private int password;

    /** 댓글의 게시 물 번호 */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "board_no")
    private Board board;

}
