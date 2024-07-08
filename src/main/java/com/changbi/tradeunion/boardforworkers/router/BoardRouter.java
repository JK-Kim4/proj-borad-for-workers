package com.changbi.tradeunion.boardforworkers.router;

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
    public String boardListPage(
            @PathVariable(name = "boardId") Long boardId,
            Model model) {
        model.addAttribute("boardId", boardId);
        return "contents/board/list";
    }
}