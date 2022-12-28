package com.giantstep.board.domain.board.service;

import com.giantstep.board.domain.board.dto.board.BoardListDto;
import com.giantstep.board.domain.board.dto.board.BoardOneDetailDto;
import com.giantstep.board.domain.board.dto.board.BoardOneDetailResponse;
import com.giantstep.board.domain.board.dto.board.BoardSearchCondition;
import com.giantstep.board.domain.board.dto.comment.BoardCommentListDto;
import com.giantstep.board.domain.board.entity.Board;
import com.giantstep.board.domain.board.repository.board.BoardRepository;
import com.giantstep.board.domain.board.repository.comment.BoardCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardCommentRepository boardCommentRepository;

    @Transactional
    public Long saveBoard(Board board) {
        return boardRepository.save(board).getId();
    }

    public List<BoardListDto> findAllBoardList() {
        return boardRepository.findAllByBoardListDto();
    }

    public Page<BoardListDto> findAllBoardListPaging(Pageable pageable, BoardSearchCondition boardSearchCondition) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber()-1);

        if (boardSearchCondition.getSize() == 0) {
            pageable = PageRequest.of(page, pageable.getPageSize());
        }
        else {
            pageable = PageRequest.of(page, boardSearchCondition.getSize());
        }

        return boardRepository.findAllByBoardListDtoAddPaging(pageable, boardSearchCondition);
    }

    public BoardOneDetailDto findByBoardId(Long boardId) {
        return boardRepository.findByBoardOneDetailDto(boardId);
    }
    public BoardOneDetailResponse findByBoardIdAndComment(Long boardId, Pageable pageable) {

        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber()-1);
        pageable = PageRequest.of(page, pageable.getPageSize());

        BoardOneDetailDto byBoardOneDetailDto = boardRepository.findByBoardOneDetailDto(boardId);

        Page<BoardCommentListDto> byBoardCommentListDtoAddPaging = boardCommentRepository.findAllByBoardCommentListDtoAddPaging(pageable, boardId);

        return new BoardOneDetailResponse(byBoardOneDetailDto, byBoardCommentListDtoAddPaging);
    }

    @Transactional
    public Long updateBoard(Board updateBoard) {

        Board findUpdateBoard = boardRepository.findById(updateBoard.getId()).get().updateBoardOne(updateBoard);
        return findUpdateBoard.getId();
    }

    public Boolean checkUpdateBoardRequest(Long updateBoardId, String updateBoardPassword) {
        return boardRepository.existsByIdAndPasswordAndDeletedYn(updateBoardId, updateBoardPassword, "N");
    }

    public Boolean checkDeleteBoardRequest(Long deleteBoardId, String deletedBoardPassword) {
        return boardRepository.existsByIdAndPasswordAndDeletedYn(deleteBoardId, deletedBoardPassword, "N");
    }

    @Transactional
    public void deleteOneBoard(Long deleteBoardId) {
        boardRepository.findById(deleteBoardId).get().deleteBoardOne();
    }

}
