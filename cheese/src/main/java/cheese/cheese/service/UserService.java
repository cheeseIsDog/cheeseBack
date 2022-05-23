package cheese.cheese.service;

import cheese.cheese.Advice.Exception.UserException;
import cheese.cheese.dto.AnswerDto;
import cheese.cheese.dto.UserDto;
import cheese.cheese.entity.School;
import cheese.cheese.entity.User;
import cheese.cheese.repository.AnswerDslRepository;
import cheese.cheese.repository.QuestionRepository;
import cheese.cheese.repository.SchoolRepository;
import cheese.cheese.repository.UserRepository;
import cheese.cheese.security.JwtTokenProvider;
import cheese.cheese.security.UserAuthentication;
import cheese.cheese.util.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static cheese.cheese.dto.Enum.ExceptionConsts.HAS_NO_USER_ID;
import static cheese.cheese.dto.Enum.ExceptionConsts.PASSWORD_IS_NOT_RIGHT;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SchoolRepository schoolRepository;
    private final QuestionRepository questionRepository;
    private final AnswerDslRepository answerDslRepository;
    private final IdGenerator idGenerator;
    private final PasswordEncoder passwordEncoder;

    public Boolean signUp(UserDto.SignUpReq signUpReq) throws Exception{
        Boolean result = false;
        if (this.isNotExistedEmail(signUpReq.getEmail()) && this.isNotExistedNickName(signUpReq.getNickName())) {
            User user = signUpReq.toEntity();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setUserId(idGenerator.getNewId());
            userRepository.save(user);
            result = true;
        }
        return result;
    }

    public UserDto.res signIn(HttpServletResponse res,
                              UserDto.loginReq loginReq) throws Exception{
        Optional<User> optionalUser = this.userRepository.findByEmail(loginReq.getEmail());
        User user;

        if(optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            throw new UserException(HAS_NO_USER_ID);
        }

        if(!passwordEncoder.encode(loginReq.getPassword()).equals(user.getPassword())){
            throw new UserException(PASSWORD_IS_NOT_RIGHT);
        }

        Authentication authentication = new UserAuthentication(loginReq.getEmail(), null, null);
        String token = JwtTokenProvider.generateToken(authentication);

        School school = this.schoolRepository.getById(user.getSchoolId());
        return UserDto.res.builder()
                .userId(user.getUserId())
                .nickName(user.getNickName())
                .score(user.getScore())
                .schoolId(school.getSchoolId())
                .schoolName(school.getSchoolName())
                .Authorization(token)
                .build();
    }

    public Boolean delete(UserDto.delete del) {
        Boolean result = true;
        User user = userRepository.findByEmail(del.getEmail()).orElse(null);

        if (user == null) {
            result = false;
        } else {
            userRepository.delete(user);
        }

        return result;
    }

    public UserDto.res getUserInfo(Long userId)  {
        User user = this.userRepository.findByUserId(userId).orElse(null);
        School school = this.schoolRepository.getById(user.getSchoolId());
        return UserDto.res.builder()
                .userId(user.getUserId())
                .nickName(user.getNickName())
                .score(user.getScore())
                .schoolId(school.getSchoolId())
                .schoolName(school.getSchoolName())
                .build();
    }

    public UserDto.userInfoDetail getUserInfo(String userEmail)  {
        User user = this.userRepository.findByEmail(userEmail).orElse(null);
        School school = this.schoolRepository.getById(user.getSchoolId());
        Integer questions = this.questionRepository.countQuestionsByUserId(user.getUserId());
        List<AnswerDto.res> answers = this.answerDslRepository.ofUser(user.getUserId());
        return UserDto.userInfoDetail.builder()
                .user(user)
                .school(school)
                .myQuestions(questions)
                .myAnswers(answers)
                .build();
    }

    private Boolean isNotExistedEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        return user == null;
    }

    private Boolean isNotExistedNickName(String nickName) {
        User user = userRepository.findByNickName(nickName).orElse(null);
        return user == null;
    }
}
