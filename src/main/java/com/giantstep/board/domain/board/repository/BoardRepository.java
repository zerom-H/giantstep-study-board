package com.giantstep.board.domain.board.repository;

import com.giantstep.board.domain.board.dto.BoardListDto;
import com.giantstep.board.domain.board.dto.BoardOneDetailDto;
import com.giantstep.board.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom, QuerydslPredicateExecutor<Board> {

//    @Query("select new com.giantstep.board.domain.board.dto.BoardListDto(" +
//            "b.id, b.writer, b.title, b.updateDate)" +
//            " from Board b order by b.updateDate desc")
//    List<BoardListDto> findAllByBoardListDto();

//    @Query("select new com.giantstep.board.domain.board.dto.BoardOneDetailDto(" +
//            "b.id, b.writer, b.title, b.contents, b.updateDate)" +
//            " from Board b where b.id = :boardId")
//    BoardOneDetailDto findByBoardOneDetailDto(@Param("boardId") Long boardId);

//    @Query("select count(b.id) from Board b where b.id= :checkBoardId and b.password= :checkBoardPassword")
//    Long checkBoardPwd(@Param("checkBoardId") Long boardId, @Param("checkBoardPassword") String boardPassword);

}
