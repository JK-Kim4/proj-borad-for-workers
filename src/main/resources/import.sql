-- MEMBER DATA IMPORT
INSERT INTO MEMBER (MEMBER_ID, MEMBER_EMAIL, MEMBER_REAL_NAME, MEMBER_NICK_NAME, MEMBER_PASSWORD, COMPANY, DEPARTMENT, ROLE, APPEND_DATE, UPDATE_DATE) VALUES (10, 'suadmin', '최고관리자', 'SUPER_ADMIN_테스트계정', '$2a$10$Qsc23VzJNxA1LZa61MjMt.tQd1aTylhADS4nPE3uJQhrs3Fn9nByO', 'CHANGBI', 'HR', 'SUPER_ADMIN', NOW(), NOW());
INSERT INTO MEMBER (MEMBER_ID, MEMBER_EMAIL, MEMBER_REAL_NAME, MEMBER_NICK_NAME, MEMBER_PASSWORD, COMPANY, DEPARTMENT, ROLE, APPEND_DATE, UPDATE_DATE) VALUES (20, 'admin', '일반관리자', 'ADMIN_테스트계정', '$2a$10$Qsc23VzJNxA1LZa61MjMt.tQd1aTylhADS4nPE3uJQhrs3Fn9nByO', 'CHANGBI', 'HR', 'SUPER_ADMIN', NOW(), NOW());
INSERT INTO MEMBER (MEMBER_ID, MEMBER_EMAIL, MEMBER_REAL_NAME, MEMBER_NICK_NAME, MEMBER_PASSWORD, COMPANY, DEPARTMENT, ROLE, APPEND_DATE, UPDATE_DATE) VALUES (30, 'changbi@changbi.com', '일반유저-창비-편집', 'USER_테스트계정_1', '$2a$10$Qsc23VzJNxA1LZa61MjMt.tQd1aTylhADS4nPE3uJQhrs3Fn9nByO', 'CHANGBI', 'PUBLISHING', 'USER', NOW(), NOW());
INSERT INTO MEMBER (MEMBER_ID, MEMBER_EMAIL, MEMBER_REAL_NAME, MEMBER_NICK_NAME, MEMBER_PASSWORD, COMPANY, DEPARTMENT, ROLE, APPEND_DATE, UPDATE_DATE) VALUES (40, 'educhangbi@changbi.com', '일반유저-창비교육-마케팅', 'USER_테스트계정_2', '$2a$10$Qsc23VzJNxA1LZa61MjMt.tQd1aTylhADS4nPE3uJQhrs3Fn9nByO', 'CHANGBI_EDU', 'MARKETING', 'USER', NOW(), NOW());
INSERT INTO MEMBER (MEMBER_ID, MEMBER_EMAIL, MEMBER_REAL_NAME, MEMBER_NICK_NAME, MEMBER_PASSWORD, COMPANY, DEPARTMENT, ROLE, APPEND_DATE, UPDATE_DATE) VALUES (50, 'mediachangbi@changbi.com', '일반유저-미디어창비-IT', 'USER_테스트계정_3', '$2a$10$Qsc23VzJNxA1LZa61MjMt.tQd1aTylhADS4nPE3uJQhrs3Fn9nByO', 'MEDIA_CHANGBI', 'IT', 'USER', NOW(), NOW());


