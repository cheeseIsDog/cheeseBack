package cheese.cheese.entity;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    private Long userId;
    private Long schoolId;
    private String title;
    private String contents;
    @ColumnDefault("0")
    private Integer likes;
    @ColumnDefault("0")
    private Integer dislikes;

    @Builder
    public Question (Long userId, Long schoolId, String title, String contents) {
        this.userId = userId;
        this.schoolId = schoolId;
        this.title = title;
        this.contents = contents;
    }
}
