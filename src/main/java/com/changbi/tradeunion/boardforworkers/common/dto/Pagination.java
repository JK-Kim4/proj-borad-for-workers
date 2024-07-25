package com.changbi.tradeunion.boardforworkers.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class Pagination {
    private final Integer PAGE_LENGTH = 10;

    private Integer pageNum;
    private Integer pageSize;

    private Long totalPages;
    private Long totalCount;

    private boolean isFirstPage;
    private boolean isLastPage;

    private List<Integer> pages = new ArrayList<>();

    @Builder
    public Pagination(Integer pageNum, Integer pageSize, Long totalCount) throws IndexOutOfBoundsException{
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPages = (long) Math.ceil((double)totalCount / pageSize) -1;

        if(pageNum > totalPages.intValue()) throw new IndexOutOfBoundsException("호출 가능 페이지 범위를 벗어났습니다.");

        if(pageNum == 0) this.isFirstPage = true;
        if(pageNum == totalPages.intValue()) this.isLastPage = true;

        this.setPages();
    }

    public void setPages(){
        Integer startPage;
        Integer endPage;

        if(this.pageNum == 0) startPage = 0;
        else startPage = (int) Math.ceil(this.pageNum/this.PAGE_LENGTH) * 10;

        endPage = startPage + 9;
        for(; startPage <= endPage; startPage++){
            pages.add(startPage);
        }
    }



}
