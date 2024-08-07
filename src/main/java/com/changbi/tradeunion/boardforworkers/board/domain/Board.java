package com.changbi.tradeunion.boardforworkers.board.domain;

import com.changbi.tradeunion.boardforworkers.board.presentation.dto.BoardSaveDto;
import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column
    private String boardName;

    @Column
    private boolean useYn;

    @Column
    private boolean attachmentAllowYn;

    @Column
    @ColumnDefault("1")
    private Integer depth;

    @Column
    private Long upperBoardId;

    @Column
    private String boardPath;

    @Column
    private LocalDateTime appendDate;

    @Column
    private Long appendAdminId;

    @Column
    private LocalDateTime updateDate;

    @Column
    private Long updateAdminId;

    @Column
    @Enumerated(EnumType.STRING)
    private Role readRole;

    @Column
    @Enumerated(EnumType.STRING)
    private Role writeRole;

    @Builder
    public Board (
            String boardName,
            boolean useYn, boolean attachmentAllowYn,
            String readRole, String writeRole,
            Integer depth){
        this.boardName = boardName;
        this.useYn = useYn;
        this.attachmentAllowYn = attachmentAllowYn;
        this.readRole = Role.valueOf(readRole);
        this.writeRole = Role.valueOf(writeRole);
        this.depth = depth;
        this.appendDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
        //TODO appendAdminId
    }

    public void update(BoardSaveDto dto){
        this.boardName = dto.getBoardName();
        this.useYn = dto.isUseYn();
        this.attachmentAllowYn = dto.isAttachmentAllowYn();
        this.updateDate = LocalDateTime.now();
        this.readRole = Role.valueOf(dto.getReadRole());
        this.writeRole = Role.valueOf(dto.getWriteRole());
    }
}
