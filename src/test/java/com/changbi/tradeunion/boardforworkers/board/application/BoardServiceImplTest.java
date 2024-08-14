package com.changbi.tradeunion.boardforworkers.board.application;

import com.changbi.tradeunion.boardforworkers.board.exception.BoardDuplicationException;
import com.changbi.tradeunion.boardforworkers.board.presentation.dto.*;
import com.changbi.tradeunion.boardforworkers.board.repository.BoardRepository;
import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.PostHead;
import com.changbi.tradeunion.boardforworkers.common.dto.Pagination;
import com.changbi.tradeunion.boardforworkers.member.application.MemberServiceImpl;
import com.changbi.tradeunion.boardforworkers.member.presentation.dto.LoginInformation;
import com.changbi.tradeunion.boardforworkers.member.presentation.dto.SessionMember;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@ActiveProfiles("loc")
public class BoardServiceImplTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardServiceImpl boardService;

    @Autowired
    MemberServiceImpl memberService;

    final String BOARD_NAME_1 = "board1";
    final String BOARD_NAME_2 = "board2";
    final String BOARD_NAME_3 = "board3";
    final String READ_ROLE = "USER";
    final String WRITE_ROLE = "USER";

    final Long NOTICE_BOARD_ID = 91L;
    final String TEST_POST_HEAD = PostHead.GENERAL.name();

    final String TEST_MEMBER_EMAIL = "mediachangbi@changbi.com";
    final String TEST_MEMBER_PASSWORD = "$2a$10$Qsc23VzJNxA1LZa61MjMt.tQd1aTylhADS4nPE3uJQhrs3Fn9nByO";

    final String TEST_POST_TITLE = "THIS TEST POST";
    final String TEST_POST_CONTENT = "HELLO THIS IS TEST POST CONTENT";

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class 게시판_엔티티_테스트{

        @Nested
        @DisplayName("게시판 생성(등록) 테스트")
        class board_insert_test{

            @Test @Transactional
            @DisplayName("신규 게시판 생성 후 고유번호를 반환한다.")
            public void new_board_insert_test(){
                boolean useYn = false;
                boolean attachmentAllowYn = true;
                BoardSaveDto dto = BoardSaveDto.builder()
                        .boardName(BOARD_NAME_1)
                        .useYn(useYn)
                        .readRole(READ_ROLE)
                        .writeRole(WRITE_ROLE)
                        .depth(1)
                        .attachmentAllowYn(attachmentAllowYn)
                        .build();


                Long boardId = boardService.save(dto);
                BoardDetailDto detailDto = boardService.findById(boardId);

                Assertions.assertEquals(BOARD_NAME_1, detailDto.getBoardName());
                Assertions.assertEquals(useYn, detailDto.isUseYn());
                Assertions.assertEquals(attachmentAllowYn, detailDto.isAttachmentAllowYn());
            }

            @Test @Transactional
            @DisplayName("동일한 이름의 게시판 생성 시 BoardDuplicationException을 반환한다.")
            public void board_name_duplication_test(){
                boolean useYn = false;
                boolean attachmentAllowYn = true;
                BoardSaveDto dto = BoardSaveDto.builder()
                        .boardName(BOARD_NAME_1)
                        .useYn(useYn)
                        .readRole(READ_ROLE)
                        .writeRole(WRITE_ROLE)
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
                boolean useYn = true;
                boolean attachmentAllowYn = true;
                BoardSaveDto dto1 = BoardSaveDto.builder()
                        .boardName(BOARD_NAME_1)
                        .useYn(useYn)
                        .readRole(READ_ROLE)
                        .writeRole(WRITE_ROLE)
                        .depth(1)
                        .attachmentAllowYn(attachmentAllowYn)
                        .build();

                BoardSaveDto dto2 = BoardSaveDto.builder()
                        .boardName(BOARD_NAME_2)
                        .useYn(useYn)
                        .readRole(READ_ROLE)
                        .writeRole(WRITE_ROLE)
                        .depth(1)
                        .attachmentAllowYn(attachmentAllowYn)
                        .build();

                boardService.save(dto1);
                boardService.save(dto2);
            }

            @Test @Transactional
            @DisplayName("등록된 게시판 목록을 조회한다.")
            public void board_select_all_test(){
                List<BoardListDto> boardList =  boardService.findBoards();

                //Assertions.assertEquals(7, boardList.size());
                Assertions.assertEquals(BOARD_NAME_1, boardList.get(0).getBoardName());
                Assertions.assertEquals(BOARD_NAME_2, boardList.get(1).getBoardName());

            }

            @Test @Transactional
            @DisplayName("게시판 ID로 특정 게시판 정보를 조회한다.")
            public void board_select_by_id_test(){
                BoardSaveDto dto = BoardSaveDto.builder()
                        .boardName(BOARD_NAME_3)
                        .useYn(true)
                        .readRole(READ_ROLE)
                        .writeRole(WRITE_ROLE)
                        .depth(1)
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
                boolean useYn = true;
                boolean attachmentAllowYn = true;
                Integer depth = 1;
                BoardSaveDto dto1 = BoardSaveDto.builder()
                        .boardName(BOARD_NAME_1)
                        .readRole(READ_ROLE)
                        .writeRole(WRITE_ROLE)
                        .depth(depth)
                        .useYn(useYn)
                        .attachmentAllowYn(attachmentAllowYn)
                        .build();

                BoardSaveDto dto2 = BoardSaveDto.builder()
                        .boardName(BOARD_NAME_2)
                        .useYn(useYn)
                        .readRole(READ_ROLE)
                        .writeRole(WRITE_ROLE)
                        .depth(depth)
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

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class 게시글_엔티티_테스트{

        final MockHttpSession session = new MockHttpSession();

        @BeforeEach
        void beforeEach(){
            //01. member login
            memberService.memberLogin(LoginInformation.builder()
                    .memberEmail(TEST_MEMBER_EMAIL)
                    .memberPassword(TEST_MEMBER_PASSWORD)
                    .build(), session);
        }

        @Nested
        @DisplayName("게시글 등록 테스트")
        class post_insert_test{

            @Test @Transactional
            @DisplayName("신규 게시글을 로그인된 사용자 정보와 함께 저장한다.")
            void post_insert_with_member_test(){
                //given
                SessionMember sessionMember = (SessionMember) session.getAttribute("member");

                //when
                PostSaveDto saveDto = PostSaveDto.builder()
                                                .memberId(sessionMember.getMemberId())
                                                .boardId(NOTICE_BOARD_ID)
                                                .postHead(TEST_POST_HEAD)
                                                .postTitle(TEST_POST_TITLE)
                                                .postContent(TEST_POST_CONTENT)
                                                .useYn(true)
                                            .build();

                Long postId = boardService.savePost(saveDto);
                PostDetailDto detailDto = boardService.findPostById(postId, null);
                //then

                Assertions.assertEquals(TEST_POST_TITLE, detailDto.getPostTitle());
                Assertions.assertEquals(TEST_POST_CONTENT, detailDto.getPostContent());
                Assertions.assertEquals(sessionMember.getMemberId(), detailDto.getMemberId());
                Assertions.assertEquals(NOTICE_BOARD_ID, detailDto.getBoardId());
            }

        }

        @Nested
        @DisplayName("게시글 조회 테스트")
        class post_select_test{

            final MockHttpSession session = new MockHttpSession();

            @BeforeEach
            void beforeEach(){
                //게시글 테스트 데이터 등록
                memberService.memberLogin(LoginInformation.builder()
                        .memberEmail(TEST_MEMBER_EMAIL)
                        .memberPassword(TEST_MEMBER_PASSWORD)
                        .build(), session);

                SessionMember sessionMember = (SessionMember) session.getAttribute("member");

                PostSaveDto saveDto = PostSaveDto.builder()
                            .memberId(sessionMember.getMemberId())
                            .boardId(NOTICE_BOARD_ID)
                            .postHead(TEST_POST_HEAD)
                            .postTitle(TEST_POST_TITLE)
                            .postContent(TEST_POST_CONTENT)
                            .useYn(true)
                        .build();

                boardService.savePost(saveDto);
                boardService.savePost(saveDto);
                boardService.savePost(saveDto);

            }

            @Test @Transactional
            @DisplayName("등록되어있는 전체 게시글을 조회한다.")
            void post_select_post_test(){
                //when
                Pagination pagination = boardService.getPostPagingInfo(0, 20);
                List<PostListDto> postListDtoList = boardService.findPosts(NOTICE_BOARD_ID, pagination);

                //then
                Assertions.assertEquals(4, postListDtoList.size());

            }
        }

    }

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class 댓글_엔티티_테스트{

        final MockHttpSession session = new MockHttpSession();

        @BeforeEach
        void beforeEach(){
            //00. 게시판 생성
            boardService.save(BoardSaveDto.builder()
                    .boardName(BOARD_NAME_1)
                    .useYn(true)
                    .readRole(READ_ROLE)
                    .writeRole(WRITE_ROLE)
                    .depth(1)
                    .attachmentAllowYn(true)
                    .build());

            //01. 세션 사용자 등록
            memberService.memberLogin(LoginInformation.builder()
                    .memberEmail(TEST_MEMBER_EMAIL)
                    .memberPassword(TEST_MEMBER_PASSWORD)
                    .build(), session);

            //02. 게시글 등록
            SessionMember sessionMember = (SessionMember) session.getAttribute("member");

            List<BoardListDto> boardListDtoList = boardService.findBoards();
            BoardListDto boardListDto = boardListDtoList.get(0);

            PostSaveDto saveDto = PostSaveDto.builder()
                    .memberId(sessionMember.getMemberId())
                    .boardId(boardListDto.getBoardId())
                    .postHead(TEST_POST_HEAD)
                    .postTitle(TEST_POST_TITLE)
                    .postContent(TEST_POST_CONTENT)
                    .useYn(true)
                    .build();

            boardService.savePost(saveDto);

        }

        @Nested
        @DisplayName("댓글 등록 테스트")
        class post_insert_test{

            private Long targetPostId;
            private Long targetMemberId;
            private String commentValue = "INSERT TEST COMMENT";

            @BeforeEach
            void beforeEach(){
                List<BoardListDto> boardListDtoList = boardService.findBoards();
                BoardListDto boardListDto = boardListDtoList.get(0);

                List<PostListDto> postListDtoList = boardService.findPosts(boardListDto.getBoardId(), null);
                SessionMember sessionMember = (SessionMember) session.getAttribute("member");

                targetMemberId = sessionMember.getMemberId();
                targetPostId = postListDtoList.get(0).getPostId();

            }

            @Test @Transactional
            @DisplayName("게시글에 신규 댓글을 등록한다.")
            void comment_insert_test(){
                CommentSaveDto commentSaveDto = CommentSaveDto.builder()
                        .postId(targetPostId)
                        .memberId(targetMemberId)
                        .value(commentValue)
                        .build();

                Long commentId = boardService.saveComment(commentSaveDto);
                CommentDetailDto commentDetailDto = boardRepository.findCommentById(commentId);

                Assertions.assertEquals(targetPostId, commentDetailDto.getPostId());
                Assertions.assertEquals(commentValue, commentDetailDto.getCommentValue());

            }


        }

        @Nested
        @DisplayName("댓글 조회 테스트")
        class post_select_test{

            @Test @Transactional
            @DisplayName("게시글 목록 조회 테스트")
            void select_post_test(){
                List<BoardListDto> boardListDtoList = boardService.findBoards();
                BoardListDto boardListDto = boardListDtoList.get(0);

                List<PostListDto> postListDtoList = boardService.findPosts(boardListDto.getBoardId(), null);

                System.out.println(postListDtoList.get(0).getPostId());
                System.out.println(postListDtoList.get(0).getPostTitle());

            }

        }

    }

}
