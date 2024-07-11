package com.changbi.tradeunion.boardforworkers.board.presentation.dto;

import com.changbi.tradeunion.boardforworkers.board.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class PostDetailDto {

    private Long postId;
    private Long boardId;
    private boolean useYn;
    private Long memberId;
    private String postTitle;
    private String postContent;
    private int readCount;
    private int recommendCount;
    private String attachmentFileName;
    private String attachmentFilePath;
    private LocalDateTime appendDate;
    private LocalDateTime updateDate;

    @Builder
    public PostDetailDto (Post post){
        this.postId = post.getId();
        this.boardId = post.getBoardId();
        this.memberId = post.getMemberId();
        this.useYn = post.isUseYn();
        this.postTitle = post.getPostTitle();
        this.postContent = post.getPostContent();
        this.readCount = post.getReadCount();
        this.recommendCount = post.getRecommendCount();
        this.attachmentFileName = post.getAttachmentFileName();
        this.attachmentFilePath = post.getAttachmentFilePath();
        this.appendDate = post.getAppendDate();
        this.updateDate = post.getUpdateDate();
    }
}

