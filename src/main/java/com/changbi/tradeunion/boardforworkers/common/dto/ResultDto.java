package com.changbi.tradeunion.boardforworkers.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ResultDto<T> {

    private String resultCode;
    private String resultMessage;

    private T data;
    private Pagination pagination;
}
