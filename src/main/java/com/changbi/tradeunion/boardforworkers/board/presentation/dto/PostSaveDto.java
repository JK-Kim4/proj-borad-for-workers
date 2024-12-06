package com.changbi.tradeunion.boardforworkers.board.presentation.dto;

import com.changbi.tradeunion.boardforworkers.board.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class PostSaveDto {

    private Long postId;
    private Long boardId;
    private Long memberId;
    private boolean useYn;
    private String postTitle;
    private String postContent;
    private String postHead;
    private Long attachmentId;

    @Builder
    public PostSaveDto(
            Long boardId, Long memberId, boolean useYn,
            String postHead, String postTitle, String postContent,
            Long attachmentId) {

        this.boardId = boardId;
        this.memberId = memberId;
        this.useYn = useYn;
        this.postHead = postHead;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.attachmentId = attachmentId;

    }

    public Post toEntity(){
        return Post.builder()
                    .boardId(this.boardId)
                    .memberId(this.memberId)
                    .postHead(this.postHead)
                    .postTitle(this.postTitle)
                    .postContent(this.postContent)
                    .useYn(this.useYn)
                    .attachmentId(this.attachmentId)
                .build();
    }

}
