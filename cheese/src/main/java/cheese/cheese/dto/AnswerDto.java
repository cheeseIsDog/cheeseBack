package cheese.cheese.dto;

import cheese.cheese.entity.Answer;
import cheese.cheese.entity.AnswerChoose;
import cheese.cheese.entity.Comment;
import cheese.cheese.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class AnswerDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class gen{
        private Long questionId;
        private Long userId;
        private String contents;

        public Answer toEntity() {
            return Answer.builder()
                    .questionId(this.questionId)
                    .userId(this.userId)
                    .contents(this.contents)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class req{
        private Long questionId;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class chooseAnswer{
        private Long answerId;
        private Long userId;

        public AnswerChoose toEntity() {
            return AnswerChoose.builder()
                    .answerId(this.answerId)
                    .userId(this.userId)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class modifyChooseAnswer{
        private Long answerId;
        private Long userId;
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
        private Boolean isChosen;
        private UserDto.res user;
        private List<CommentDto.res> comments = new ArrayList<>();

        public res(Answer answer, AnswerChoose answerChoose, User user) {
            this.answerId = answer.getAnswerId();
            this.userId = answer.getUserId();
            this.isChosen = answerChoose != null;
            this.contents = answer.getContents();
            this.user = user.toDto();
        }
    }
}
