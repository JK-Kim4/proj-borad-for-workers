-- MEMBER DATA IMPORT
INSERT INTO MEMBER (MEMBER_ID, MEMBER_EMAIL, MEMBER_REAL_NAME, MEMBER_NICK_NAME, MEMBER_PASSWORD, COMPANY, DEPARTMENT, ROLE, APPEND_DATE, UPDATE_DATE) VALUES (99, 'test@changbi.com', '김창비', '안녕하세요', '$2a$10$/Qg3qFcKuGDqlfKZJ0qy2OEEiPEHyu5y5MOzcNhUCbLmEAzQ19GAm', 'CHANGBI', 'HR', 'SUPER_ADMIN', NOW(), NOW());
INSERT INTO MEMBER (MEMBER_ID, MEMBER_EMAIL, MEMBER_REAL_NAME, MEMBER_NICK_NAME, MEMBER_PASSWORD, COMPANY, DEPARTMENT, ROLE, APPEND_DATE, UPDATE_DATE) VALUES (98, 'changbi@changbi.com', '안비창', '안비창', '$2a$10$/Qg3qFcKuGDqlfKZJ0qy2OEEiPEHyu5y5MOzcNhUCbLmEAzQ19GAm', 'CHANGBI_EDU', 'PUBLISHING', 'USER', NOW(), NOW());
INSERT INTO MEMBER (MEMBER_ID, MEMBER_EMAIL, MEMBER_REAL_NAME, MEMBER_NICK_NAME, MEMBER_PASSWORD, COMPANY, DEPARTMENT, ROLE, APPEND_DATE, UPDATE_DATE) VALUES (97, 'dev@changbi.com', '개발팀아무개', '😄', '$2a$10$/Qg3qFcKuGDqlfKZJ0qy2OEEiPEHyu5y5MOzcNhUCbLmEAzQ19GAm', 'MEDIA_CHANGBI', 'IT', 'USER', NOW(), NOW());


