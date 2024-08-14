package com.changbi.tradeunion.boardforworkers.board.repository;

import com.changbi.tradeunion.boardforworkers.board.domain.*;
import com.changbi.tradeunion.boardforworkers.board.exception.PostIllegalArgumentException;
import com.changbi.tradeunion.boardforworkers.board.presentation.dto.CommentDetailDto;
import com.changbi.tradeunion.boardforworkers.board.presentation.dto.PostDetailDto;
import com.changbi.tradeunion.boardforworkers.board.presentation.dto.PostListDto;
import com.changbi.tradeunion.boardforworkers.board.presentation.dto.PostSaveDto;
import com.changbi.tradeunion.boardforworkers.common.CommonValues;
import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.PostHead;
import com.changbi.tradeunion.boardforworkers.common.dto.Pagination;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Board board) {
        em.persist(board);
        return board.getId();
    }

    public Long saveAttachment(Attachment attachment) {
        em.persist(attachment);
        return attachment.getId();
    }

    public void delete(Long boardId) {
        Board board = em.find(Board.class, boardId);
        em.remove(board);
        em.flush();
    }

    public void deletePost(Long postId) {
        Post post = em.find(Post.class, postId);
        em.remove(post);
    }

    public Board findById(Long boardId) {
        String query = "select b from Board b where b.id = :boardId";

        // throw (NoResult | NonUnique) Exception
        return em.createQuery(query, Board.class)
                .setParameter("boardId", boardId)
                .getSingleResult();
    }

    public List<Board> findBoards(){
        String query = "select b from Board b where b.depth = 1 order by b.id";

        return em.createQuery(query, Board.class)
                .getResultList();
    }

    public List<Board> findBoardsForClient(){
        String query = "select b from Board b where b.useYn = TRUE AND b.depth = 1 order by b.id";

        return em.createQuery(query, Board.class)
                .getResultList();
    }

    public Post findPostById(Long postId){
        return em.find(Post.class, postId);
    }

    public List<PostListDto> findPosts(Long boardId){
        String query =  "select " +
                            "new com.changbi.tradeunion.boardforworkers.board.presentation.dto.PostListDto" +
                            "(p.id, b.id, m.id, " +
                            "p.useYn, p.postHead, p.postTitle, " +
                            "p.readCount, p.recommendCount, p.appendDate, p.updateDate," +
                            "m.memberRealName, b.boardName) " +
                        "from Post p " +
                        "left outer join Member m on p.memberId = m.id " +
                        "left outer join Board b on p.boardId = b.id " +
                        "where p.boardId = :boardId";

        return em.createQuery(query, PostListDto.class)
                .setParameter("boardId", boardId)
                .getResultList();
    }

    public List<PostListDto> findPostsForClient(Long boardId, Pagination pagination) {
        String query =  "select " +
                            "new com.changbi.tradeunion.boardforworkers.board.presentation.dto.PostListDto" +
                            "(" +
                                "p.id, b.id, m.id, " +
                                "p.useYn, p.postHead, p.postTitle, " +
                                "p.readCount, p.recommendCount, p.appendDate, p.updateDate," +
                                "m.memberRealName, b.boardName" +
                            ") " +
                        "from Post p " +
                        "left outer join Member m on p.memberId = m.id " +
                        "left outer join Board b on p.boardId = b.id " +
                        "where p.useYn = true " +
                        "and p.boardId = :boardId " +
                        "order by p.appendDate desc";

        return em.createQuery(query, PostListDto.class)
                .setParameter("boardId", boardId)
                .setFirstResult(pagination.getPageNum() * pagination.getPageSize())
                .setMaxResults(pagination.getPageSize())
                .getResultList();
    }


    public boolean isAlreadyExistBoardName(String boardName) {
        String query = "select count(b) > 0 from Board b where b.boardName = :boardName";

        return em.createQuery(query, Boolean.class)
                .setParameter("boardName", boardName)
                .getSingleResult();
    }

    public Board findByBoardName(String boardName) {
        String query = "select b from Board b where b.boardName = :boardName";

        return em.createQuery(query, Board.class).setParameter("boardName", boardName).getSingleResult();
    }

    public Long savePost(Post post) {
        em.persist(post);
        return post.getId();
    }

    public PostDetailDto findPostDetailById(Long postId) {
        String query =  "select " +
                            "new com.changbi.tradeunion.boardforworkers.board.presentation.dto.PostDetailDto" +
                            "(" +
                                "p.id, b.id, b.boardName, m.id, m.memberRealName, m.memberNickName," +
                                "p.postHead, p.postTitle, p.postContent, p.useYn, p.readCount, p.recommendCount," +
                                "p.attachmentId, a.fileOriginalName, a.fileSize, a.filePath, p.appendDate, p.updateDate" +
                            ") " +
                        "from Post p " +
                        "left outer join Board b on p.boardId = b.id " +
                        "left outer join Member m on p.memberId = m.id " +
                        "left outer join Attachment a on p.attachmentId = a.id " +
                        "where p.id = :postId";

        return em.createQuery(query, PostDetailDto.class)
                .setParameter("postId", postId)
                .getSingleResult();
    }

    public void postUpdate(PostSaveDto dto){
        try {
            Post post = em.find(Post.class, dto.getPostId());
            post.update(dto);
        }catch (Exception e){
            throw new PostIllegalArgumentException(CommonValues.RESULT_MESSAGE_FAIL_POST_ILLEGAL_ARGUMENT, dto.getBoardId(), dto.getPostId());
        }

    }

    public void updatePostReadCount(Long postId) {
        Post post = em.find(Post.class, postId);
        post.updateReadCount();
    }

    public int updatePostRecommendCount(Long postId) {
        Post post = em.find(Post.class, postId);
        post.updateRecommendCount();
        return post.getRecommendCount();
    }

    public Board findParentBoard(Long upperBoardId) {
        String query = "select b " +
                "from Board b " +
                "where b.depth = 1 and b.id = :upperBoardId";

        return em.createQuery(query, Board.class).setParameter("upperBoardId", upperBoardId).getSingleResult();
    }

    public List<Board> findChildBoardList(Long boardId) {
        String query =  "select b " +
                        "From Board b " +
                        "where b.depth = 2 " +
                        "and b.upperBoardId = :boardId " +
                        "order by b.id asc";

        return em.createQuery(query, Board.class)
                .setParameter("boardId", boardId)
                .getResultList();
    }

    public PostDetailDto findMostRecentNoticePost() throws NoResultException, NonUniqueResultException {
        String query =  "select " +
                            "new com.changbi.tradeunion.boardforworkers.board.presentation.dto.PostDetailDto" +
                            "(" +
                                "p.id, b.id, b.boardName, m.id, m.memberRealName, m.memberNickName," +
                                "p.postHead, p.postTitle, p.postContent, p.useYn, p.readCount, p.recommendCount," +
                                "p.attachmentId, a.fileName, a.filePath, a.fileSize, p.appendDate, p.updateDate" +
                            ") " +
                        "from Post p " +
                        "left outer join Board b on p.boardId = b.id " +
                        "left outer join Member m on p.memberId = m.id " +
                        "left outer join Attachment a on p.attachmentId = a.id " +
                        "where p.boardId = 91 " +
                        "and p.postHead = :noticeHead " +
                        "order by p.appendDate desc limit 1";

        return em.createQuery(query, PostDetailDto.class)
                .setParameter("noticeHead", PostHead.NOTICE)
                .getSingleResult();
    }

    public PostDetailDto findMostPopularPost() throws NoResultException, NonUniqueResultException{
        String query =  "select " +
                            "new com.changbi.tradeunion.boardforworkers.board.presentation.dto.PostDetailDto" +
                            "(" +
                                "p.id, b.id, b.boardName, m.id, m.memberRealName, m.memberNickName," +
                                "p.postHead, p.postTitle, p.postContent, p.useYn, p.readCount, p.recommendCount," +
                                "p.attachmentId, a.fileName, a.filePath, a.fileSize, p.appendDate, p.updateDate" +
                            ") " +
                        "from Post p " +
                        "left outer join Board b on p.boardId = b.id " +
                        "left outer join Member m on p.memberId = m.id " +
                        "left outer join Attachment a on p.attachmentId = a.id " +
                        "where b.id not in (91, 93, 94) " +
                        "order by p.recommendCount desc, p.readCount desc, p.id desc limit 1";


        return em.createQuery(query, PostDetailDto.class).getSingleResult();
    }

    public Long findAuthorIdByPostId(Long postId) {
        String query = "select p.memberId From Post p where p.id = :postId";

        return em.createQuery(query, Long.class)
                .setParameter("postId", postId)
                .getSingleResult();
    }

    public List<PostListDto> findPostsByMemberId(Long memberId) {
        String query =  "select " +
                            "new com.changbi.tradeunion.boardforworkers.board.presentation.dto.PostListDto" +
                                "(" +
                                    "p.id, b.id, m.id, " +
                                    "p.useYn, p.postHead, p.postTitle, " +
                                    "p.readCount, p.recommendCount, p.appendDate, p.updateDate," +
                                    "m.memberRealName, b.boardName" +
                                ") " +
                        "from Post p " +
                        "left outer join Member m on p.memberId = m.id " +
                        "left outer join Board b on p.boardId = b.id " +
                        "where p.memberId = :memberId";

        return em.createQuery(query, PostListDto.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    public Long getPostTotalCount() {
        String query = "select count(*) from Post p where p.useYn = true";

        return em.createQuery(query, Long.class).getSingleResult();
    }

    public Long reportPost(Report report) {
        em.persist(report);
        if( this.getReportCount(report.getPostId()) >= 5){
            em.find(Post.class, report.getPostId()).updateUseYn();
        }
        em.flush();
        return report.getId();
    }

    public Report findReportById(Report report) throws EmptyResultDataAccessException {
        String query = "select r " +
                "from Report r " +
                "where r.postId = :postId " +
                "and r.memberId = :memberId";

        return em.createQuery(query, Report.class)
                .setParameter("postId", report.getPostId())
                .setParameter("memberId", report.getMemberId())
                .getSingleResult();
    }

    public CommentDetailDto findCommentById(Long commentId) {
        String query =  "select " +
                            "new com.changbi.tradeunion.boardforworkers.board.presentation.dto.CommentDetailDto" +
                            "(c.id, c.postId, c.memberId, c.parentCommentId, c.commentValue) " +
                        "from Comment c " +
                        "where c.id = :commentId";

        return em.createQuery(query, CommentDetailDto.class)
                .setParameter("commentId", commentId)
                .getSingleResult();
    }

    /*private method*/
    private Long getReportCount(Long postId) {
        String query = "select count(r) from Report r " +
                "where r.postId = :postId";

        return em.createQuery(query, Long.class)
                .setParameter("postId", postId)
                .getSingleResult();
    }

    public Attachment findAttachmentById(Long attachmentId) {
        return em.find(Attachment.class, attachmentId);
    }

    public Long saveComment(Comment comment) {
        em.persist(comment);
        return comment.getId();
    }

}
