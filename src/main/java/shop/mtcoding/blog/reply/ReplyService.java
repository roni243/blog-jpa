package shop.mtcoding.blog.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.error.ex.Exception403;
import shop.mtcoding.blog.user.User;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;

    @Transactional
    public void 댓글쓰기(ReplyRequest.SaveDTO reqDTO, User sessionUser) {
        replyRepository.save(reqDTO.toEntity(sessionUser));
    }

    public Integer 댓글삭제(Integer id, Integer sessionUserId) {
        Reply replyPS = replyRepository.findById(id);

        // Exception404
        if (replyPS == null) throw new RuntimeException("댓글 없는데 삭제");


        if (!replyPS.getUser().getId().equals(sessionUserId)) {
            throw new Exception403("니 댓글 아닌데?");
        }

        int boardId = replyPS.getBoard().getId();

        replyRepository.deleteById(id);

        return boardId;
    }
}
