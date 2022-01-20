package cheese.cheese.dto;

import cheese.cheese.entity.Comment;
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
        private String contents;

        public Comment toEntity() {
            return Comment.builder()
                    .answerId(this.answerId)
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
        private String contents;
        private Integer likes;
        private Integer dislikes;

        public res(Comment comment) {
            this.answerId = comment.getAnswerId();
            this.contents = comment.getContents();
            this.likes = comment.getLikes();
            this.dislikes = comment.getDislikes();
        }
    }
}
