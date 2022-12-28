package com.giantstep.board.domain.board.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter @Setter
public class BoardSearchCondition {

    /** 검색 writer, title*/
    private String writer = "";
    private String title = "";

    /** 페이지당 보여줄 게시물 수 */
    private int size;

    /** 수정 날짜 검색 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}
