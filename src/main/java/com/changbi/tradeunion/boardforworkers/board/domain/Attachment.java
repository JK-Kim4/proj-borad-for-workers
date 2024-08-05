package com.changbi.tradeunion.boardforworkers.board.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Attachment {

    @Id @Getter
    @Column(name = "attachment_id")
    private Long id;

    @Column
    private Long postId;

    @Column
    private String fileOriginalName;

    @Column
    private String fileName;

    @Column
    private String fileSize;

    @Column
    private String filePath;

    @Column
    private LocalDateTime appendDate;

    @Column
    private LocalDateTime updateDate;

    public void updatePostId(Long postId){
        this.postId = postId;
    }
}
