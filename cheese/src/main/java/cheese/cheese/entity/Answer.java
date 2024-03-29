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
@Table(name="answer")
public class Answer extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="answer_id")
    private Long answerId;

    @Column(name="user_id")
    private Long userId;

    @Column(name="question_id")
    private Long questionId;

    @Column(name="contents")
    private String contents;

    @Builder
    public Answer(Long userId, Long questionId, String contents) {
        this.userId = userId;
        this.questionId = questionId;
        this.contents = contents;
    }
}
