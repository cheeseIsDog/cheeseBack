package cheese.cheese.dto;


import cheese.cheese.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class QuestionDto {

    @Getter
    @AllArgsConstructor
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
    public static class req{
        private Long schoolId;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class res{
        private Long questionId;
        private Long userId;
        private Long schoolId;
        private String title;
        private String contents;
        private Integer likes;
        private Integer dislikes;
    }
}