-- BOARD DATA IMPORT
-- INSERT INTO BOARD (BOARD_ID, BOARD_NAME, USE_YN, ATTACHMENT_ALLOW_YN, DEPTH, UPPER_BOARD_ID, BOARD_PATH, READ_ROLE, WRITE_ROLE, APPEND_DATE, UPDATE_DATE, APPEND_ADMIN_ID, UPDATE_ADMIN_ID) VALUES (90, '소개', TRUE, TRUE, 1, 90, '/board/90/post/list', 'GUEST', 'SUPER_ADMIN', NOW(), NOW(), 99, 99);
INSERT INTO BOARD (BOARD_ID, BOARD_NAME, USE_YN, ATTACHMENT_ALLOW_YN, DEPTH, UPPER_BOARD_ID, BOARD_PATH, READ_ROLE, WRITE_ROLE, APPEND_DATE, UPDATE_DATE, APPEND_ADMIN_ID, UPDATE_ADMIN_ID) VALUES (91, '소식', TRUE, TRUE, 1, 91, '/board/91/post/list', 'USER', 'ADMIN', NOW(), NOW(), 99, 99);
INSERT INTO BOARD (BOARD_ID, BOARD_NAME, USE_YN, ATTACHMENT_ALLOW_YN, DEPTH, UPPER_BOARD_ID, BOARD_PATH, READ_ROLE, WRITE_ROLE, APPEND_DATE, UPDATE_DATE, APPEND_ADMIN_ID, UPDATE_ADMIN_ID) VALUES (92, '자유게시판', TRUE, TRUE, 1, 92, '/board/92/post/list', 'ADMIN', 'USER', NOW(), NOW(), 99, 99);
INSERT INTO BOARD (BOARD_ID, BOARD_NAME, USE_YN, ATTACHMENT_ALLOW_YN, DEPTH, UPPER_BOARD_ID, BOARD_PATH, READ_ROLE, WRITE_ROLE, APPEND_DATE, UPDATE_DATE, APPEND_ADMIN_ID, UPDATE_ADMIN_ID) VALUES (93, '우리회사 게시판', TRUE, TRUE, 1, 93, '/board/93/post/list', 'USER', 'USER', NOW(), NOW(), 99, 99);
INSERT INTO BOARD (BOARD_ID, BOARD_NAME, USE_YN, ATTACHMENT_ALLOW_YN, DEPTH, UPPER_BOARD_ID, BOARD_PATH, READ_ROLE, WRITE_ROLE, APPEND_DATE, UPDATE_DATE, APPEND_ADMIN_ID, UPDATE_ADMIN_ID) VALUES (94, '부서별 게시판', TRUE, TRUE, 1, 94, '/board/94/post/list', 'USER', 'USER', NOW(), NOW(), 99, 99);
INSERT INTO BOARD (BOARD_ID, BOARD_NAME, USE_YN, ATTACHMENT_ALLOW_YN, DEPTH, UPPER_BOARD_ID, BOARD_PATH, READ_ROLE, WRITE_ROLE, APPEND_DATE, UPDATE_DATE, APPEND_ADMIN_ID, UPDATE_ADMIN_ID) VALUES (95, '자료실', TRUE, TRUE, 1, 95, '/board/95/post/list', 'USER', 'ADMIN', NOW(), NOW(), 99, 99);
INSERT INTO BOARD (BOARD_ID, BOARD_NAME, USE_YN, ATTACHMENT_ALLOW_YN, DEPTH, UPPER_BOARD_ID, BOARD_PATH, READ_ROLE, WRITE_ROLE, APPEND_DATE, UPDATE_DATE, APPEND_ADMIN_ID, UPDATE_ADMIN_ID) VALUES (80, '창비', TRUE, TRUE, 2, 93, '/board/private/company/80/post/list', 'USER', 'USER', NOW(), NOW(), 99, 99);
INSERT INTO BOARD (BOARD_ID, BOARD_NAME, USE_YN, ATTACHMENT_ALLOW_YN, DEPTH, UPPER_BOARD_ID, BOARD_PATH, READ_ROLE, WRITE_ROLE, APPEND_DATE, UPDATE_DATE, APPEND_ADMIN_ID, UPDATE_ADMIN_ID) VALUES (81, '창비교육', TRUE, TRUE, 2, 93, '/board/private/company/81/post/list', 'USER', 'USER', NOW(), NOW(), 99, 99);
INSERT INTO BOARD (BOARD_ID, BOARD_NAME, USE_YN, ATTACHMENT_ALLOW_YN, DEPTH, UPPER_BOARD_ID, BOARD_PATH, READ_ROLE, WRITE_ROLE, APPEND_DATE, UPDATE_DATE, APPEND_ADMIN_ID, UPDATE_ADMIN_ID) VALUES (82, '미디어창비', TRUE, TRUE, 2, 93, '/board/private/company/82/post/list', 'USER', 'USER', NOW(), NOW(), 99, 99);
INSERT INTO BOARD (BOARD_ID, BOARD_NAME, USE_YN, ATTACHMENT_ALLOW_YN, DEPTH, UPPER_BOARD_ID, BOARD_PATH, READ_ROLE, WRITE_ROLE, APPEND_DATE, UPDATE_DATE, APPEND_ADMIN_ID, UPDATE_ADMIN_ID) VALUES (83, '편집', TRUE, TRUE, 2, 94, '/board/private/department/83/post/list', 'USER', 'USER', NOW(), NOW(), 99, 99);
INSERT INTO BOARD (BOARD_ID, BOARD_NAME, USE_YN, ATTACHMENT_ALLOW_YN, DEPTH, UPPER_BOARD_ID, BOARD_PATH, READ_ROLE, WRITE_ROLE, APPEND_DATE, UPDATE_DATE, APPEND_ADMIN_ID, UPDATE_ADMIN_ID) VALUES (84, '마케팅', TRUE, TRUE, 2, 94, '/board/private/department/84/post/list', 'USER', 'USER', NOW(), NOW(), 99, 99);
INSERT INTO BOARD (BOARD_ID, BOARD_NAME, USE_YN, ATTACHMENT_ALLOW_YN, DEPTH, UPPER_BOARD_ID, BOARD_PATH, READ_ROLE, WRITE_ROLE, APPEND_DATE, UPDATE_DATE, APPEND_ADMIN_ID, UPDATE_ADMIN_ID) VALUES (85, '디자인', TRUE, TRUE, 2, 94, '/board/private/department/85/post/list', 'USER', 'USER', NOW(), NOW(), 99, 99);
INSERT INTO BOARD (BOARD_ID, BOARD_NAME, USE_YN, ATTACHMENT_ALLOW_YN, DEPTH, UPPER_BOARD_ID, BOARD_PATH, READ_ROLE, WRITE_ROLE, APPEND_DATE, UPDATE_DATE, APPEND_ADMIN_ID, UPDATE_ADMIN_ID) VALUES (86, '인사', TRUE, TRUE, 2, 94, '/board/private/department/86/post/list', 'USER', 'USER', NOW(), NOW(), 99, 99);


-- POST DATA IMPORT
INSERT INTO POST (POST_ID, MEMBER_ID, BOARD_ID, POST_TITLE, POST_HEAD, POST_CONTENT, USE_YN, READ_COUNT, RECOMMEND_COUNT, APPEND_DATE, UPDATE_DATE) VALUES (99, 99, 92, 'TEST POST 01', 'GENERAL', 'test post', true, 0, 0, now(), now());
INSERT INTO POST (POST_ID, MEMBER_ID, BOARD_ID, POST_TITLE, POST_HEAD, POST_CONTENT, USE_YN, READ_COUNT, RECOMMEND_COUNT, APPEND_DATE, UPDATE_DATE) VALUES (100, 99, 95, 'TEST POST 01', 'GENERAL', 'test post', true, 0, 0, now(), now());