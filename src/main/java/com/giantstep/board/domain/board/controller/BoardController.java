package com.giantstep.board.domain.board.controller;

import com.giantstep.board.domain.board.controller.dto.BoardAddFormDto;
import com.giantstep.board.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/giantstep-study/board")
public class BoardController {

    private final BoardService boardService;

    /** 게시 물 작성 */
    @GetMapping("/boardAddForm")
    public String boardAddForm(@ModelAttribute("BoardAddFormDto") BoardAddFormDto form){
        return "board/boardAddForm";
    }

    /** 게시 물 작성 완료 */
    @PostMapping("/boardAddForm")
    public String boardwriteDone(@ModelAttribute BoardAddFormDto boardAddFormDto) {
        boardService.saveBoard(boardAddFormDto);
        return "redirect:/giantstep-study/board";
    }

}
