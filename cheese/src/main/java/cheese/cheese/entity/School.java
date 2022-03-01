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
@Table(name="school")
public class School extends BaseTimeEntity {
    @Id
    @Column(name="school_id")
    private Long schoolId;

    @Column(name="school_name")
    private String schoolName;

    @Column(name="score")
    private Integer score;
}
