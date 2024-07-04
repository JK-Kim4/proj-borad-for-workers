package com.changbi.tradeunion.boardforworkers.common.dto;

import lombok.Builder;
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

    @Builder
    public ResultDto(
            String resultCode, String resultMessage,
            T data, Pagination pagination) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.data = data;
        this.pagination = pagination;
    }
}
