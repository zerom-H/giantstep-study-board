package com.giantstep.board.domain.board.controller;

import com.giantstep.board.domain.board.dto.BoardAddFormDto;
import com.giantstep.board.domain.board.dto.BoardListDto;
import com.giantstep.board.domain.board.dto.BoardOneDetailDto;
import com.giantstep.board.domain.board.entity.Board;
import com.giantstep.board.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/giantstep-study/board")
public class BoardController {

    private final BoardService boardService;

    /** 게시 물 작성 */
    @GetMapping("/boardAddForm")
    public String boardAddForm(@ModelAttribute("board") BoardAddFormDto form){
        return "board/boardAddForm";
    }

    /** 게시 물 작성 완료 */
    @PostMapping("/boardAddForm")
    public String boardWriteDone(@Valid @ModelAttribute("board") BoardAddFormDto boardAddFormDto, BindingResult bindingResult) {

        //검증 실패하면 다시 작성 폼으로
        if (bindingResult.hasErrors()) {
            log.info("errors = {} ", bindingResult);
            return "board/boardAddForm";
        }
        boardService.saveBoard(boardAddFormDto.toEntity());
        return "board/boardList";
    }

    /** 게시 물 리스트 조회 */
    @GetMapping
    public String boardListView(Model model) {
        model.addAttribute("boardList", boardService.findAllBoardList().stream()
                .map(board -> new BoardListDto(
                        board.getId(),
                        board.getWriter(),
                        board.getTitle(),
                        board.getCreateDate()
                ))
                .collect(Collectors.toList()));
        return "board/boardList";
    }

    /** 게시 물 단건 상세조회 */
    @GetMapping("/{boardId}/detail")
    public String boardOneDetailView(@PathVariable("boardId") Long boardId,
                                     Model model) {
        model.addAttribute("boardOneDetail", new BoardOneDetailDto(boardService.findByBoardId(boardId)));
        return "board/boardOneDetail";
    }



}
