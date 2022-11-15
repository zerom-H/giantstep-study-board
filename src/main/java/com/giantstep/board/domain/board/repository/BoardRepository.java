package com.giantstep.board.domain.board.repository;

import com.giantstep.board.domain.board.dto.BoardListDto;
import com.giantstep.board.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("select new com.giantstep.board.domain.board.dto.BoardListDto(" +
            "b.id, b.writer, b.title, b.updateDate)" +
            " from Board b order by b.updateDate desc")
    List<BoardListDto> findAllByBoardListDto();

}
