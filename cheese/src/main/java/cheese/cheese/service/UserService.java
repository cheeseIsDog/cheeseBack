package cheese.cheese.service;

import cheese.cheese.Advice.Exception.UserNotFoundException;
import cheese.cheese.dto.UserDto;
import cheese.cheese.entity.User;
import cheese.cheese.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Boolean signUp(UserDto.SignUpReq signUpReq) throws Exception{
        Boolean result = false;
        if (this.isNotExistedEmail(signUpReq.getEmail()) && this.isNotExistedNickName(signUpReq.getNickName())) {
            userRepository.save(signUpReq.toEntity());
            result = true;
        }
        return result;
    }

    public UserDto.res signIn(UserDto.loginReq loginReq) {
        User user = userRepository.findByEmailAndPassword(loginReq.getEmail(), loginReq.getPassword())
                .orElseThrow(UserNotFoundException::new);
        return UserDto.res.builder()
                .userId(user.getUserId())
                .nickName(user.getNickName())
                .score(user.getScore())
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

    private Boolean isNotExistedEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        return user == null;
    }

    private Boolean isNotExistedNickName(String nickName) {
        User user = userRepository.findByNickName(nickName).orElse(null);
        return user == null;
    }
}
