package com.changbi.tradeunion.boardforworkers.common.dto;

import com.changbi.tradeunion.boardforworkers.board.domain.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class BoardSaveDto {

    private Long boardId;
    private String boardName;
    private boolean useYn;
    private boolean attachmentAllowYn;

    public Board toEntity(){
        return Board.builder()
                .boardName(this.boardName)
                .useYn(this.isUseYn())
                .attachmentAllowYn(this.isAttachmentAllowYn())
                .build();
    }
}
