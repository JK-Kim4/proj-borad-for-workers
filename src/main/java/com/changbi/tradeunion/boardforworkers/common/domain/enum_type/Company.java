package com.changbi.tradeunion.boardforworkers.common.domain.enum_type;

import lombok.Getter;

@Getter
public enum Company {

    CHANGBI("창비"),
    CHANGBI_EDU("창비교육"),
    MEDIA_CHANGBI("미디어창비");

    private String value;
    Company(String value){
        this.value = value;
    }


    
}
