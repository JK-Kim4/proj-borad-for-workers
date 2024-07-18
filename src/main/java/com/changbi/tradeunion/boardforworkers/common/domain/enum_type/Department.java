package com.changbi.tradeunion.boardforworkers.common.domain.enum_type;

import lombok.Getter;

@Getter
public enum Department {

    PUBLISHING("출판"),
    IT("IT"),
    HR("인사"),
    MARKETING("마케팅"),
    DESIGN("디자인"),
    ETC("기타");

    private String value;

    Department(String value){
        this.value = value;
    }
}
