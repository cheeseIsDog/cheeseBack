package cheese.cheese.dto;

import cheese.cheese.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserDto {

    @Getter
    @NoArgsConstructor
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
    @NoArgsConstructor
    public static class loginReq{
        private String email;
        private String password;

        @Builder
        public loginReq(String email, String password){
            this.email = email;
            this.password = password;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class sendAuth {
        private String email;

        @Builder
        public sendAuth(String email) {
            this.email = email;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class authEmail {
        private String email;
        private String authNumber;

        @Builder
        public authEmail(String email, String authNumber) {
            this.email = email;
            this.authNumber = authNumber;
        }
    }

    @NoArgsConstructor
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
