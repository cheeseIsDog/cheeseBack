package cheese.cheese.entity;

import cheese.cheese.entity.common.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name="answer_like_dislike")
public class AnswerLikeDislike extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="answer_id")
    private Long answerId;
    private Long userId;
    private Boolean good;
    private Boolean bad;

    @Builder
    public AnswerLikeDislike(Long userId, Long answerId, Boolean like, Boolean dislike) {
        this.userId = userId;
        this.answerId = answerId;
        this.good = like;
        this.bad = dislike;
    }

    public void changeState(Boolean like, Boolean dislike) {
        this.good = like;
        this.bad = dislike;
    }
}
