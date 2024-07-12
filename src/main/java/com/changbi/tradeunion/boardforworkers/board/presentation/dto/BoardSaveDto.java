package com.changbi.tradeunion.boardforworkers.board.presentation.dto;

import com.changbi.tradeunion.boardforworkers.board.domain.Board;
import lombok.Builder;
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
    private String readRole;
    private String writeRole;

    @Builder
    public BoardSaveDto(
            long boardId, String boardName,
            boolean useYn, boolean attachmentAllowYn,
            String readRole, String writeRole) {
        this.boardId = boardId;
        this.boardName = boardName;
        this.useYn = useYn;
        this.attachmentAllowYn = attachmentAllowYn;
        this.readRole = readRole;
        this.writeRole = writeRole;
    }

    public Board toEntity(){
        return Board.builder()
                .boardName(this.boardName)
                .useYn(this.isUseYn())
                .attachmentAllowYn(this.isAttachmentAllowYn())
                .readRole(this.readRole)
                .writeRole(this.writeRole)
                .build();
    }
}
