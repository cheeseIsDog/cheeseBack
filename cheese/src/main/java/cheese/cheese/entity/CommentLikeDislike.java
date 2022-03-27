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
@Table(name="comment_like_dislike")
public class CommentLikeDislike extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Long commentId;
    private Long userId;
    private Boolean likes;
    private Boolean dislikes;

    @Builder
    public CommentLikeDislike(Long commentId, Long userId, Boolean likes, Boolean dislikes) {
        this.commentId = commentId;
        this.userId = userId;
        this.likes = likes;
        this.dislikes = dislikes;
    }
}
