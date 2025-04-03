package shop.mtcoding.blog.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public void 회원가입(UserRequest.JoinDTO joinDTO) {
        User user = userRepository.findByUsername(joinDTO.getUsername());

        if (user != null) throw new RuntimeException("동일한 username이 존재합니다");

        userRepository.save(joinDTO.getUsername(), joinDTO.getPassword(), joinDTO.getEmail());
    }

    public User 로그인(UserRequest.LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername());

        if (user == null) {
            throw new RuntimeException("해당 유저네임이 없습니다");
        }

        if(!(user.getPassword().equals(loginDTO.getPassword()))) {
            throw new RuntimeException("해당 패스워드가 틀렸습니다");
        }

        return user;
    }

}
