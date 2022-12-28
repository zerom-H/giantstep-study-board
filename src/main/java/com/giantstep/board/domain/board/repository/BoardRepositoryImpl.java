package com.giantstep.board.domain.board.repository;

import com.giantstep.board.domain.board.dto.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalTime;
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
                        writerContains(boardSearchCondition.getWriter()),
                        titleContains(boardSearchCondition.getTitle()),
                        betweenDate(boardSearchCondition.getStartDate(), boardSearchCondition.getEndDate())
                )
                .orderBy(board.updateDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> totalBoardListCount = queryFactory
                .select(board.count())
                .from(board)
                .where(
                        writerContains(boardSearchCondition.getWriter()),
                        titleContains(boardSearchCondition.getTitle()),
                        betweenDate(boardSearchCondition.getStartDate(), boardSearchCondition.getEndDate()))
                ;

        return PageableExecutionUtils.getPage(boardListDtoPagingList, pageable, totalBoardListCount::fetchOne);
    }

    private BooleanExpression writerContains(String writer) {
        return hasText(writer) ? board.writer.contains(writer) : null;
    }

    private BooleanExpression titleContains(String title) {
        return hasText(title) ? board.title.contains(title) : null;
    }

    private BooleanExpression betweenDate(LocalDate startDate, LocalDate endDate){

        // 급한대로 최소한의 기간 검색의 유효성 검사 여기에 넣었습니다.
        if (startDate == null || endDate == null) {
            return null;
        }
        else {
            // atStartOfDay() : 날짜 + 00:00:00.00000000을 의미
            // .atTime(LocalTime.MAX) : 날짜 + 23:59:59.99999999를 의미
            return board.updateDate.between(startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));
        }
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
