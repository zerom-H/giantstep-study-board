package com.giantstep.board.domain.board.repository.comment;

import com.giantstep.board.domain.board.dto.comment.BoardCommentListDto;
import com.giantstep.board.domain.board.dto.comment.QBoardCommentListDto;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.giantstep.board.domain.board.entity.QBoardComment.*;

public class BoardCommentRepositoryImpl implements BoardCommentRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public BoardCommentRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<BoardCommentListDto> findAllByBoardCommentListDtoAddPaging(Pageable pageable, Long boardId) {

        List<BoardCommentListDto> boardCommentListDtoList = queryFactory
                .select(new QBoardCommentListDto(
                        boardComment.id,
                        boardComment.writer,
                        boardComment.contents,
                        boardComment.board.id,
                        boardComment.password,
                        boardComment.updateDate
                ))
                .from(boardComment)
                .orderBy(boardComment.updateDate.desc())
                .where(boardComment.board.id.eq(boardId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> boardCommentCount = queryFactory
                .select(boardComment.count())
                .from(boardComment)
                .where(boardComment.board.id.eq(boardId));

        return PageableExecutionUtils.getPage(boardCommentListDtoList, pageable, boardCommentCount::fetchOne);
    }
}
