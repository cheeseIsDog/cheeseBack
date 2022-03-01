package cheese.cheese.service;

import cheese.cheese.Advice.Exception.UserNotFoundException;
import cheese.cheese.dto.UserDto;
import cheese.cheese.entity.School;
import cheese.cheese.entity.User;
import cheese.cheese.repository.SchoolRepository;
import cheese.cheese.repository.UserRepository;
import cheese.cheese.util.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SchoolRepository schoolRepository;
    private final IdGenerator idGenerator;

    public Boolean signUp(UserDto.SignUpReq signUpReq) throws Exception{
        Boolean result = false;
        if (this.isNotExistedEmail(signUpReq.getEmail()) && this.isNotExistedNickName(signUpReq.getNickName())) {
            User user = signUpReq.toEntity();
            user.setUserId(idGenerator.getNewId());
            userRepository.save(user);
            result = true;
        }
        return result;
    }

    public UserDto.res signIn(UserDto.loginReq loginReq) {
        User user = this.userRepository.findByEmailAndPassword(loginReq.getEmail(), loginReq.getPassword())
                .orElseThrow(UserNotFoundException::new);
        School school = this.schoolRepository.getById(user.getSchoolId());
        return UserDto.res.builder()
                .userId(user.getUserId())
                .nickName(user.getNickName())
                .score(user.getScore())
                .schoolId(school.getSchoolId())
                .schoolName(school.getSchoolName())
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
