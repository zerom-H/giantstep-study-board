package com.giantstep.board.domain.board.controller;

import com.giantstep.board.domain.board.dto.board.*;
import com.giantstep.board.domain.board.service.BoardService;
import com.giantstep.board.utils.UtilsMethod;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("giantstep-study/board")
public class BoardController extends UtilsMethod {

    private final BoardService boardService;

    /** 게시 물 작성 */
    @GetMapping("boardAddForm")
    public String boardAddForm(@ModelAttribute("board") BoardAddFormDto form){
        return "board/boardAddForm";
    }

    /** 게시 물 작성 완료 */
    @PostMapping("boardAddForm")
    public String boardWriteDone(@Valid @ModelAttribute("board") BoardAddFormDto boardAddFormDto
            , BindingResult bindingResult, Model model) {

        //검증 실패하면 다시 작성 폼으로
        if (bindingResult.hasErrors()) {
            log.info("errors = {} ", bindingResult);
            String message = "수정한 폼의 유효성 검사가 실패했습니다. 다시 입력하세요!";
            model.addAttribute("message", message);
            return "board/boardAddForm";
        }
        boardService.saveBoard(boardAddFormDto.toEntity());
        return showMessageAndRedirectUri("게시물 등록이 완료되었습니다.","listPaging", model);
    }

    /** 게시 물 리스트 조회 */
    @GetMapping("list")
    public String boardListView(Model model) {
        model.addAttribute("boardAllList" ,boardService.findAllBoardList());
        return "board/boardList";
    }

    /** 게시 물 리스트 조회 Paging 처리, 검색기능 추가 한 버전 */
    @GetMapping("listPaging")
    public String boardListViewPaging(Model model, @PageableDefault(size = 10) Pageable pageable,
                                      @ModelAttribute(name = "searchCondition") BoardSearchCondition boardSearchCondition) {
        model.addAttribute("boardAllListPaging", boardService.findAllBoardListPaging(pageable, boardSearchCondition));
        return "board/boardListPaging";
    }

    /** 게시 물 단건 상세조회 */
    @GetMapping("{boardId}/detail")
    public String boardOneDetailView(@PathVariable("boardId") Long boardId,
                                     Model model) {
        model.addAttribute("boardOneDetail", boardService.findByBoardId(boardId));
        return "board/boardOneDetail";
    }

    /** 게시 물 수정 */
    @GetMapping("{boardId}/update")
    public String boardOneUpdate(@PathVariable("boardId") Long boardId, Model model) {
        model.addAttribute("boardOneUpdate", boardService.findByBoardId(boardId));
        return "board/boardUpdateForm";
    }

    @GetMapping("{boardId}/checkUpdateBoardRequest")
    public String checkPassWordUpdateBoard(@PathVariable("boardId") Long boardId, Model model,
                                           @ModelAttribute("checkUpdateBoardRequest") BoardUpdateCheckRequest boardUpdateCheckRequest) {
        model.addAttribute("checkBoardId", boardService.findByBoardId(boardId).getBoardId());
        return "board/boardUpdateCheck";
    }
    @PostMapping("{boardId}/checkUpdateBoardRequest")
    public String checkPassWordUpdateBoardDone(Model model,
            @Valid @ModelAttribute("checkUpdateBoardRequest") BoardUpdateCheckRequest boardUpdateCheckRequest) {

        boolean checkBoardRequest = boardService.checkUpdateBoardRequest(boardUpdateCheckRequest.getBoardId(), boardUpdateCheckRequest.getBoardPassword());
        if (checkBoardRequest == true){
            return showMessageAndRedirectUri("비밀번호 검증에 성공했습니다. 다음 화면으로 이동합니다.", "update", model);
        }
        else {
            return showMessageAndRedirectUri("비밀번호 검증에 실패했습니다. 다시 입력하세요!", "checkUpdateBoardRequest", model);
        }
    }

    @PostMapping("{boardId}/update")
    public String boardOneUpdateDone(@Valid @ModelAttribute("boardOneUpdate") BoardUpdateFormDto boardUpdateFormDto,
                                    BindingResult bindingResult, Model model) {
        //검증 실패하면 다시 작성 폼으로
        if (bindingResult.hasErrors()) {
            log.info("errors = {} ", bindingResult);
            String message = "수정한 폼의 유효성 검사가 실패했습니다. 다시 입력하세요!";
            model.addAttribute("message", message);
            return "board/boardUpdateForm";
        }
        boardService.updateBoard(boardUpdateFormDto.toEntity());
        return showMessageAndRedirectUri("게시 물 수정에 성공했습니다.", "detail", model);
    }


    /** 삭제하기 */
    @GetMapping("{boardId}/checkDeleteBoardRequest")
    public String checkDeleteBoardRequest(@PathVariable("boardId") Long boardId, Model model,
                                            @ModelAttribute("boardDeleteCheckRequest") BoardDeleteCheckRequest boardDeleteCheckRequest) {
        model.addAttribute("checkBoardId", boardService.findByBoardId(boardId).getBoardId());
        return "board/boardDeleteCheck";
    }

    @PostMapping("checkDeleteBoardRequest")
    public String checkDeleteBoardRequestDone(Model model, @ModelAttribute("boardDeleteCheckRequest") BoardDeleteCheckRequest boardDeleteCheckRequest) {

        boolean checkDeleteBoardRequest = boardService.checkDeleteBoardRequest(boardDeleteCheckRequest.getBoardId(), boardDeleteCheckRequest.getBoardPassword());
        if (checkDeleteBoardRequest == true) {
            boardService.deleteOneBoard(boardDeleteCheckRequest.getBoardId());
            return showMessageAndRedirectUri("글 삭제에 성공했습니다.", "listPaging", model);
        }
        else {
            return showMessageAndRedirectUri("삭제할 게시 물 검증에 실패했습니다. 다시 확인하세요.",
                    boardDeleteCheckRequest.getBoardId() + "/checkDeleteBoardRequest", model);
        }
    }

}
