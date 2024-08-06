package com.changbi.tradeunion.boardforworkers.board.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Attachment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Builder
    public Attachment(
            String fileOriginalName, String fileName,
            String fileSize, String filePath){
        this.fileOriginalName = fileOriginalName;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.filePath = filePath;
    }

    public void updatePostId(Long postId){
        this.postId = postId;
    }
}
