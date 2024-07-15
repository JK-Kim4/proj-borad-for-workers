package com.changbi.tradeunion.boardforworkers.router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardRouter {

    @GetMapping("/{boardId}/post/list")
    public String postListPage(
            @PathVariable(name = "boardId") Long boardId,
            Model model) {
        model.addAttribute("boardId", boardId);
        return "contents/post/list";
    }

    @GetMapping("/{boardId}/post/save")
    public String postSavePage(
            @PathVariable(name = "boardId") Long boardId,
            Model model, HttpServletRequest request) throws IllegalAccessException {

        HttpSession session = request.getSession();
        if(session.getAttribute("member") == null){
            throw new IllegalAccessException("잘못된 접근입니다. 로그인 후 이용해주세요.");
        }

        model.addAttribute("boardId", boardId);
        return "contents/post/save";
    }

    @GetMapping("/post/detail/{postId}")
    public String postDetailPage(
            @PathVariable(name = "postId") Long postId,
            Model model) {

        model.addAttribute("postId", postId);
        return "contents/post/detail";
    }
}
