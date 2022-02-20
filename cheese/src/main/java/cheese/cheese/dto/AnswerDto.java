package cheese.cheese.dto;

import cheese.cheese.entity.Answer;
import cheese.cheese.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AnswerDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class gen{
        private Long questionsId;
        private Long userId;
        private String contents;

        public Answer toEntity() {
            return Answer.builder()
                    .questionId(this.questionsId)
                    .userId(this.userId)
                    .contents(this.contents)
                    .build();
        }
    }
}
