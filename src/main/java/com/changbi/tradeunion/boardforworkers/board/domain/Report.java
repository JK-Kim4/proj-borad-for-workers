package com.changbi.tradeunion.boardforworkers.board.domain;

import com.changbi.tradeunion.boardforworkers.member.domain.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Report {

    @Id @GeneratedValue
    @Column(name = "report_id")
    private Long id;

    private Long postId;

    private Long memberId;

    private LocalDateTime reportDate;

    @Builder
    public Report(Post post, Member member) {
        this.postId = post.getId();
        this.memberId = member.getId();
        this.reportDate = LocalDateTime.now();
    }
}
