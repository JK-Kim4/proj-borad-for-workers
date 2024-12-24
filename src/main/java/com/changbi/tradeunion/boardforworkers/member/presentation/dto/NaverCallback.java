package com.changbi.tradeunion.boardforworkers.member.presentation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class NaverCallback {

    private String code;
    private String state;
    private String error;
    private String error_description;

}
