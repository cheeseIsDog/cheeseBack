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
@Table(name="tag_master")
public class TagMaster extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagMasterId;
    private Long questionId;
    private Long schoolId;
    private Long tagWordId;

    @Builder
    public TagMaster(Long questionId, Long schoolId, Long tagWordId) {
        this.questionId = questionId;
        this.schoolId = schoolId;
        this.tagWordId = tagWordId;
    }
}
