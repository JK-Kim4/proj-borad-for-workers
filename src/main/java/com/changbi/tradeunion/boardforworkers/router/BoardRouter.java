package com.changbi.tradeunion.boardforworkers.router;

import com.changbi.tradeunion.boardforworkers.board.application.BoardService;
import com.changbi.tradeunion.boardforworkers.board.presentation.dto.BoardDetailDto;
import com.changbi.tradeunion.boardforworkers.common.utility.EnumUtility;
import com.changbi.tradeunion.boardforworkers.member.presentation.dto.SessionMember;
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

    private final BoardService boardService;

    @GetMapping("/{boardId}/post/list")
    public String postListPage(
            @PathVariable(name = "boardId") Long boardId,
            Model model) {
        model.addAttribute("boardId", boardId);
        return "contents/post/list";
    }

    /*사내 게시판 || 부서 게시판 이동*/
    @GetMapping("/private/{type}/{boardId}/post/list")
    public String privateListPage(
            @PathVariable(name = "boardId") Long boardId,
            @PathVariable(name = "type") String  type,
            Model model){

        model.addAttribute("boardId", boardId);
        return "contents/post/list";
    }

    @GetMapping("/{boardId}/post/save")
    public String postSavePage(
            @PathVariable(name = "boardId") Long boardId,
            HttpSession session,
            Model model, HttpServletRequest request) {

        //쓰기 권한 확인
        SessionMember member = (SessionMember) session.getAttribute("member");
        BoardDetailDto boardDetailDto = boardService.findById(boardId);

        if(EnumUtility.isQualifiedRole(member.getRole(), boardDetailDto.getWriteRole())){
            model.addAttribute("boardId", boardId);
            return "contents/post/save";
        }else{
            return "error/403";
        }
    }

    @GetMapping("/{boardId}/post/detail/{postId}")
    public String postDetailPage(
            @PathVariable(name = "postId") Long postId,
            @PathVariable(name = "boardId") Long boardId,
            HttpSession session,
            Model model) {

        model.addAttribute("postId", postId);
        return "contents/post/detail";
    }

    @GetMapping("/{boardId}/post/update/{postId}")
    public String postUpdatePage(
            @PathVariable(name = "postId") Long postId,
            @PathVariable(name = "boardId") Long boardId,
            HttpSession session, Model model) {

        //글쓴이 로그인 여부 확인
        SessionMember member = (SessionMember) session.getAttribute("member");
        Long authorId = boardService.findAuthorIdByPostId(postId);

        if(member.getMemberId().equals(authorId)){
            model.addAttribute("postId", postId);
            model.addAttribute("boardId", boardId);
            return "contents/post/update";
        }else{
            return "error/403";
        }
    }


}
