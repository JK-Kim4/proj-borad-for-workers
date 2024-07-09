package com.changbi.tradeunion.boardforworkers.board.application;

import com.changbi.tradeunion.boardforworkers.board.presentation.dto.BoardDetailDto;
import com.changbi.tradeunion.boardforworkers.board.presentation.dto.BoardSaveDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class BoardServiceImplTest {

    @Autowired
    BoardServiceImpl boardService;

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class 게시판_엔티티_테스트{

        @Nested
        @DisplayName("게시판 생성(등록) 테스트")
        class board_insert_test{


            @Test @Transactional
            @DisplayName("신규 게시판 생성 후 고유번호를 반환한다.")
            public void new_board_insert_test(){
                BoardSaveDto dto = new BoardSaveDto();
                String name = "자유게시판";
                boolean useYn = false;
                boolean attachmentAllowYn = true;
                dto.setBoardName(name); dto.setUseYn(useYn); dto.setAttachmentAllowYn(attachmentAllowYn);

                Long boardId = boardService.save(dto);
                BoardDetailDto detailDto = boardService.findById(boardId);

                Assertions.assertEquals(name, detailDto.getBoardName());
                Assertions.assertEquals(useYn, detailDto.isUseYn());
                Assertions.assertEquals(attachmentAllowYn, detailDto.isAttachmentAllowYn());
            }
        }

    }

}
