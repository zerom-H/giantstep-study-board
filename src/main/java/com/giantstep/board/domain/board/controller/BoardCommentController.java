package com.giantstep.board.domain.board.controller;

import com.giantstep.board.domain.board.dto.comment.BoardCommentAddFormDto;
import com.giantstep.board.domain.board.dto.comment.BoardCommentUpdateFormDto;
import com.giantstep.board.domain.board.service.BoardCommentService;
import com.giantstep.board.utils.UtilsMethod;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("giantstep-study/board")
public class BoardCommentController extends UtilsMethod {

    private final BoardCommentService boardCommentService;

    @PostMapping("{boardId}/commentAdd")
    public String commentAddDone(@PathVariable("boardId") Long boardId, Model model,
                                 @ModelAttribute("boardComment")BoardCommentAddFormDto boardCommentAddFormDto){
        boardCommentService.saveBoardComment(boardId, boardCommentAddFormDto);
        return showMessageAndRedirectUri("댓글이 성공적으로 등록되었습니다.", "detail", model);
    }

    @PostMapping("{boardId}/updateBoardComment")
    public String commentUpdateDone(@PathVariable("boardId") Long boardId,
                                    @ModelAttribute("boardCommentUpdateForm")BoardCommentUpdateFormDto boardCommentUpdateFormDto) {
        boardCommentService.updateBoardComment(boardCommentUpdateFormDto.toEntity());
        return "redirect:detail";
    }

    @PostMapping("{boardId}/{boardCommentId}")
    public String commentDeleteDone(@PathVariable("boardId") Long boardId, @PathVariable("boardCommentId") Long boardCommentId) {
        boardCommentService.deleteBoardComment(boardCommentId);
        return "redirect:detail";
    }
}