-- BOARD DATA IMPORT
-- INSERT INTO BOARD (BOARD_ID, BOARD_NAME, USE_YN, ATTACHMENT_ALLOW_YN, DEPTH, UPPER_BOARD_ID, BOARD_PATH, READ_ROLE, WRITE_ROLE, APPEND_DATE, UPDATE_DATE, APPEND_ADMIN_ID, UPDATE_ADMIN_ID) VALUES (90, '소개', TRUE, TRUE, 1, 90, '/board/90/post/list', 'GUEST', 'SUPER_ADMIN', NOW(), NOW(), 99, 99);
INSERT INTO BOARD (BOARD_ID, BOARD_NAME, USE_YN, ATTACHMENT_ALLOW_YN, DEPTH, UPPER_BOARD_ID, BOARD_PATH, READ_ROLE, WRITE_ROLE, APPEND_DATE, UPDATE_DATE, APPEND_ADMIN_ID, UPDATE_ADMIN_ID) VALUES (91, '소식', TRUE, TRUE, 1, 91, '/board/91/post/list', 'USER', 'ADMIN', NOW(), NOW(), 99, 99);
INSERT INTO BOARD (BOARD_ID, BOARD_NAME, USE_YN, ATTACHMENT_ALLOW_YN, DEPTH, UPPER_BOARD_ID, BOARD_PATH, READ_ROLE, WRITE_ROLE, APPEND_DATE, UPDATE_DATE, APPEND_ADMIN_ID, UPDATE_ADMIN_ID) VALUES (92, '자유게시판', TRUE, TRUE, 1, 92, '/board/92/post/list', 'ADMIN', 'USER', NOW(), NOW(), 99, 99);
INSERT INTO BOARD (BOARD_ID, BOARD_NAME, USE_YN, ATTACHMENT_ALLOW_YN, DEPTH, UPPER_BOARD_ID, BOARD_PATH, READ_ROLE, WRITE_ROLE, APPEND_DATE, UPDATE_DATE, APPEND_ADMIN_ID, UPDATE_ADMIN_ID) VALUES (93, '우리회사 게시판', TRUE, TRUE, 1, 93, '/board/93/post/list', 'USER', 'USER', NOW(), NOW(), 99, 99);
INSERT INTO BOARD (BOARD_ID, BOARD_NAME, USE_YN, ATTACHMENT_ALLOW_YN, DEPTH, UPPER_BOARD_ID, BOARD_PATH, READ_ROLE, WRITE_ROLE, APPEND_DATE, UPDATE_DATE, APPEND_ADMIN_ID, UPDATE_ADMIN_ID) VALUES (94, '부서별 게시판', TRUE, TRUE, 1, 94, '/board/94/post/list', 'USER', 'USER', NOW(), NOW(), 99, 99);
INSERT INTO BOARD (BOARD_ID, BOARD_NAME, USE_YN, ATTACHMENT_ALLOW_YN, DEPTH, UPPER_BOARD_ID, BOARD_PATH, READ_ROLE, WRITE_ROLE, APPEND_DATE, UPDATE_DATE, APPEND_ADMIN_ID, UPDATE_ADMIN_ID) VALUES (95, '자료실', TRUE, TRUE, 1, 95, '/board/95/post/list', 'USER', 'ADMIN', NOW(), NOW(), 99, 99);
INSERT INTO BOARD (BOARD_ID, BOARD_NAME, USE_YN, ATTACHMENT_ALLOW_YN, DEPTH, UPPER_BOARD_ID, BOARD_PATH, READ_ROLE, WRITE_ROLE, APPEND_DATE, UPDATE_DATE, APPEND_ADMIN_ID, UPDATE_ADMIN_ID) VALUES (80, '창비', TRUE, FALSE, 2, 93, '/board/private/company/80/post/list', 'USER', 'USER', NOW(), NOW(), 99, 99);
INSERT INTO BOARD (BOARD_ID, BOARD_NAME, USE_YN, ATTACHMENT_ALLOW_YN, DEPTH, UPPER_BOARD_ID, BOARD_PATH, READ_ROLE, WRITE_ROLE, APPEND_DATE, UPDATE_DATE, APPEND_ADMIN_ID, UPDATE_ADMIN_ID) VALUES (81, '창비교육', TRUE, FALSE, 2, 93, '/board/private/company/81/post/list', 'USER', 'USER', NOW(), NOW(), 99, 99);
INSERT INTO BOARD (BOARD_ID, BOARD_NAME, USE_YN, ATTACHMENT_ALLOW_YN, DEPTH, UPPER_BOARD_ID, BOARD_PATH, READ_ROLE, WRITE_ROLE, APPEND_DATE, UPDATE_DATE, APPEND_ADMIN_ID, UPDATE_ADMIN_ID) VALUES (82, '미디어창비', TRUE, FALSE, 2, 93, '/board/private/company/82/post/list', 'USER', 'USER', NOW(), NOW(), 99, 99);
INSERT INTO BOARD (BOARD_ID, BOARD_NAME, USE_YN, ATTACHMENT_ALLOW_YN, DEPTH, UPPER_BOARD_ID, BOARD_PATH, READ_ROLE, WRITE_ROLE, APPEND_DATE, UPDATE_DATE, APPEND_ADMIN_ID, UPDATE_ADMIN_ID) VALUES (83, '출판', TRUE, FALSE, 2, 94, '/board/private/department/83/post/list', 'USER', 'USER', NOW(), NOW(), 99, 99);
INSERT INTO BOARD (BOARD_ID, BOARD_NAME, USE_YN, ATTACHMENT_ALLOW_YN, DEPTH, UPPER_BOARD_ID, BOARD_PATH, READ_ROLE, WRITE_ROLE, APPEND_DATE, UPDATE_DATE, APPEND_ADMIN_ID, UPDATE_ADMIN_ID) VALUES (84, '마케팅', TRUE, FALSE, 2, 94, '/board/private/department/84/post/list', 'USER', 'USER', NOW(), NOW(), 99, 99);
INSERT INTO BOARD (BOARD_ID, BOARD_NAME, USE_YN, ATTACHMENT_ALLOW_YN, DEPTH, UPPER_BOARD_ID, BOARD_PATH, READ_ROLE, WRITE_ROLE, APPEND_DATE, UPDATE_DATE, APPEND_ADMIN_ID, UPDATE_ADMIN_ID) VALUES (85, '디자인', TRUE, FALSE, 2, 94, '/board/private/department/85/post/list', 'USER', 'USER', NOW(), NOW(), 99, 99);


-- POST DATA IMPORT
INSERT INTO POST (POST_ID, MEMBER_ID, BOARD_ID, POST_TITLE, POST_HEAD, POST_CONTENT, USE_YN, READ_COUNT, RECOMMEND_COUNT, APPEND_DATE, UPDATE_DATE) VALUES (12, 20, 92, 'TEST POST 01', 'GENERAL', 'test post', true, 0, 0, now(), now());
INSERT INTO POST (POST_ID, MEMBER_ID, BOARD_ID, POST_TITLE, POST_HEAD, POST_CONTENT, USE_YN, READ_COUNT, RECOMMEND_COUNT, APPEND_DATE, UPDATE_DATE) VALUES (13, 20, 95, 'TEST POST 02', 'GENERAL', 'test post', true, 0, 0, now(), now());
INSERT INTO POST (POST_ID, MEMBER_ID, BOARD_ID, POST_TITLE, POST_HEAD, POST_CONTENT, USE_YN, READ_COUNT, RECOMMEND_COUNT, APPEND_DATE, UPDATE_DATE) VALUES (10, 20, 91, '창비그룹 노동조합 게시판 알파테스트를 시작합니다.', 'NOTICE', '<h3>창비그룹 노동조합 게시판 알파테스트를 시작합니다.</h3><p><br></p><ul><li><p>기간: 2024.07.22. ~ 2024.07.31.</p></li><li><p>대상: 창비그룹 노동조합 지부원</p></li></ul>', true, 0, 0, now(), now());