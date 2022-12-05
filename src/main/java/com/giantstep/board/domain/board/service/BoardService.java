package com.giantstep.board.domain.board.service;

import com.giantstep.board.domain.board.dto.BoardDeleteCheckCondition;
import com.giantstep.board.domain.board.dto.BoardListDto;
import com.giantstep.board.domain.board.dto.BoardOneDetailDto;
import com.giantstep.board.domain.board.dto.BoardUpdateCheckCondition;
import com.giantstep.board.domain.board.entity.Board;
import com.giantstep.board.domain.board.repository.BoardRepository;
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

    @Transactional
    public Long saveBoard(Board board) {
        return boardRepository.save(board).getId();
    }

    public List<BoardListDto> findAllBoardList() {
        return boardRepository.findAllByBoardListDto();
    }

    public Page<BoardListDto> findAllBoardListPaging(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber()-1);
        pageable = PageRequest.of(page, pageable.getPageSize());
        return boardRepository.findAllByBoardListDtoAddPaging(pageable);
    }

    public BoardOneDetailDto findByBoardId(Long boardId) {
        return boardRepository.findByBoardOneDetailDto(boardId);
    }

    @Transactional
    public Long updateBoard(Board updateBoard) {

        Board findUpdateBoard = boardRepository.findById(updateBoard.getId()).get().updateBoardOne(updateBoard);
        return findUpdateBoard.getId();
    }

    public Long checkUpdateBoardPwd(BoardUpdateCheckCondition boardUpdateCheckCondition) {
        return boardRepository.checkBoardPwd(boardUpdateCheckCondition);
    }

    public Long checkDeleteBoardCondition(BoardDeleteCheckCondition boardDeleteCheckCondition) {
        return boardRepository.checkDeleteBoardCondition(boardDeleteCheckCondition);
    }

    @Transactional
    public void deleteOneBoard(Board deleteBoard) {
        boardRepository.findById(deleteBoard.getId()).get().deleteBoardOne(deleteBoard);
    }

}
