package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    // 한방쿼리(h2 query, om -> dto)
    public BoardResponse.DetailDTO findDetail(Integer boardId, Integer userId) {
        return null;
    }


    public Board findByIdJoinUser(Integer id) {
        Query query = em.createQuery("select b from Board b join fetch b.user u where b.id = :id", Board.class);
        query.setParameter("id", id);
        return (Board) query.getSingleResult();
    }

    public Board findById(Integer id) {
        return em.find(Board.class, id); // id로 찾을때 캐싱위해 JPQL안씀
    }

    public List<Board> findAll() {
        String sql = "select b from Board b where b.isPublic = true order by b.id desc";
        Query query = em.createQuery(sql, Board.class);
        return query.getResultList();
    }

    public List<Board> findAll(Integer userId) {
        String s1 = "select b from Board b where b.isPublic = true or b.user.id = :userId order by b.id desc";
        String s2 = "select b from Board b where b.isPublic = true order by b.id desc";

        Query query = null;
        if (userId == null) {
            query = em.createQuery(s2, Board.class);
        } else {
            query = em.createQuery(s1, Board.class);
            query.setParameter("userId", userId);
        }

        return query.getResultList();
    }

    public void save(Board board) {
        em.persist(board);
    }
}
