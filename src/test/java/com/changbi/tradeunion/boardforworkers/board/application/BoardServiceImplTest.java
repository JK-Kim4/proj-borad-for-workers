package com.changbi.tradeunion.boardforworkers.board.application;

import com.changbi.tradeunion.boardforworkers.board.exception.BoardDuplicationException;
import com.changbi.tradeunion.boardforworkers.board.presentation.dto.BoardDetailDto;
import com.changbi.tradeunion.boardforworkers.board.presentation.dto.BoardListDto;
import com.changbi.tradeunion.boardforworkers.board.presentation.dto.BoardSaveDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class BoardServiceImplTest {

    @Autowired
    BoardServiceImpl boardService;
    final String BOARD_NAME_1 = "board1";
    final String BOARD_NAME_2 = "board2";
    final String BOARD_NAME_3 = "board3";

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class 게시판_엔티티_테스트{

        @Nested
        @DisplayName("게시판 생성(등록) 테스트")
        class board_insert_test{

            @Test @Transactional
            @DisplayName("신규 게시판 생성 후 고유번호를 반환한다.")
            public void new_board_insert_test(){
                String name = "자유게시판";
                boolean useYn = false;
                boolean attachmentAllowYn = true;
                BoardSaveDto dto = BoardSaveDto.builder()
                        .boardName(name)
                        .useYn(useYn)
                        .attachmentAllowYn(attachmentAllowYn)
                        .build();


                Long boardId = boardService.save(dto);
                BoardDetailDto detailDto = boardService.findById(boardId);

                Assertions.assertEquals(name, detailDto.getBoardName());
                Assertions.assertEquals(useYn, detailDto.isUseYn());
                Assertions.assertEquals(attachmentAllowYn, detailDto.isAttachmentAllowYn());
            }

            @Test @Transactional
            @DisplayName("동일한 이름의 게시판 생성 시 BoardDuplicationException을 반환한다.")
            public void board_name_duplication_test(){
                String name = "자유게시판";
                boolean useYn = false;
                boolean attachmentAllowYn = true;
                BoardSaveDto dto = BoardSaveDto.builder()
                        .boardName(name)
                        .useYn(useYn)
                        .attachmentAllowYn(attachmentAllowYn)
                        .build();

                Long boardId = boardService.save(dto);

                Assertions.assertThrows(BoardDuplicationException.class, () -> boardService.save(dto));

            }
        }

        @Nested
        @DisplayName("게시판 조회 테스트")
        class board_select_test{


            @BeforeEach
            void beforeEach(){
                boolean useYn = false;
                boolean attachmentAllowYn = true;
                BoardSaveDto dto1 = BoardSaveDto.builder()
                        .boardName(BOARD_NAME_1)
                        .useYn(useYn)
                        .attachmentAllowYn(attachmentAllowYn)
                        .build();

                BoardSaveDto dto2 = BoardSaveDto.builder()
                        .boardName(BOARD_NAME_2)
                        .useYn(useYn)
                        .attachmentAllowYn(attachmentAllowYn)
                        .build();

                boardService.save(dto1);
                boardService.save(dto2);
            }

            @Test @Transactional
            @DisplayName("등록된 게시판 목록을 조회한다.")
            public void board_select_all_test(){
                List<BoardListDto> boardList =  boardService.findBoards();

                Assertions.assertEquals(2, boardList.size());
                Assertions.assertEquals(BOARD_NAME_1, boardList.get(0).getBoardName());
                Assertions.assertEquals(BOARD_NAME_2, boardList.get(1).getBoardName());

            }

            @Test @Transactional
            @DisplayName("게시판 ID로 특정 게시판 정보를 조회한다.")
            public void board_select_by_id_test(){
                BoardSaveDto dto = BoardSaveDto.builder()
                        .boardName(BOARD_NAME_3)
                        .useYn(true)
                        .attachmentAllowYn(true)
                        .build();

                Long boardId = boardService.save(dto);

                BoardDetailDto detailDto = boardService.findById(boardId);

                Assertions.assertEquals(BOARD_NAME_3, detailDto.getBoardName());
            }
        }

        @Nested
        @DisplayName("게시판 수정 및 삭제 테스트")
        class board_delete_test{

            @BeforeEach
            void beforeEach(){
                boolean useYn = false;
                boolean attachmentAllowYn = true;
                BoardSaveDto dto1 = BoardSaveDto.builder()
                        .boardName(BOARD_NAME_1)
                        .useYn(useYn)
                        .attachmentAllowYn(attachmentAllowYn)
                        .build();

                BoardSaveDto dto2 = BoardSaveDto.builder()
                        .boardName(BOARD_NAME_2)
                        .useYn(useYn)
                        .attachmentAllowYn(attachmentAllowYn)
                        .build();
                boardService.save(dto1);
                boardService.save(dto2);
            }


            @Test @Transactional
            @DisplayName("게시판 삭제 테스트 (ignore POST)")
            void board_delete_ignoring_post_test(){
                int totalBoardSize = boardService.findBoards().size();

                BoardDetailDto boardDetailDto = boardService.findByBoardName(BOARD_NAME_1);
                boardService.delete(boardDetailDto.getBoardId());

                totalBoardSize -= 1;

                Assertions.assertEquals(totalBoardSize, boardService.findBoards().size());
            }
        }

    }

}
