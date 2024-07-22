package com.changbi.tradeunion.boardforworkers.common;

import com.changbi.tradeunion.boardforworkers.board.domain.Board;
import com.changbi.tradeunion.boardforworkers.board.presentation.dto.BoardDetailDto;
import com.changbi.tradeunion.boardforworkers.board.repository.BoardRepository;
import com.changbi.tradeunion.boardforworkers.common.utility.EnumUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@ActiveProfiles("loc")
public class EnumTypeTest {

    @Autowired
    BoardRepository boardRepository;

    @Test @Transactional
    @DisplayName("사용자 권한 확인")
    void member_read_role_check(){
        List<Board> boardListDtoList = boardRepository.findBoards();
        System.out.println(boardListDtoList.size());
        Board board = boardListDtoList.get(0);

        BoardDetailDto boardDetailDto = BoardDetailDto.builder().board(board).build();

        String memberRole = "USER";

        Assertions.assertTrue(EnumUtility.isQualifiedRole(memberRole, board.getReadRole().name()));
        Assertions.assertFalse(EnumUtility.isQualifiedRole(memberRole, board.getWriteRole().name()));

    }
}
