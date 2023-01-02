package com.giantstep.board.domain.board.repository.comment;

import com.giantstep.board.domain.board.entity.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long>, BoardCommentRepositoryCustom, QuerydslPredicateExecutor<BoardComment> {
}
