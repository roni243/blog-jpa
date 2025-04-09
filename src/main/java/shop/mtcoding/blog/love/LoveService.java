package shop.mtcoding.blog.love;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LoveService {
    private final LoveRepository loveRepository;


    @Transactional
    public LoveResponse.SaveDTO 좋아요(LoveRequest.SaveDTO reqDTO, Integer sessionUserId) {
        Love lovePS = loveRepository.save(reqDTO.toEntity(sessionUserId));
        Long loveCount = loveRepository.findByBoardId(reqDTO.getBoardId());
        return new LoveResponse.SaveDTO(lovePS.getId(), loveCount.intValue());
    }

    @Transactional
    public LoveResponse.DeleteDTO 좋아요취소(Integer id) {

        Love lovePS = loveRepository.findById(id);
        if (lovePS == null) throw new RuntimeException("좋아요 안했는데 왜 취소를 하려고 해");

        //권한체크 (lovePS.getUser().getID())를 세션유저아이디와 비교 Integer객체니 equals로 비교
        // 안될때 터트리기

        Integer boardId = lovePS.getBoard().getId();

        loveRepository.deleteById(id);
        Long loveCount = loveRepository.findByBoardId(boardId);
        return new LoveResponse.DeleteDTO(loveCount.intValue());
    }
}
