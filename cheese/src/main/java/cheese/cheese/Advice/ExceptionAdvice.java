package cheese.cheese.Advice;

import cheese.cheese.Advice.Exception.GlobalException;
import cheese.cheese.Advice.Exception.QuestionException;
import cheese.cheese.Advice.Exception.UserException;
import cheese.cheese.dto.Enum.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static cheese.cheese.dto.Enum.ExceptionConsts.*;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {

    @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResponse ExceptionAboutUser(HttpServletRequest request, UserException e){
        switch (e.getMessage()) {
            case HAS_NO_USER_ID:
                return new CommonResponse(4001, HAS_NO_USER_ID);
            case PASSWORD_IS_NOT_RIGHT:
                return new CommonResponse(4002, PASSWORD_IS_NOT_RIGHT);
            case TOKEN_HAS_PROBLEM:
                return new CommonResponse(401, TOKEN_HAS_PROBLEM);
            case EXISTED_EMAIL:
                return new CommonResponse(400, EXISTED_EMAIL);
            case EXISTED_NICKNAME:
                return new CommonResponse(400, EXISTED_NICKNAME);
            case ERROR_DURING_GET_SCHOOL_LIST:
                return new CommonResponse(500, ERROR_DURING_GET_SCHOOL_LIST);
            case GHOST_USER:
                return new CommonResponse(444, GHOST_USER);
            case ABUSING_USER:
                return new CommonResponse(4444, ABUSING_USER);
            default:
                return new CommonResponse(500, SERVER_ERROR);
        }
    }

    @ExceptionHandler(QuestionException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResponse ExceptionAboutQuestion(HttpServletRequest request, UserException e){
        switch (e.getMessage()) {
            case EXISTED_TITLE:
                return new CommonResponse(50010, EXISTED_TITLE);
            case HAS_NO_USER_ID2:
                return new CommonResponse(50011, HAS_NO_USER_ID2);
            case HAS_NO_SCHOOL_ID:
                return new CommonResponse(50012, HAS_NO_SCHOOL_ID);
            case HAS_NO_QUESTION_CONTENTS:
                return new CommonResponse(50013, HAS_NO_QUESTION_CONTENTS);
            default:
                return new CommonResponse(500, SERVER_ERROR);
        }
    }

    @ExceptionHandler(GlobalException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResponse ExceptionAboutGlobal(HttpServletRequest request, UserException e){
        switch (e.getMessage()) {
            default:
                return new CommonResponse(500, SERVER_ERROR);
        }
    }
}
