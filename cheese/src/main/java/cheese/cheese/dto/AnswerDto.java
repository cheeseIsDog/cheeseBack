package cheese.cheese.dto;

import cheese.cheese.dto.Enum.YN;
import cheese.cheese.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    public static class answerLikeDislike{
        private Long answerId;
        private Long userId;
        private Boolean like;
        private Boolean dislike;

        public answerLikeDislike(AnswerLikeDislike answerLikeDislike) {
            this.answerId = answerLikeDislike.getAnswerId();
            this.userId = answerLikeDislike.getUserId();
            this.like = answerLikeDislike.getLikes();
            this.dislike = answerLikeDislike.getDislikes();
        }

        public AnswerLikeDislike toEntity() {
            return AnswerLikeDislike.builder()
                    .answerId(this.answerId)
                    .userId(this.userId)
                    .likes(this.like)
                    .dislikes(this.dislike)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class res{
        private Long answerId;
        private Long userId;
        private String contents;
        private Integer likes = 0;
        private Integer dislikes = 0;
        private Boolean isChosen;
        private YN userLikeDislikeAction;
        private UserDto.res user;
        private List<CommentDto.res> comments = new ArrayList<>();
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;

        public res(Answer answer, AnswerChoose answerChoose, User user) {
            this.answerId = answer.getAnswerId();
            this.userId = answer.getUserId();
            this.isChosen = answerChoose != null;
            this.contents = answer.getContents();
            this.user = user.toDto();
            this.createdDate = answer.getCreatedDate();
            this.modifiedDate = answer.getModifiedDate();
        }

        public res(Answer answer, AnswerChoose answerChoose) {
            this.answerId = answer.getAnswerId();
            this.userId = answer.getUserId();
            this.isChosen = answerChoose != null;
            this.contents = answer.getContents();
            this.createdDate = answer.getCreatedDate();
            this.modifiedDate = answer.getModifiedDate();
        }

        public void setUserLikeDislikeAction(YN yn){
            this.userLikeDislikeAction = yn;
        }

        public void setLikesDislikes(List<AnswerDto.answerLikeDislike> list) {
            list.forEach(dto -> {
                if (dto.getLike() != null) {
                    this.likes++;
                } else {
                    this.dislikes++;
                }
            });
        }
    }
}
