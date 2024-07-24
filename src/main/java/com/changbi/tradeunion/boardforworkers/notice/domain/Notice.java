package com.changbi.tradeunion.boardforworkers.notice.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Notice {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
