package com.changbi.tradeunion.boardforworkers.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Pagination {
    private final Integer PAGE_SIZE_DEFAULT = 20;

    private Integer pageNum;
    private Integer pageSize;

    private Long totalPages;
    private Long totalCount;

    private boolean isFirstPage;
    private boolean isLastPage;


    @Builder
    public Pagination(Integer pageNum, Integer pageSize){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }



}
