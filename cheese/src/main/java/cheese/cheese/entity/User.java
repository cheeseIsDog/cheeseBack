package cheese.cheese.entity;

import cheese.cheese.dto.UserDto;
import cheese.cheese.entity.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="user")
public class User extends BaseTimeEntity {
    @Id
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
    public User(String nickName, String password, Long schoolId, String email, String authNumber) {
        this.nickName = nickName;
        this.password = password;
        this.email = email;
        this.authNumber = authNumber;
        this.schoolId = schoolId;
        this.score = 0;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserDto.res toDto() {
        return new UserDto.res(this.getUserId(), this.getNickName(), this.getScore());
    }
}
