package com.changbi.tradeunion.boardforworkers.board.repository;

import com.changbi.tradeunion.boardforworkers.board.domain.Board;
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
        return em.find(Board.class, boardId);
    }

    public List<Board> findBoards(){
        String query = "select b from Board b";

        return em.createQuery(query, Board.class)
                .getResultList();
    }



    public boolean isAlreadyExistBoardName(String boardName) {
        String query = "select count(b) > 0 from Board b where b.boardName = :boardName";

        return em.createQuery(query, Boolean.class)
                .setParameter("boardName", boardName)
                .getSingleResult();
    }
}
