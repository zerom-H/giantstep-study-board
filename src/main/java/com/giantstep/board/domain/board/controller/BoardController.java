package com.giantstep.board.domain.board.controller;

import com.giantstep.board.domain.board.dto.BoardAddFormDto;
import com.giantstep.board.domain.board.dto.BoardUpdateCheckPwdDto;
import com.giantstep.board.domain.board.dto.BoardUpdateFormDto;
import com.giantstep.board.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        boardService.saveBoard(boardAddFormDto.toEntity());
        return "board/boardList";
    }

    /** 게시 물 리스트 조회 */
    @GetMapping
    public String boardListView(Model model) {
        model.addAttribute("boardAllList" ,boardService.findAllBoardList());
        return "board/boardList";
    }

    /** 게시 물 단건 상세조회 */
    @GetMapping("/{boardId}/detail")
    public String boardOneDetailView(@PathVariable("boardId") Long boardId,
                                     Model model) {
        model.addAttribute("boardOneDetail", boardService.findByBoardId(boardId));
        return "board/boardOneDetail";
    }

    /** 게시 물 수정 */
    @GetMapping("/{boardId}/edite")
    public String boardOneEdite(@PathVariable("boardId") Long boardId, Model model) {
        model.addAttribute("boardOneUpdate", boardService.findByBoardId(boardId));
        return "board/boardUpdateForm";
    }

    @GetMapping("{boardId}/checkUpdateBoardPwd")
    public String CheckPassWordUpdateBoard(@PathVariable("boardId") Long boardId, Model model,
                                           @ModelAttribute("checkUpdateBoardForm")BoardUpdateCheckPwdDto boardUpdateCheckPwdDto) {
        model.addAttribute("checkBoardId", boardService.findByBoardId(boardId).getBoardId());
        return "board/boardUpdatePwdCheck";
    }
    @PostMapping("{boardId}/checkUpdateBoardPwd")
    public String CheckPassWordUpdateBoardDone(
            @Valid @ModelAttribute("checkUpdateBoardForm")BoardUpdateCheckPwdDto boardUpdateCheckPwdDto,
            BindingResult bindingResult) {

        //검증 실패하면 다시 검증 폼으로
        if (bindingResult.hasErrors()) {
            log.info("errors = {} ", bindingResult);
            return "board/boardUpdatePwdCheck";
        }

        Long checkBoardPwd = boardService.checkUpdateBoardPwd(boardUpdateCheckPwdDto);
        if (checkBoardPwd == 1){
            return "redirect:/giantstep-study/board/{boardId}/edite";
        }
        else {
            return "board/boardUpdatePwdCheck";
        }
    }

    /** 변경 된 부분만 수정하기 */
    @PostMapping("/{boardId}/edite")
    public String boardOneEditeDone(@Valid @ModelAttribute("boardOneUpdate") BoardUpdateFormDto boardUpdateFormDto,
                                    BindingResult bindingResult) {
        //검증 실패하면 다시 작성 폼으로
        if (bindingResult.hasErrors()) {
            log.info("errors = {} ", bindingResult);
            return "board/boardUpdateForm";
        }
        boardService.updateBoard(boardUpdateFormDto);
        return "redirect:/giantstep-study/board";
    }



}
