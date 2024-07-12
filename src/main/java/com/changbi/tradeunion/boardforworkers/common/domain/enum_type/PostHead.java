package com.changbi.tradeunion.boardforworkers.common.domain.enum_type;

import lombok.Getter;

@Getter
public enum PostHead {

    NEWS("뉴스"),
    NOTICE("공지"),
    GENERAL("일반");

    private String value;

    PostHead(String value){
        this.value = value;
    }

}
