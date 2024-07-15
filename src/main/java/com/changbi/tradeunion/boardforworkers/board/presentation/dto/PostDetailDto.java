package com.changbi.tradeunion.boardforworkers.board.presentation.dto;

import com.changbi.tradeunion.boardforworkers.board.domain.Post;
import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.PostHead;
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
    private String postHead;
    private String postTitle;
    private String postContent;
    private int readCount;
    private int recommendCount;
    private String attachmentFileName;
    private String attachmentFilePath;
    private LocalDateTime appendDate;
    private LocalDateTime updateDate;

    private String boardName;
    private String memberRealName;
    private String memberNickName;

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

    public PostDetailDto(
            Long postId, Long boardId, String boardName,
            Long memberId, String memberRealName, String memberNickName,
            PostHead postHead, String postTitle, String postContent,
            boolean useYn, int readCount, int recommendCount,
            String attachmentFileName, String attachmentFilePath,
            LocalDateTime appendDate, LocalDateTime updateDate) {

        this.postId = postId;
        this.boardId = boardId;
        this.boardName = boardName;
        this.memberId = memberId;
        this.memberRealName = memberRealName;
        this.memberNickName = memberNickName;
        this.postHead = postHead.name();
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.useYn = useYn;
        this.readCount = readCount;
        this.recommendCount = recommendCount;
        this.attachmentFileName = attachmentFileName;
        this.attachmentFilePath = attachmentFilePath;
        this.appendDate = appendDate;
        this.updateDate = updateDate;

    }
}

