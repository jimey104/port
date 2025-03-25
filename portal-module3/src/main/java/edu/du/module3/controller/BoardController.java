package edu.du.module3.controller;

import edu.du.module3.dto.BoardDTO;
import edu.du.module3.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 게시판 컨트롤러
 * 클라이언트 요청을 처리하고 뷰를 반환함.
 */
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService; // 게시글 서비스

    /**
     * 게시글 목록 페이지
     */
    @GetMapping
    public String list(Model model) {
        List<BoardDTO> boardList = boardService.getAllBoards();
        model.addAttribute("boardList", boardList);
        return "board/list";
    }

    /**
     * 게시글 상세 페이지
     */
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        BoardDTO board = boardService.getBoardById(id);
        model.addAttribute("board", board);
        return "board/detail";
    }

    /**
     * 게시글 작성 페이지
     */
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("board", new BoardDTO());
        return "board/form";
    }

    /**
     * 게시글 수정 페이지
     */
    @GetMapping("/edit/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        BoardDTO board = boardService.getBoardById(id);
        model.addAttribute("board", board);
        return "board/form";
    }

    /**
     * 게시글 저장 처리
     */
    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) {
        boardService.saveBoard(boardDTO);
        return "redirect:/board";
    }

    @GetMapping("/delete/{id}")
    public String deleteForm(@PathVariable Long id) {
       boardService.deletedById(id);
        return "redirect:/board";
    }

}
