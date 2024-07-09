package com.changbi.tradeunion.boardforworkers.common.interceptor;

import com.changbi.tradeunion.boardforworkers.board.application.BoardServiceImpl;
import com.changbi.tradeunion.boardforworkers.board.presentation.dto.BoardListDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientHeaderMenuInterceptor implements HandlerInterceptor {

    private final BoardServiceImpl boardService;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        HttpSession session = request.getSession();
        List<BoardListDto> headers = (List<BoardListDto>) session.getAttribute("headers");

        if(headers != null && headers.size() > 0) {
            return true;
        }else{
            headers = boardService.findBoards();
            session.setAttribute("headers", headers);
            return true;
        }
    }
}
