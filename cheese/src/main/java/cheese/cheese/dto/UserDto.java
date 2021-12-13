package cheese.cheese.dto;

import cheese.cheese.entity.User;
import lombok.Builder;
import lombok.Getter;

public class UserDto {

    @Getter
    public static class SignUpReq {
        private String nickName;
        private String password;
        private String email;
        private String authNumber;

        @Builder
        public SignUpReq(String nickName, String password, String email, String authNumber) {
            this.nickName = nickName;
            this.password = password;
            this.email = email;
            this.authNumber = authNumber;
        }

        public User toEntity() {
            return User.builder()
                    .nickName(this.nickName)
                    .password(this.password)
                    .email(this.email)
                    .authNumber(this.authNumber)
                    .build();
        }
    }

    @Getter
    public static class loginReq{
        private String email;
        private String password;

        @Builder
        public loginReq(String email, String password){
            this.email = email;
            this.password = password;
        }
    }

    public static class res {
        private Long userId;
        private String nickName;
        private Integer score;
        private String schoolName;

        @Builder
        public res(Long userId, String nickName, Integer score) {
            this.userId = userId;
            this.nickName = nickName;
            this.score = score;
        }
    }
}
