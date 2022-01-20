package cheese.cheese.dto;

import cheese.cheese.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class CommentDto {

    @Getter
    @AllArgsConstructor
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
}
