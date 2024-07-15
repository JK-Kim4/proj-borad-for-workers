package com.changbi.tradeunion.boardforworkers.router;

import com.changbi.tradeunion.boardforworkers.board.application.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminRouter {

    private final BoardService boardService;

    @GetMapping(value = {"", "/", "/index"})
    public String adminIndexPage() {
        return "admin/index";
    }

    @GetMapping("/member/list")
    public String memberListPage(){
        return "admin/member/list";
    }

    @GetMapping("/save")
    public String adminSavePage(){
        return "admin/save";
    }

    @GetMapping("/pre-member/list")
    public String preMemberListPage(){
        return "admin/member/pre-member-list";
    }

    @GetMapping("/board/list")
    public String boardListPage(){
        return "admin/board/list";
    }

    @GetMapping("/board/save")
    public String boardSavePage(){
        return "admin/board/save";
    }

    @GetMapping("/board/detail/{boardId}")
    public String boardDetailPage(
            @PathVariable(name = "boardId") Long boardId,
            Model model){
        model.addAttribute("boardId", boardId);
        return "admin/board/detail";
    }

    @GetMapping("/board/{boardId}/post/list")
    public String boardPostListPage(
            @PathVariable(name = "boardId") Long boardId,
            Model model){

        model.addAttribute("board", boardService.findById(boardId));
        return "admin/board/post/list";
    }

    @GetMapping("/board/post/detail/{postId}")
    public String boardPostDetailPage(
            @PathVariable(name = "postId") Long postId,
            Model model){

        model.addAttribute("postId", postId);
        return "admin/board/post/detail";
    }
}
