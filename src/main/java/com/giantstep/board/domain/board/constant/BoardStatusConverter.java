package com.giantstep.board.domain.board.constant;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BoardStatusConverter implements AttributeConverter<BoardStatus, String> {

    @Override
    public String convertToDatabaseColumn(BoardStatus attribute) {
        return attribute.getCode();
    }

    @Override
    public BoardStatus convertToEntityAttribute(String dbData) {
        return BoardStatus.ofCode(dbData);
    }
}
