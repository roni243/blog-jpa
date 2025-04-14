package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.reply.Reply;

import java.util.List;

@Import(BoardRepository.class) // BoardRepository
@DataJpaTest // EntityManager, PC
public class BoardRepositoryTest {
    @PersistenceContext
    private EntityManager em;

    @Autowired // DI
    private BoardRepository boardRepository;
    private BoardService boardService;

    @Test
    public void findByIdJoinUserAndReplies_test() {
        // given
        Integer boardId = 4;
        Board board = boardRepository.findByIdJoinUserAndReplies(boardId);


        for (Reply reply : board.getReplies()) {
            System.out.println("-----------------------------------Lazy Loading Select");
            System.out.println(reply.getUser().getUsername());
            System.out.println("------------------------");
        }

    }

    @Test
    public void findById_test() {
        // given
        Integer boardId = 4;
        Board board = boardRepository.findById(boardId);
    }

    @Test
    public void findByIdJoinUser_test() {
        Integer id = 1;

        Board board = boardRepository.findByIdJoinUser(id);
    }

    @Test
    @Transactional
    @Commit
    public void delete_test() {

        Integer id = 1; // 삭제하려는 게시글의 ID

        // 게시글 삭제
        Query q2 = em.createQuery("DELETE FROM Board b WHERE b.id = :id");
        q2.setParameter("id", id);
        q2.executeUpdate();  // 게시글 삭제

        em.flush();  // 세션 캐시를 DB에 반영
        em.clear();  // 캐시 초기화, DB와의 동기화 확인

        // 삭제 후 남아있는 게시글 수 조회
        Long count = em.createQuery("SELECT COUNT(b) FROM Board b", Long.class)
                .getSingleResult();

        System.out.println("남아있는 게시글 수: " + count);
    }


    //        Integer id = 1;
//
//        Query q2 = em.createQuery("DELETE FROM Board b WHERE b.id = :id");
//        q2.setParameter("id", id);
//        q2.executeUpdate();
//        em.flush();
//        em.clear();


    @Test
    public void insert_test() {
        Board newBoard = new Board();
        em.persist(newBoard);

        em.flush();
        em.clear();

        Integer insertedId = newBoard.getId();
        System.out.println("삽입된 게시글 ID: " + insertedId);
    }

    @Test
    public void update_test() {

        Integer id = 1;  // 업데이트하려는 게시글의 ID

        Board existingBoard = em.find(Board.class, id);

        if (existingBoard != null) {

            existingBoard.setTitle("업데이트된 제목");
            existingBoard.setContent("업데이트된 내용입니다.");

            em.merge(existingBoard);

            em.flush();
            em.clear();

            Board updatedBoard = em.find(Board.class, id);

            System.out.println("업데이트된 제목: " + updatedBoard.getTitle());
            System.out.println("업데이트된 내용: " + updatedBoard.getContent());
        } else {
            System.out.println("해당 게시글을 찾을 수 없습니다.");
        }

    }


    @Test
    public void findAll_test() {
        // given
        Integer userId = 1;

        // when
        List<Board> boardList = boardRepository.findAll(userId);

        // eye
        for (Board board : boardList) {
            System.out.print(board.getId() + ", " + board.getTitle());
            System.out.println();
        }

    }

//     Lazy -> Board -> User(id=1)
//     Eager -> N+1  -> Board조회 -> 연관된 User 유저 수 만큼 주회
//     Eager -> Join -> 한방쿼리
//        System.out.println("--------------------");
//        boardList.get(0).getUser().getUsername();
//        System.out.println("--------------------");

}
//}
