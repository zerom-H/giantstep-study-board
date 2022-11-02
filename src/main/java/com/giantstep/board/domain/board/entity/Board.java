package com.giantstep.board.domain.board.entity;

import com.giantstep.board.global.audit.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board")
@Entity
public class Board extends BaseTimeEntity {

    /** 게시 글 번호 */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;
    
    /** 작성자 */
    @Column(name = "board_writer", columnDefinition = "varchar(100)", nullable = false)
    private String writer;

    /** 글 제목 */
    @Column(name = "board_title", nullable = false)
    private String title;

    /** 글 내용 */
    @Column(name = "board_contents", columnDefinition = "text", nullable = false)
    private String contents;

    /** 글 비밀번호 */
    @Column(name = "board_password", nullable = false)
    private int password;

    @OneToMany(mappedBy = "board")
    private List<BoardComment> boardComments = new ArrayList<>();

}
