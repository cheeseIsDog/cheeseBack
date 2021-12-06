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

    public Boolean signUp(UserDto.SignUpReq signUpReq) {
        Boolean result = false;
        if (!this.isExistedId(signUpReq.getId())) {
            userRepository.save(signUpReq.toEntity());
            result = true;
        }
        return result;
    }

    public UserDto.res signIn(UserDto.loginReq loginReq) {
        User user = userRepository.findByIdAndPassword(loginReq.getId(), loginReq.getPassword())
                .orElseThrow(UserNotFoundException::new);
        return UserDto.res.builder()
                .userId(user.getUserId())
                .nickName(user.getNickName())
                .score(user.getScore())
                .build();
    }

    private Boolean isExistedId(String id) {
        return userRepository.findById(id).orElse(null) != null;
    }
}
