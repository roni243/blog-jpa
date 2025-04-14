package shop.mtcoding.blog.board.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shop.mtcoding.blog.user.UserRepository;


@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findAll_test() {
        Integer userId = 1;
        userRepository.findById(userId);
    }
}
