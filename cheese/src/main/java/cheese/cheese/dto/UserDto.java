package cheese.cheese.dto;

import cheese.cheese.entity.School;
import cheese.cheese.entity.User;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class UserDto {

    @Getter
    @NoArgsConstructor
    public static class SignUpReq {
        private String nickName;
        private String password;
        private String email;
        private Long schoolId;
        private String authNumber;

        @Builder
        public SignUpReq(String nickName, String password, String email, Long schoolId, String authNumber) {
            this.nickName = nickName;
            this.password = password;
            this.email = email;
            this.schoolId = schoolId;
            this.authNumber = authNumber;
        }

        public User toEntity() {
            return User.builder()
                    .nickName(this.nickName)
                    .password(this.password)
                    .email(this.email)
                    .authNumber(this.authNumber)
                    .schoolId(this.schoolId)
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
    public static class userInfoReq{
        private Long userId;

        @Builder
        public userInfoReq(Long userId){
            this.userId = userId;
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
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public static class res {
        private Long userId;
        private String nickName;
        private Integer score;
        private String schoolName;
        private Long schoolId;
        private String token;

        @Builder
        public res(Long userId, String nickName, Integer score, String schoolName, Long schoolId, String Authorization) {
            this.userId = userId;
            this.nickName = nickName;
            this.score = score;
            this.schoolName = schoolName;
            this.schoolId = schoolId;
            this.token = Authorization;
        }

        public res(Long userId, String nickName, Integer score) {
            this.userId = userId;
            this.nickName = nickName;
            this.score = score;
        }
    }

    @NoArgsConstructor
    @Getter
    public static class delete {
        private String email;
        @Builder
        public delete(String email) {
            this.email = email;
        }
    }

    @NoArgsConstructor
    @Getter
    public static class userInfoDetail {
        private String email;
        private String nickName;
        private String schoolName;
        private Integer myQuestions;
        private Integer myChosenAnswers;
        private Integer myHasNotChosenAnswers;
        private Integer myAllAnswers;


        @Builder
        public userInfoDetail(User user, School school, Integer myQuestions, List<AnswerDto.res> myAnswers) {
            this.email = user.getEmail();
            this.nickName = user.getNickName();
            this.schoolName = school.getSchoolName();
            this.myQuestions = myQuestions;
            this.myAllAnswers = myAnswers.size();
            this.myChosenAnswers = (int) myAnswers.stream()
                    .filter(AnswerDto.res::getIsChosen)
                    .count();
            this.myHasNotChosenAnswers = myAllAnswers - myChosenAnswers;
        }
    }
}
