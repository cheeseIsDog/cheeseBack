package cheese.cheese.dto.Enum;

import lombok.Getter;

import static cheese.cheese.dto.Enum.ExceptionConsts.HAS_NO_USER_ID;
import static cheese.cheese.dto.Enum.ExceptionConsts.PASSWORD_IS_NOT_RIGHT;

@Getter
public class CommonResponse {
    int code;
    String msg;

    public CommonResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
