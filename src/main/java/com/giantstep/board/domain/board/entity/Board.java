package com.giantstep.board.domain.board.entity;

import com.giantstep.board.global.audit.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board extends BaseTimeEntity {

    /** 게시 글 번호 */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /** 작성자 */
    private String writer;

    /** 글 제목 */
    private String title;

    /** 글 내용 */
    private String contents;

    /** 글 비밀번호 */
    private String password;

    @OneToMany(mappedBy = "board")
    private List<BoardComment> boardComments = new ArrayList<>();

    @Builder
    public Board(Long id, String writer, String title, String contents, String password) {
        this.id =  id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.password = password;
    }

    public void updateBoardOne(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

}
