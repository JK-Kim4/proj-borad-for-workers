package com.changbi.tradeunion.boardforworkers.board.repository;

import com.changbi.tradeunion.boardforworkers.board.domain.Board;
import com.changbi.tradeunion.boardforworkers.board.domain.Post;
import com.changbi.tradeunion.boardforworkers.board.presentation.dto.PostDetailDto;
import com.changbi.tradeunion.boardforworkers.board.presentation.dto.PostListDto;
import com.changbi.tradeunion.boardforworkers.board.presentation.dto.PostSaveDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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

    public void delete(Long boardId) {
        Board board = em.find(Board.class, boardId);
        em.remove(board);
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
        String query = "select b from Board b where b.useYn = TRUE AND b.depth = 1";

        return em.createQuery(query, Board.class)
                .getResultList();
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

    public List<PostListDto> findPostsForClients(Long boardId) {
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

    public PostDetailDto findPostById(Long postId) {
        String query =  "select " +
                            "new com.changbi.tradeunion.boardforworkers.board.presentation.dto.PostDetailDto" +
                            "(" +
                                "p.id, b.id, b.boardName, m.id, m.memberRealName, m.memberNickName," +
                                "p.postHead, p.postTitle, p.postContent, p.useYn, p.readCount, p.recommendCount," +
                                "p.attachmentFileName, p.attachmentFilePath, p.appendDate, p.updateDate" +
                            ") " +
                        "from Post p " +
                        "left outer join Board b on p.boardId = b.id " +
                        "left outer join Member m on p.memberId = m.id " +
                        "where p.id = :postId";

        return em.createQuery(query, PostDetailDto.class)
                .setParameter("postId", postId)
                .getSingleResult();
    }

    public void postUpdate(PostSaveDto dto) {
        Post post = em.find(Post.class, dto.getPostId());
        post.update(dto);
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
}
