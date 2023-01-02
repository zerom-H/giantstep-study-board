package com.giantstep.board.domain.board.dto.board;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardSearchCondition {

    /** 검색 writer, title*/
    private String writer = "";
    private String title = "";

    /** 페이지당 보여줄 게시물 수 */
    private int size;
}
