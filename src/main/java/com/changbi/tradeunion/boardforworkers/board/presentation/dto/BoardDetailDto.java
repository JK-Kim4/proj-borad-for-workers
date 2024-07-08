package com.changbi.tradeunion.boardforworkers.board.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class BoardDetailDto {

    private Long boardId;
    private String boardName;
    private boolean useYn;
    private boolean attachmentAllowYn;
    private LocalDateTime appendDate;
    private Long appendAdminId;
    private LocalDateTime updateDate;
    private Long updateAdminId;

}
