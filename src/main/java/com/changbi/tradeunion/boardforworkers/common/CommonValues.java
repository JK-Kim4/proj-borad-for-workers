package com.changbi.tradeunion.boardforworkers.common;

public class CommonValues {

    public static final String SERVICE_TYPE_CLIENT = "CLIENT";
    public static final String BOARD_TYPE_COMPANY = "COMPANY";
    public static final String BOARD_TYPE_DEPARTMENT = "DEPARTMENT";

    public static final String PROCESS_TYPE_ACCEPT = "ACCEPT";
    public static final String PROCESS_TYPE_DECLINE = "DECLINE";

    /*RESULT CODE SUCCESS*/
    public static final String RESULT_CODE_SUCCESS_DEFAULT = "0000";
    public static final String RESULT_CODE_SUCCESS_BUT_EMPTY = "0001";

    /*RESULT MESSAGE SUCCESS*/
    public static final String RESULT_MESSAGE_SUCCESS_DEFAULT = "요청이 성공적으로 처리되었습니다.";
    public static final String RESULT_MESSAGE_SUCCESS_INSERT = "등록이 성공적으로 완료되었습니다.";
    public static final String RESULT_MESSAGE_SUCCESS_UPDATE = "수정이 성공적으로 완료되었습니다.";
    public static final String RESULT_MESSAGE_SUCCESS_DELETE = "삭제가 성공적으로 완료되었습니다.";

    /*RESULT CODE FAIL*/
    public static final String RESULT_CODE_FAIL_SYSTEM = "9999";
    public static final String RESULT_CODE_FAIL_SINGLE_ENTITY_VIOLATION = "9900";
    public static final String RESULT_CODE_FAIL_DEFAULT = "9000";
    public static final String RESULT_CODE_FAIL_MEMBER_DUPLICATION = "8000";
    public static final String RESULT_CODE_FAIL_MEMBER_LOGIN_VALIDATION = "8888";


    /*RESULT MESSAGE FAIL*/
    public static final String RESULT_MESSAGE_FAIL_DEFAULT = "요청 처리에 실패하였습니다.";
    public static final String RESULT_MESSAGE_FAIL_SINGLE_ENTITY_VIOLATION = "요청하신 결과가 존재하지 않거나 1개 이상입니다.";
    public static final String RESULT_MESSAGE_FAIL_SYSTEM = "시스템 오류가 발생하여 요청을 처리하지 못하였습니다.";
    public static final String RESULT_MESSAGE_FAIL_INSERT = "등록에 실패하였습니다.";
    public static final String RESULT_MESSAGE_FAIL_UPDATE = "수정에 실패하였습니다.";
    public static final String RESULT_MESSAGE_FAIL_DELETE = "삭제에 실패하였습니다.";

    public static final String RESULT_MESSAGE_FAIL_MEMBER_DUPLICATION = "이미 존재하는 회원입니다.";
    public static final String RESULT_MESSAGE_FAIL_BOARD_NAME_DUPLICATION = "이미 존재하는 게시판 이름입니다.";
    public static final String RESULT_MESSAGE_FAIL_BOARD_AUTHORIZATION = "게시판을 사용할 수 있는 권한이 존재하지않습니다.";
    public static final String RESULT_MESSAGE_FAIL_MEMBER_NOTFOUND = "회원이 존재하지 않습니다.";
    public static final String RESULT_MESSAGE_FAIL_MEMBER_LOGIN_VALIDATION = "아이디 혹은 비밀번호를 확인 후 다시 시도해주세요.";

    public static final String LOCAL_FILE_UPLOAD_PATH = "/Users/jongwan-air/Desktop/temp-upload";
}
