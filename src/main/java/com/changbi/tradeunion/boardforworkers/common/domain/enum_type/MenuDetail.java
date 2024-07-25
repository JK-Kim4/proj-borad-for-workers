package com.changbi.tradeunion.boardforworkers.common.domain.enum_type;

import lombok.Getter;

@Getter
public enum MenuDetail {

    LIST("목록"),
    DETAIL("상세"),
    UPDATE("수정"),
    ETC("기타");

    private String value;

    MenuDetail(String value) {
        this.value = value;
    }
}
