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
    private Boolean likes;
    private Boolean dislikes;

    @Builder
    public QuestionLikeDislike(Long questionId, Long userId, Boolean likes, Boolean dislikes) {
        this.questionId = questionId;
        this.userId = userId;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public void changeState(Boolean likes, Boolean dislikes) {
        this.likes = likes;
        this.dislikes = dislikes;
    }
}
