package com.changbi.tradeunion.boardforworkers.board.presentation.dto;

import com.changbi.tradeunion.boardforworkers.board.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class PostListDto {

    private Long postId;
    private Long boardId;
    private boolean useYn;
    private Long memberId;
    private String  postHead;
    private String postTitle;
    private int readCount;
    private int recommendCount;
    private LocalDateTime appendDate;

    @Builder
    public PostListDto(Post post){
        this.postId = post.getId();
        this.boardId = post.getBoardId();
        this.memberId = post.getMemberId();
        this.useYn = post.isUseYn();
        this.postHead = post.getPostHead().getValue();
        this.postTitle = post.getPostTitle();
        this.readCount = post.getReadCount();
        this.recommendCount = post.getRecommendCount();
        this.appendDate = post.getAppendDate();
    }
}
