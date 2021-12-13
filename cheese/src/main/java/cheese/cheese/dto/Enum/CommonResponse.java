package cheese.cheese.dto.Enum;

import lombok.Getter;

@Getter
public enum CommonResponse {
    SUCCESS(0, "성공하셨습니다."),
    FAIL( -1, "실패하셨습니다."),
    USER_NOT_FOUND(-2, "id, password에 해당하는 유저가 없습니다."),
    ;

    int code;
    String msg;

    CommonResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
