package cheese.cheese.dto;


import cheese.cheese.entity.Question;
import cheese.cheese.entity.Tag;
import cheese.cheese.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

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

        public List<Tag> getTagEntities(Long questionId) {
            return tags.stream().map(t -> new Tag(questionId, t)).collect(Collectors.toList());
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
        private UserDto.res user;
        private Long schoolId;
        private String title;
        private String contents;
        private List<TagDto.res> tagList;
        private Integer likes;
        private Integer dislikes;
        private Integer duringTime;

        public res(Question question, User user, List<Tag> tag) {
            this.questionId = question.getQuestionId();
            this.user = user.toDto();
            this.schoolId = question.getSchoolId();
            this.title = question.getTitle();
            this.contents = question.getContents();
            this.likes = question.getLikes();
            this.dislikes = question.getDislikes();
            this.tagList = tag
                    .stream()
                    .map(element -> element.toDto())
                    .collect(Collectors.toList());
            this.duringTime = question.getCreatedDate().truncatedTo(ChronoUnit.MINUTES)
                    .compareTo(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        }
    }
}
