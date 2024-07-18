package com.changbi.tradeunion.boardforworkers.board.presentation.dto;

import com.changbi.tradeunion.boardforworkers.board.domain.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class BoardListDto {

    private Long boardId;
    private String boardName;
    private boolean useYn;
    private boolean attachmentAllowYn;
    private String boardPath;
    private LocalDateTime appendDate;
    private Long appendAdminId;
    private LocalDateTime updateDate;
    private Long updateAdminId;

    private Integer depth;
    private List<BoardListDto> childBoardList = new ArrayList<>();

    @Builder
    public BoardListDto(Board board){
        this.boardId = board.getId();
        this.boardName = board.getBoardName();
        this.useYn = board.isUseYn();
        this.attachmentAllowYn = board.isAttachmentAllowYn();
        this.boardPath = board.getBoardPath();
        this.appendDate = board.getAppendDate();
        this.appendAdminId = board.getAppendAdminId();
        this.updateDate = board.getUpdateDate();
        this.updateAdminId = board.getUpdateAdminId();
    }

    public void setChildBoardList(List<BoardListDto> childBoardList) {
        this.childBoardList = childBoardList;
    }
}
