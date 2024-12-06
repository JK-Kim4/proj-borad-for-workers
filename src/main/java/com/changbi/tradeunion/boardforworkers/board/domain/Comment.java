package com.changbi.tradeunion.boardforworkers.board.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column
    private Long postId;

    @Column
    private Long memberId;

    @Column
    private Long parentCommentId;

    @Column
    private String commentValue;

    @Column
    private LocalDateTime appendDate;

    @Column
    private LocalDateTime updateDate;

    @Builder
    public Comment(
            Long postId, Long memberId, String commentValue,
            LocalDateTime appendDate, LocalDateTime updateDate){

        this.postId = postId;
        this.memberId = memberId;
        this.commentValue = commentValue;
        this.appendDate = appendDate;
        this.updateDate = updateDate;

    }

    public void updateParentCommentId(Long parentCommentId){
        this.parentCommentId = parentCommentId;
    }
}
