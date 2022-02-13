package cheese.cheese.dto;


import cheese.cheese.dto.Enum.YN;
import cheese.cheese.entity.Question;
import cheese.cheese.entity.User;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        private List<String> tags;

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
        private Long offset;
        private Long limit;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class searchReq{
        private Long schoolId;
        private String title;
        private Long offset;
        private Long limit;
    }

    @Getter
    @NoArgsConstructor
    public static class res{
        private Long questionId;
        private UserDto.res user;
        private Long schoolId;
        private String title;
        private String contents;
        private List<TagDto.res> tagList = new ArrayList<>();
        private Integer likes;
        private Integer dislikes;
        private Long duringTime;
        private YN solved_YN;

        public res(Question question, User user) {
            this.questionId = question.getQuestionId();
            this.user = user.toDto();
            this.schoolId = question.getSchoolId();
            this.title = question.getTitle();
            this.contents = question.getContents();
            this.likes = question.getLikes();
            this.dislikes = question.getDislikes();
            this.duringTime = Duration.between(question.getCreatedDate(), LocalDateTime.now()).getSeconds();
            this.solved_YN = question.getSolved_YN();
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class reqOfUserQuestions{
        private Long userId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class resOfUserQuestions{
        private Integer totalQuestions;
        private Integer solvedQuestions;
        private Integer unSolvedQuestions;
        private Integer totalAnswers;

        public resOfUserQuestions(Integer totalQuestions, Integer solvedQuestions) {
            this.totalQuestions = totalQuestions;
            this.solvedQuestions = solvedQuestions;
            this.unSolvedQuestions = totalQuestions - solvedQuestions;
            this.totalAnswers = 0;
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class reqOfSchoolQuestions{
        private Long schoolId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class resOfSchoolQuestions{
        private Integer totalQuestions;
        private Integer solvedQuestions;
        private Integer unSolvedQuestions;

        public resOfSchoolQuestions(Integer totalQuestions, Integer solvedQuestions) {
            this.totalQuestions = totalQuestions;
            this.solvedQuestions = solvedQuestions;
            this.unSolvedQuestions = totalQuestions - solvedQuestions;
        }
    }
}
