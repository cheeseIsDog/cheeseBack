package cheese.cheese.entity;

import cheese.cheese.entity.common.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name="question_like_dislike")
public class QuestionLikeDislike extends BaseTimeEntity {
    @Id
    private Long questionId;
    private Long userId;
    private Boolean good;
    private Boolean bad;

    @Builder
    public QuestionLikeDislike(Long questionId, Long userId, Boolean like, Boolean dislike) {
        this.questionId = questionId;
        this.userId = userId;
        this.good = like;
        this.bad = dislike;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public void changeState(Boolean like, Boolean dislike) {
        this.good = like;
        this.bad = dislike;
    }
}
