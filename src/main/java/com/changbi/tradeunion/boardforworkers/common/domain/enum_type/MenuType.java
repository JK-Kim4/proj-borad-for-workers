package com.changbi.tradeunion.boardforworkers.common.domain.enum_type;

public enum MenuType {

    ABOUT("소개"),
    NOTICE("소식"),
    FREE_BOARD("자유게시판"),
    COMPANY_CHANGBI("창비 게시판"),
    COMPANY_EDU_CHANGBI("창비교육 게시판"),
    COMPANY_MEDIA_CHANGBI("미디어창비 게시판"),
    DEPARTMENT_PUBLISHING("편집부 게시판"),
    DEPARTMENT_MARKETING("마게팅부 게시판"),
    DEPARTMENT_DESIGN("디자인부 게시판"),
    REFERENCE("자료실");

    private String value;

    MenuType(String value) {
        this.value = value;
    }
}
