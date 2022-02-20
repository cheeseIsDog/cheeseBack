package cheese.cheese.dto;

import cheese.cheese.entity.Comment;
import cheese.cheese.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CommentDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class gen{
        private Long answerId;
        private Long userId;
        private String contents;

        public Comment toEntity() {
            return Comment.builder()
                    .answerId(this.answerId)
                    .userId(this.userId)
                    .contents(this.contents)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class req{
        private Long answerId;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class res{
        private Long answerId;
        private Long userId;
        private String contents;
        private Integer likes;
        private Integer dislikes;
        private UserDto.res user;

        public res(Comment comment, User user) {
            this.answerId = comment.getAnswerId();
            this.userId = comment.getUserId();
            this.contents = comment.getContents();
            this.likes = comment.getLikes();
            this.dislikes = comment.getDislikes();
            this.user = user.toDto();
        }
    }
}
