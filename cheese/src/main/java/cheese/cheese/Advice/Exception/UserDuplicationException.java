package cheese.cheese.Advice.Exception;

import lombok.Getter;

@Getter
public class UserDuplicationException extends RuntimeException {
    private String id;
    private String msg;

    public UserDuplicationException(String id){
        this.id = id;
        this.msg = "중복되는 아이디입니다.";
    }
}
