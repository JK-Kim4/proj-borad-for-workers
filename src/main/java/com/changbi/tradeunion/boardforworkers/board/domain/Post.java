package com.changbi.tradeunion.boardforworkers.board.domain;

import com.changbi.tradeunion.boardforworkers.board.presentation.dto.PostSaveDto;
import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.PostHead;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Post Entity
 *
 * @desc: 게시글 엔티티 ( Board 1 : Post N )
 * */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column
    private Long boardId;

    @Column
    private boolean useYn;

    @Column
    private Long memberId;

    @Column
    @Enumerated(EnumType.STRING)
    private PostHead postHead;

    @Column
    private String postTitle;

    @Column(columnDefinition = "TEXT")
    private String postContent;

    @Column
    private int readCount;

    @Column
    private int recommendCount;

    @Column
    private String attachmentFileName;

    @Column
    private String attachmentFilePath;

    @Column
    private LocalDateTime appendDate;

    @Column
    private LocalDateTime updateDate;

    @Transient
    private String memberName;

    @Transient
    private String boardName;

    @Builder
    public Post (
            String postHead, String postTitle, String postContent,
            String attachmentFileName, String attachmentFilePath,
            Long boardId, Long memberId, boolean useYn) {

        this.boardId = boardId;
        this.memberId = memberId;
        this.useYn = useYn;
        if(!Objects.isNull(postHead)){
            this.postHead = PostHead.valueOf(postHead);
        }
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.readCount = 0;
        this.recommendCount = 0;
        this.attachmentFileName = attachmentFileName;
        this.attachmentFilePath = attachmentFilePath;
        this.appendDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();

    }

    public void update(PostSaveDto dto) {
        this.useYn = dto.isUseYn();
        this.postHead = PostHead.valueOf(dto.getPostHead());
        this.postTitle = dto.getPostTitle();
        this.postContent = dto.getPostContent();
        this.updateDate = LocalDateTime.now();
        //TODO attachment file
    }

    public void updateReadCount() {
        this.readCount += 1;
    }

    public void updateRecommendCount() {
        this.recommendCount += 1;
    }
}
