package com.changbi.tradeunion.boardforworkers.board.presentation.dto;

import com.changbi.tradeunion.boardforworkers.board.domain.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardListDto {

    private Long boardId;
    private String boardName;
    private boolean useYn;
    private boolean attachmentAllowYn;
    private LocalDateTime appendDate;
    private Long appendAdminId;
    private LocalDateTime updateDate;
    private Long updateAdminId;

    @Builder
    public BoardListDto(Board board){
        this.boardId = board.getId();
        this.boardName = board.getBoardName();
        this.useYn = board.isUseYn();
        this.attachmentAllowYn = board.isAttachmentAllowYn();
        this.appendDate = board.getAppendDate();
        this.appendAdminId = board.getAppendAdminId();
        this.updateDate = board.getUpdateDate();
        this.updateAdminId = board.getUpdateAdminId();
    }
}
