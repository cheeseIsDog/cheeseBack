package cheese.cheese.dto;


import cheese.cheese.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class QuestionDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class gen{
        private Long userId;
        private Long schoolId;
        private String title;
        private String contents;

        public Question toEntity() {
            return Question.builder()
                    .userId(this.userId)
                    .schoolId(this.schoolId)
                    .title(this.title)
                    .contents(this.contents)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class req{
        private Long schoolId;
    }

    @Getter
    @NoArgsConstructor
    public static class res{
        private Long questionId;
        private Long userId;
        private Long schoolId;
        private String title;
        private String contents;
        private Integer likes;
        private Integer dislikes;

        public res(Question question) {
            this.questionId = question.getQuestionId();
            this.userId = question.getUserId();
            this.schoolId = question.getSchoolId();
            this.title = question.getTitle();
            this.contents = question.getContents();
            this.likes = question.getLikes();
            this.dislikes = question.getDislikes();
        }
    }
}
