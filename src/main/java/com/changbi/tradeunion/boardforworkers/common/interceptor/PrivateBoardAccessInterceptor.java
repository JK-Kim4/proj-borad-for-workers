package com.changbi.tradeunion.boardforworkers.common.interceptor;

import com.changbi.tradeunion.boardforworkers.board.application.BoardService;
import com.changbi.tradeunion.boardforworkers.board.exception.PrivateBoardAuthorizationException;
import com.changbi.tradeunion.boardforworkers.board.presentation.dto.BoardDetailDto;
import com.changbi.tradeunion.boardforworkers.common.CommonValues;
import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.Company;
import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.Department;
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

        String boardType = this.extractPrivateBoardType(request);
        Long boardId = this.extractPrivateBoardId(request);

        if(CommonValues.BOARD_TYPE_COMPANY.equals(boardType)) result = this.companyBoardSessionCheck(member.getCompany(), boardId);

        if(CommonValues.BOARD_TYPE_DEPARTMENT.equals(boardType)) result = this.departmentBoardSessionCheck(member.getDepartment(), boardId);

        if(result){
            return result;
        }else{
            throw new PrivateBoardAuthorizationException(CommonValues.RESULT_MESSAGE_FAIL_BOARD_AUTHORIZATION, boardId);
        }

    }

    private Long extractPrivateBoardId(HttpServletRequest request) {
        String []requestUri = request.getRequestURI().trim().split("/");
        Long boardId = Long.valueOf(requestUri[4]);

        return boardId;
    }

    private String extractPrivateBoardType(HttpServletRequest request){
        String []requestUri = request.getRequestURI().trim().split("/");
        String boardType = requestUri[3].toUpperCase();

        logger.info("[PrivateBoardAccessInterceptor] REQUEST BOARD TYPE = {}", boardType);

        return boardType;
    }

    private String parseBoardIdToCompany(Long boardId){
        BoardDetailDto detailDto = boardService.findById(boardId);
        String boardName = detailDto.getBoardName();
        Company []companies = Company.values();

        for(Company company : companies){
            if (company.getValue().equals(boardName)) return company.name();
        }

        return null;
    }

    private String parseBoardIdToDepartment(Long boardId){
        BoardDetailDto detailDto = boardService.findById(boardId);
        String boardName = detailDto.getBoardName();
        Department []departments = Department.values();

        for (Department department : departments){
            if (department.getValue().equals(boardName)) return department.name();
        }

        return null;
    }

    private boolean companyBoardSessionCheck(String memberCompany, Long boardId){
        String company = this.parseBoardIdToCompany(boardId);

        logger.info("[PrivateBoardAccessInterceptor] SESSION MEMBER COMPANY = {}", company);
        logger.info("[PrivateBoardAccessInterceptor] BOARD ID = {}", boardId);


        return memberCompany.equals(company);

    }

    private boolean departmentBoardSessionCheck(String memberDepartment, Long boardId){
        String department = this.parseBoardIdToDepartment(boardId);

        logger.info("[PrivateBoardAccessInterceptor] SESSION MEMBER DEPARTMENT = {}", memberDepartment);
        logger.info("[PrivateBoardAccessInterceptor] BOARD ID = {}", boardId);

        return memberDepartment.equals(department);
    }
}
