package com.changbi.tradeunion.boardforworkers.common.utility;

import com.changbi.tradeunion.boardforworkers.board.presentation.dto.BoardDetailDto;
import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.Company;
import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.Department;
import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.Role;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class EnumUtility {

    private static final Logger logger = LoggerFactory.getLogger(EnumUtility.class);


    public static Long extractPrivateBoardId(HttpServletRequest request) {
        String []requestUri = request.getRequestURI().trim().split("/");
        Long boardId = Long.valueOf(requestUri[4]);

        return boardId;
    }

    public static String extractPrivateBoardType(HttpServletRequest request){
        String []requestUri = request.getRequestURI().trim().split("/");
        String boardType = requestUri[3].toUpperCase();

        logger.info("[public staticBoardAccessInterceptor] REQUEST BOARD TYPE = {}", boardType);

        return boardType;
    }

    public static String parseBoardIdToCompany(BoardDetailDto detailDto){
        String boardName = detailDto.getBoardName();
        Company []companies = Company.values();

        for(Company company : companies){
            if (company.getValue().equals(boardName)) return company.name();
        }

        return null;
    }

    public static String parseBoardIdToDepartment(BoardDetailDto detailDto){
        String boardName = detailDto.getBoardName();
        Department []departments = Department.values();

        for (Department department : departments){
            if (department.getValue().equals(boardName)) return department.name();
        }

        return null;
    }

    public static boolean companyBoardSessionCheck(String memberCompany, BoardDetailDto detailDto){
        String company = EnumUtility.parseBoardIdToCompany(detailDto);

        logger.info("[PrivateBoardAccessInterceptor] SESSION MEMBER COMPANY = {}", company);
        logger.info("[PrivateBoardAccessInterceptor] BOARD ID = {}", detailDto.getBoardId());

        return memberCompany.equals(company);

    }

    public static boolean departmentBoardSessionCheck(String memberDepartment, BoardDetailDto detailDto){
        String department = EnumUtility.parseBoardIdToDepartment(detailDto);

        logger.info("[PrivateBoardAccessInterceptor] SESSION MEMBER DEPARTMENT = {}", memberDepartment);
        logger.info("[PrivateBoardAccessInterceptor] BOARD ID = {}", detailDto.getBoardId());

        return memberDepartment.equals(department);
    }

    public static boolean isQualifiedRole(String memberRole, String boardReadRole){
        String []memberRoleValues = Role.valueOf(memberRole)
                                        .getValue()
                                        .split(",");
        return Arrays.asList(memberRoleValues).contains(Role.valueOf(boardReadRole).getHighestRoleValue());
    }
}
