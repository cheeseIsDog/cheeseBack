package cheese.cheese.dto;


import cheese.cheese.dto.Enum.YN;
import cheese.cheese.entity.Question;
import cheese.cheese.entity.QuestionLikeDislike;
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
    @Builder
    public static class req{
        private Long schoolId;
        private Long offset;
        private Long limit;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class reqById{
        private Long questionId;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class searchReqByTitle{
        private Long schoolId;
        private String title;
        private Long offset;
        private Long limit;
    }


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class searchReqByTag{
        private Long schoolId;
        private String tagName;
        private Long offset;
        private Long limit;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class reqOfDetail{
        private Long questionId;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class questionLikeDislike{
        private Long questionId;
        private Long userId;
        private Boolean likes;
        private Boolean dislikes;

        public questionLikeDislike(QuestionLikeDislike questionLikeDislike) {
            this.questionId = questionLikeDislike.getQuestionId();
            this.userId = questionLikeDislike.getQuestionId();
            this.likes = questionLikeDislike.getLikes();
            this.dislikes = questionLikeDislike.getDislikes();
        }

        public QuestionLikeDislike toEntity() {
            return QuestionLikeDislike.builder()
                    .questionId(this.questionId)
                    .userId(this.userId)
                    .likes(this.likes)
                    .dislikes(this.dislikes)
                    .build();
        }
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
        private Integer likes = 0;
        private Integer dislikes = 0;
        private Long duringTime;
        private YN solved_YN;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;

        @Builder
        public res(Question question, User user) {
            this.questionId = question.getQuestionId();
            this.user = user.toDto();
            this.schoolId = question.getSchoolId();
            this.title = question.getTitle();
            this.contents = question.getContents();
            this.duringTime = Duration.between(question.getCreatedDate(), LocalDateTime.now()).getSeconds();
            this.solved_YN = question.getSolved_YN();
            this.createdDate = question.getCreatedDate();
            this.modifiedDate = question.getModifiedDate();
        }

        public void setLikesDislikes(List<QuestionDto.questionLikeDislike> list) {

            list.forEach(dto -> {
                if (dto.getLikes()) {
                    this.likes++;
                } else {
                    this.dislikes++;
                }
            });
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

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class resOfDetail{
        String contents;
    }
}
