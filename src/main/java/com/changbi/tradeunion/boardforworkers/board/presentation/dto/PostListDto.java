package com.changbi.tradeunion.boardforworkers.board.presentation.dto;

import com.changbi.tradeunion.boardforworkers.board.domain.Post;
import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.PostHead;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

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
    private LocalDateTime updateDate;

    //other entity data
    private String boardName;
    private String memberRealName;

    @Builder
    public PostListDto(Post post){
        this.postId = post.getId();
        this.boardId = post.getBoardId();
        this.memberId = post.getMemberId();
        this.useYn = post.isUseYn();
        this.postHead = post.getPostHead().name();
        this.postTitle = post.getPostTitle();
        this.readCount = post.getReadCount();
        this.recommendCount = post.getRecommendCount();
        this.appendDate = post.getAppendDate();
    }

    public PostListDto(
            Long postId, Long boardId, Long memberId,
            boolean useYn, PostHead postHead, String postTitle,
            int readCount, int recommendCount,
            LocalDateTime appendDate, LocalDateTime updateDate, String memberRealName, String boardName){
        this.postId =postId;
        this.boardId = boardId;
        this.boardName = boardName;
        this.memberId = memberId;
        this.memberRealName = memberRealName;
        this.useYn = useYn;
        if(!Objects.isNull(postHead)){
            this.postHead = postHead.name();
        }
        this.postTitle = postTitle;
        this.readCount = readCount;
        this.recommendCount = recommendCount;
        this.appendDate = appendDate;
        this.updateDate = updateDate;
    }

    public PostListDto (String memberRealName, String boardName){
        this.memberRealName = memberRealName;
        this.boardName = boardName;
    }
}
