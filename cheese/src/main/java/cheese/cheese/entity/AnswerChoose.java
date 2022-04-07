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
@Table(name="answer_choose")
public class AnswerChoose extends BaseTimeEntity {
    @Id
    @Column(name="answer_id")
    private Long answerId;

    @Column(name="user_id")
    private Long userId;

    private Boolean choose;

    @Builder
    public AnswerChoose(Long userId, Long answerId) {
        this.userId = userId;
        this.answerId = answerId;
        this.choose = true;
    }

    public void changeChooseState() {
        this.choose = !this.choose;
    }
}
