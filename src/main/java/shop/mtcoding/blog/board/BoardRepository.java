package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    public Board findByIdJoinUserAndReplies(Integer id) {
        Query query = em.createQuery("select b from Board b join fetch b.user left join fetch b.replies r left join fetch r.user where b.id = :id order by r.id desc", Board.class);
        query.setParameter("id", id);
        return (Board) query.getSingleResult();
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
        String sql = "select b from Board b where b.isPublic = true or b.user.id = :userId order by b.id desc";
        Query query = em.createQuery(sql, Board.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    public void save(Board board) {
        em.persist(board);
    }

    @Transactional
    public void deleteById(Integer id) {
        Query q1 = em.createQuery("DELETE FROM Reply r WHERE r.board.id = :id");
        q1.setParameter("id", id);
        q1.executeUpdate();
        
        Query q2 = em.createQuery("DELETE FROM Board b WHERE b.id = :id");
        q2.setParameter("id", id);
        q2.executeUpdate();
    }
}
