package cheese.cheese.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    @Column(name="nick_name")
    private String nickName;

    @Column(name="id")
    private String id;

    @Column(name="password")
    private String password;

    @Column(name="score")
    private Integer score;

    @Column(name="school_id")
    private Long schoolId;

    @Builder
    public User(String nickName, String id, String password) {
        this.nickName = nickName;
        this.id = id;
        this.password = password;
    }
}
