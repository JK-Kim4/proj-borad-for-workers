package com.changbi.tradeunion.boardforworkers.board.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CommentDetailDto {

    private Long commentId;
    private Long postId;
    private Long memberId;
    private Long parentCommentId;
    private String commentValue;

    @Builder
    public CommentDetailDto(Long commentId, Long postId, Long memberId, Long parentCommentId, String commentValue){
        this.commentId = commentId;
        this.postId = postId;
        this.memberId = memberId;
        this.parentCommentId = parentCommentId;
        this.commentValue = commentValue;
    }

}
