package com.changbi.tradeunion.boardforworkers.application.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum MetaType {

    SERVICE_NAME("서비스 이름"),
    SERVICE_ICON("대표 아이콘");

    private String value;
    MetaType(String value){
        this.value = value;
    }
}
