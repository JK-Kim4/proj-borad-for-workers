package com.changbi.tradeunion.boardforworkers.board.repository;

import com.changbi.tradeunion.boardforworkers.board.domain.Board;
import com.changbi.tradeunion.boardforworkers.board.domain.Post;
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

    public void delete(Board board) {
        em.remove(board);
    }

    public Board findById(Long boardId) {
        String query = "select b from Board b where b.id = :boardId";

        // throw (NoResult | NonUnique) Exception
        return em.createQuery(query, Board.class)
                .setParameter("boardId", boardId)
                .getSingleResult();
    }

    public List<Board> findBoards(){
        String query = "select b from Board b";

        return em.createQuery(query, Board.class)
                .getResultList();
    }

    public List<Post> findPosts(Long boardId){
        String query = "select p from Post p where p.boardId = :boardId";

        return em.createQuery(query, Post.class)
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

    public Post findPostById(Long postId) {
        String query = "select p from Post p where p.id = :postId";

        return em.createQuery(query, Post.class)
                .setParameter("postId", postId)
                .getSingleResult();
    }
}
