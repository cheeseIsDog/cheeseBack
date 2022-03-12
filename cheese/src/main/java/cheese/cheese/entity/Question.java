package cheese.cheese.entity;

import cheese.cheese.dto.Enum.YN;
import cheese.cheese.entity.common.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="question")
public class Question extends BaseTimeEntity {
    @Id
    private Long questionId;
    private Long userId;
    private Long schoolId;
    private String title;
    private String contents;
    @Column(columnDefinition = "integer default 0")
    private Integer likes;
    @Column(columnDefinition = "integer default 0")
    private Integer dislikes;
    @Column(columnDefinition = "integer default 0")
    private Integer view;
    @Enumerated(EnumType.STRING)
    private YN solved_YN;

    @Builder
    public Question (Long userId, Long schoolId, String title, String contents) {
        this.userId = userId;
        this.schoolId = schoolId;
        this.title = title;
        this.contents = contents;
        this.likes = 0;
        this.dislikes = 0;
        this.view = 0;
        this.solved_YN = YN.No;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
