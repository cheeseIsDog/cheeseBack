package cheese.cheese.entity;

import cheese.cheese.entity.common.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="comment")
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Long commentId;
    private Long answerId;
    private Long userId;
    private String contents;
    private Integer likes;
    private Integer dislikes;

    @Builder
    public Comment(Long answerId, Long userId, String contents) {
        this.answerId = answerId;
        this.userId = userId;
        this.contents = contents;
        this.likes = 0;
        this.dislikes = 0;
    }
}
