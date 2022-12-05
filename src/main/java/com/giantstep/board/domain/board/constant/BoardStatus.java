package com.giantstep.board.domain.board.constant;

import lombok.Getter;

import java.util.Arrays;
import java.util.NoSuchElementException;

@Getter
public enum BoardStatus {

    ALIVE("작성", "1"),
    DELETE("삭제", "2");

    private String desc;
    private String code;

    BoardStatus(String desc, String code) {
        this.desc = desc;
        this.code = code;
    }

    public static BoardStatus ofCode(String code) {

        return Arrays.stream(BoardStatus.values())
                .filter(v -> v.getCode().equals(code))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException());
    }

}
