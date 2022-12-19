package com.giantstep.board.domain.board.repository;

import com.giantstep.board.domain.board.dto.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.giantstep.board.domain.board.entity.QBoard.*;
import static org.springframework.util.StringUtils.*;

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
                .where(board.deletedYn.eq("N"))
                .orderBy(board.updateDate.desc())
                .fetch();
    }

    @Override
    public Page<BoardListDto> findAllByBoardListDtoAddPaging(Pageable pageable, BoardSearchCondition boardSearchCondition) {

        List<BoardListDto> boardListDtoPagingList = queryFactory
                .select(new QBoardListDto(
                        board.id,
                        board.writer,
                        board.title,
                        board.updateDate
                ))
                .from(board)
                .where(
                        board.deletedYn.eq("N"),
                        searchTypeCheck(boardSearchCondition)
                )
                .orderBy(board.updateDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalBoardListCount = queryFactory
                .select(board.count())
                .from(board)
                .where(searchTypeCheck(boardSearchCondition))
                .fetchOne();

        return new PageImpl<>(boardListDtoPagingList, pageable, totalBoardListCount);
    }

    private BooleanExpression searchTypeCheck(BoardSearchCondition boardSearchCondition) {
        if ( "writer".equals(boardSearchCondition.getType()) ){
            return writerContains(boardSearchCondition.getKeyWord());
        }
        else if ( "title".equals(boardSearchCondition.getType()) ){
            return titleContains(boardSearchCondition.getKeyWord());
        }
        else {
            return null;
        }
    }

    private BooleanExpression writerContains(String keyWord) {
        return hasText(keyWord) ? board.writer.contains(keyWord) : null;
    }

    private BooleanExpression titleContains(String keyWord) {
        return hasText(keyWord) ? board.title.contains(keyWord) : null;
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
                .where(board.id.eq(boardId),
                        board.deletedYn.eq("N")
                )
                .fetchOne();
    }
}
