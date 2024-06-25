package com.changbi.tradeunion.boardforworkers.notice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Notice {

    @Id @GeneratedValue
    @Column(name = "notice_seq")
    private Long seq;

    @Column(length = 120)
    private String noticeTitle;

    @Column(columnDefinition = "TEXT")
    private String noticeDescription;

    @Column
    private boolean useYn;

    @Column
    private boolean fixedYn;

    @Column
    private LocalDateTime appendDate;

    @Column
    private LocalDateTime updateDate;

}
