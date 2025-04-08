package shop.mtcoding.blog.love;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoveService {
    private final LoveRepository loveRepository;

    public void 좋아요(Love love) {

    }
}
