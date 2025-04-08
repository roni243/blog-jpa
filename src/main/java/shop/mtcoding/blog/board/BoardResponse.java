package shop.mtcoding.blog.board;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

public class BoardResponse {

    @AllArgsConstructor
    @Data
    public static class DetailDTO {

        private Integer id;
        private String title;
        private String content;
        private Boolean isPublic;
        private Boolean isOwner;
        private String username;
        private Timestamp createdAt;
        private Long loveCount; // 그룹함수로 리턴되는것은 롱타입
        private Boolean isLove;

        //Object배열로

//        public DetailDTO(Board board, Integer sessionUserId, Boolean isLove) {
//            this.id = board.getId();
//            this.title = board.getTitle();
//            this.content = board.getContent();
//            this.isPublic = board.getIsPublic();
//            this.isOwner = sessionUserId == board.getUser().getId();
//            this.username = board.getUser().getUsername();
//            this.createdAt = board.getCreatedAt();
//            //this.isLove = sessionUserId == 1 ? true : false;
//            this.isLove = isLove;
//            this.loveCount = loveCount;
//        }
    }
}
