package cheese.cheese.entity;

import cheese.cheese.entity.common.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="tag_word")
public class TagWord extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagWordId;
    private String tagName;

    public TagWord(String tagName) {
        this.tagName = tagName;
    }
}
