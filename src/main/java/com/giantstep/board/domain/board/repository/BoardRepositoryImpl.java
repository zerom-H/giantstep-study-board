package com.giantstep.board.domain.board.repository;

import com.giantstep.board.domain.board.dto.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;
import static com.giantstep.board.domain.board.entity.QBoard.*;

public class BoardRepositoryImpl implements BoardRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager em) { this.queryFactory = new JPAQueryFactory(em); }

    @Override
    public List<BoardListDto> findAllByBoardListDto() {

        return queryFactory
                .select(new QBoardListDto(
                        board.id,
                        board.writer,
                        board.title,
                        board.updateDate
                ))
                .from(board)
                .orderBy(board.updateDate.desc())
                .fetch();
    }

    @Override
    public BoardOneDetailDto findByBoardOneDetailDto(Long boardId) {
        return queryFactory
                .select(new QBoardOneDetailDto(
                        board.id,
                        board.writer,
                        board.title,
                        board.contents,
                        board.password,
                        board.updateDate
                ))
                .from(board)
                .where(board.id.eq(boardId))
                .fetchOne();
    }

    @Override
    public Long checkBoardPwd(BoardUpdateCheckPwdCondition boardUpdateCheckPwdCondition) {
        return queryFactory
                .select(board.id.count()
                )
                .from(board)
                .where(
                        boardIdEq(boardUpdateCheckPwdCondition.getBoardId()),
                        boardPasswordEq(boardUpdateCheckPwdCondition.getBoardPassword())
                )
                .fetchOne();
    }

    private BooleanExpression boardIdEq(Integer boardId) {
        return hasText(String.valueOf(boardId)) ? board.id.eq(Long.valueOf(boardId)) : null;
    }

    private BooleanExpression boardPasswordEq(String boardPassword) {
        return hasText(boardPassword) ? board.password.eq(boardPassword) : null;
    }
}
