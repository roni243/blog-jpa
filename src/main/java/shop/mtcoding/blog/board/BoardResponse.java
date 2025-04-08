package shop.mtcoding.blog.board;

import lombok.Data;

import java.sql.Timestamp;

public class BoardResponse {

    @Data
    public static class DetailDTO {

        private Integer id;
        private String title;
        private String content;
        private Boolean isPublic;
        private Boolean isOwner;
        private Boolean isLove;
        private Integer loveCount;
        private String username;
        private Timestamp createdAt;
        private Integer loveId;

        public DetailDTO(Board board, Integer sessionUserId, Boolean isLove, Integer loveCount, Integer loveId) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.isPublic = board.getIsPublic();
            this.isOwner = sessionUserId == board.getUser().getId();
            this.username = board.getUser().getUsername();
            this.createdAt = board.getCreatedAt();
            //this.isLove = sessionUserId == 1 ? true : false;
            this.isLove = isLove;
            this.loveCount = loveCount;
            this.loveId = loveId;
        }
    }
}
