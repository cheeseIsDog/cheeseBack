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
@Table(name="user")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    @Column(name="nick_name")
    private String nickName;

    @Column(name="email")
    private String email;

    @Column(name="auth_number")
    private String authNumber;

    @Column(name="password")
    private String password;

    @Column(name="score")
    private Integer score;

    @Column(name="school_id")
    private Long schoolId;

    @Builder
    public User(String nickName, String password, String email, String authNumber) {
        this.nickName = nickName;
        this.password = password;
        this.email = email;
        this.authNumber = authNumber;
    }
}
