package cheese.cheese.service;

import cheese.cheese.Advice.Exception.UserNotFoundException;
import cheese.cheese.dto.UserDto;
import cheese.cheese.entity.School;
import cheese.cheese.entity.User;
import cheese.cheese.repository.SchoolRepository;
import cheese.cheese.repository.UserRepository;
import cheese.cheese.security.JwtTokenProvider;
import cheese.cheese.security.UserAuthentication;
import cheese.cheese.util.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SchoolRepository schoolRepository;
    private final IdGenerator idGenerator;
    private PasswordEncoder passwordEncoder;

    public Boolean signUp(UserDto.SignUpReq signUpReq) throws Exception{
        Boolean result = false;
        if (this.isNotExistedEmail(signUpReq.getEmail()) && this.isNotExistedNickName(signUpReq.getNickName())) {
            User user = signUpReq.toEntity();
            user.setPassword(user.getPassword());
            user.setUserId(idGenerator.getNewId());
            userRepository.save(user);
            result = true;
        }
        return result;
    }

    public UserDto.res signIn(HttpServletResponse res,
                              UserDto.loginReq loginReq) {
        User user = this.userRepository.findByEmail(loginReq.getEmail()).orElseThrow(UserNotFoundException::new);

        if(!loginReq.getPassword().equals(user.getPassword())){
            throw new IllegalArgumentException("비밀번호를 확인하세요.");
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

    private Boolean isNotExistedEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        return user == null;
    }

    private Boolean isNotExistedNickName(String nickName) {
        User user = userRepository.findByNickName(nickName).orElse(null);
        return user == null;
    }
}
