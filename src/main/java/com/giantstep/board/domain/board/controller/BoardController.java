package com.giantstep.board.domain.board.controller;

import com.giantstep.board.domain.board.controller.dto.BoardAddFormDto;
import com.giantstep.board.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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

        boardService.saveBoard(boardAddFormDto);
        return "board/boardList";
    }

}
