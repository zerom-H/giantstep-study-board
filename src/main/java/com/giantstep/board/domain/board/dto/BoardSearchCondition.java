package com.giantstep.board.domain.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardSearchCondition {
    /** 검색 키워드 */
    private String keyWord;

    /** 검색 타입(writer, title) */
    private String type;

    /** 페이지당 보여줄 게시물 수 */
    private int size;
}
