package com.changbi.tradeunion.boardforworkers.board.presentation.dto;

import com.changbi.tradeunion.boardforworkers.board.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class PostSaveDto {

    private Long boardId;
    private Long memberId;
    private boolean useYn;
    private String postTitle;
    private String postContent;
    private String postHead;
    private String attachmentFileName;
    private String attachmentFilePath;

    @Builder
    public PostSaveDto(
            Long boardId, Long memberId, boolean useYn,
            String postHead, String postTitle, String postContent,
            String attachmentFileName, String attachmentFilePath) {

        this.boardId = boardId;
        this.memberId = memberId;
        this.useYn = useYn;
        this.postHead = postHead;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.attachmentFileName = attachmentFileName;
        this.attachmentFilePath = attachmentFilePath;

    }

    public Post toEntity(){
        return Post.builder()
                    .boardId(this.boardId)
                    .memberId(this.memberId)
                    .postHead(this.postHead)
                    .postTitle(this.postTitle)
                    .postContent(this.postContent)
                    .useYn(this.useYn)
                    .attachmentFileName(this.attachmentFileName)
                    .attachmentFilePath(this.attachmentFilePath)
                .build();
    }

}
