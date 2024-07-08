package com.changbi.tradeunion.boardforworkers.board.domain;

import com.changbi.tradeunion.boardforworkers.common.dto.BoardSaveDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Board Entity
 *
 * @desc: 게시판 엔티티 (Board 1 : Post N)
 * */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @Column
    private String boardName;

    @Column
    private boolean useYn;

    @Column
    private boolean attachmentAllowYn;

    @Column
    private LocalDateTime appendDate;

    @Column
    private Long appendAdminId;

    @Column
    private LocalDateTime updateDate;

    @Column
    private Long updateAdminId;

    public void update(BoardSaveDto dto){
        this.id = dto.getBoardId();
        this.boardName = dto.getBoardName();
        this.useYn = dto.isUseYn();
        this.attachmentAllowYn = dto.isAttachmentAllowYn();
        this.updateDate = LocalDateTime.now();
    }
}
