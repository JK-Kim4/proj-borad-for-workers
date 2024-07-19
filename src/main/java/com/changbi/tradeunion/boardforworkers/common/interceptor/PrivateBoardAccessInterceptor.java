package com.changbi.tradeunion.boardforworkers.common.interceptor;

import com.changbi.tradeunion.boardforworkers.board.application.BoardService;
import com.changbi.tradeunion.boardforworkers.board.exception.PrivateBoardAuthorizationException;
import com.changbi.tradeunion.boardforworkers.board.presentation.dto.BoardDetailDto;
import com.changbi.tradeunion.boardforworkers.common.CommonValues;
import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.Role;
import com.changbi.tradeunion.boardforworkers.common.utility.EnumUtility;
import com.changbi.tradeunion.boardforworkers.member.presentation.dto.SessionMember;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class PrivateBoardAccessInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(PrivateBoardAccessInterceptor.class);
    private final BoardService boardService;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        boolean result = false;
        HttpSession session = request.getSession();
        SessionMember member = (SessionMember) session.getAttribute("member");

        if (member.getRole().equals(Role.ADMIN.name()) || member.getRole().equals(Role.SUPER_ADMIN.name())){
            return true;
        }

        String boardType = EnumUtility.extractPrivateBoardType(request);
        Long boardId = EnumUtility.extractPrivateBoardId(request);

        BoardDetailDto detailDto = boardService.findById(boardId);

        if(CommonValues.BOARD_TYPE_COMPANY.equals(boardType)) result = EnumUtility.companyBoardSessionCheck(member.getCompany(), detailDto);

        if(CommonValues.BOARD_TYPE_DEPARTMENT.equals(boardType)) result = EnumUtility.departmentBoardSessionCheck(member.getDepartment(), detailDto);

        if(result){
            return result;
        }else{
            throw new PrivateBoardAuthorizationException(CommonValues.RESULT_MESSAGE_FAIL_BOARD_AUTHORIZATION, boardId);
        }
    }


    private boolean validatePostListPageAuthorization(
            HttpServletRequest request,
            SessionMember member){

        return false;
    }

    private boolean validatePostSavePageAuthorization(
            HttpServletRequest request,
            SessionMember member){

        return false;
    }


}
