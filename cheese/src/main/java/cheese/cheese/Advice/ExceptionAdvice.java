package cheese.cheese.Advice;

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
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected CommonResponse UserNotFoundException(HttpServletRequest request, UserException e){
        switch (e.getMessage()) {
            case HAS_NO_USER_ID:
                return new CommonResponse(4001, HAS_NO_USER_ID);
            case PASSWORD_IS_NOT_RIGHT:
                return new CommonResponse(4002, PASSWORD_IS_NOT_RIGHT);
            default:
                return new CommonResponse(500, SERVER_ERROR);
        }
    }
}
