package cheese.cheese.dto;

import cheese.cheese.entity.User;
import lombok.Builder;
import lombok.Getter;

public class UserDto {

    @Getter
    public static class SignUpReq {
        private String nickName;
        private String id;
        private String password;

        @Builder
        public SignUpReq(String nickName, String id, String password) {
            this.nickName = nickName;
            this.id = id;
            this.password = password;
        }

        public User toEntity() {
            return User.builder()
                    .nickName(this.nickName)
                    .id(this.id)
                    .password(this.password)
                    .build();
        }
    }

    @Getter
    public static class loginReq{
        private String id;
        private String password;

        @Builder
        public loginReq(String id, String password){
            this.id = id;
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
