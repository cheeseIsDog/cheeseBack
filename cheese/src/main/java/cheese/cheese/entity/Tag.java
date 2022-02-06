package cheese.cheese.entity;

import cheese.cheese.dto.TagDto;
import cheese.cheese.entity.common.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="tag")
public class Tag extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tag_id")
    private Long tagId;

    @Column(name="question_id")
    private Long questionId;

    @Column(name="tag_name")
    private String tagName;

    public TagDto.res toDto() {
        return new TagDto.res(this);
    }

    public Tag(Long questionId, String tagName) {
        this.questionId = questionId;
        this.tagName = tagName;
    }
}
