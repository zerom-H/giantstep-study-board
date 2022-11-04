package com.giantstep.board.domain.board.repository;

import com.giantstep.board.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
