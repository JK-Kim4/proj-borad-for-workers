package com.changbi.tradeunion.boardforworkers.board.presentation.dto;

import com.changbi.tradeunion.boardforworkers.board.domain.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class CommentSaveDto {

    private Long postId;

    private Long memberId;

    private Long parentCommentId;

    private String value;

    @Builder
    public CommentSaveDto(Long postId, Long memberId, String value) {
        this.postId = postId;
        this.memberId = memberId;
        this.value = value;
    }

    public Comment toEntity(){
        return Comment.builder()
                .postId(this.postId)
                .memberId(this.memberId)
                .commentValue(this.value)
                .appendDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
    }

}
