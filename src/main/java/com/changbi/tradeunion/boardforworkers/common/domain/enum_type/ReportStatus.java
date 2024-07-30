package com.changbi.tradeunion.boardforworkers.common.domain.enum_type;

import lombok.Getter;

@Getter
public enum ReportStatus {

    REGISTRATION("접수"),
    INPROCESS("처리중"),
    REFUSE("반려"),
    PASS("처리 완료");

    private String value;

    ReportStatus(String value) {
        this.value = value;
    }
}
