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

    @Column(name="user_id")
    private Long userId;

    @Column(name="likes")
    private Boolean likes;

    @Column(name="dislikes")
    private Boolean dislikes;

    @Builder
    public AnswerLikeDislike(Long userId, Long answerId, Boolean likes, Boolean dislikes) {
        this.userId = userId;
        this.answerId = answerId;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public void changeState(Boolean likes, Boolean dislikes) {
        this.likes = likes;
        this.dislikes = dislikes;
    }
}
