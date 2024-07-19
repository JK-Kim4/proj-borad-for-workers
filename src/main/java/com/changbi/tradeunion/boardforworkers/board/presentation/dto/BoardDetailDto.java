package com.changbi.tradeunion.boardforworkers.board.presentation.dto;

import com.changbi.tradeunion.boardforworkers.board.domain.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class BoardDetailDto {

    private Long boardId;
    private String boardName;
    private boolean useYn;
    private boolean attachmentAllowYn;
    private String readRole;
    private String writeRole;
    private Integer depth;
    private Long upperBoardId;
    private LocalDateTime appendDate;
    private Long appendAdminId;
    private LocalDateTime updateDate;
    private Long updateAdminId;

    private List<BoardDetailDto> childBoardList = new ArrayList<>();
    private BoardDetailDto parentBoard;

    @Builder
    public BoardDetailDto(Board board) {
        this.boardId = board.getId();
        this.boardName = board.getBoardName();
        this.useYn = board.isUseYn();
        this.attachmentAllowYn = board.isAttachmentAllowYn();
        this.depth = board.getDepth();
        this.upperBoardId = board.getUpperBoardId();
        this.readRole = board.getReadRole().name();
        this.writeRole = board.getWriteRole().name();
        this.appendDate = board.getAppendDate();
        this.updateDate = board.getUpdateDate();
        //TODO admin id
        //this.appendAdminId = board.getAppendAdminId();
        //this.updateAdminId = board.getUpdateAdminId();
    }

}
