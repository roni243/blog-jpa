package shop.mtcoding.blog.love;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.user.User;

import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(
        name = "love_tb",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "board_id"})
        }
)
@Entity
public class Love {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Board board;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Love(int id, User user, Board board, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.board = board;
        this.createdAt = createdAt;
    }
}
