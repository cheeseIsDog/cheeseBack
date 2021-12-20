package cheese.cheese.Advice;

import cheese.cheese.Advice.Exception.UserDuplicationException;
import cheese.cheese.Advice.Exception.UserNotFoundException;
import cheese.cheese.dto.Enum.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {


    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected CommonResponse UserNotFoundException(HttpServletRequest request, UserNotFoundException e){
        return CommonResponse.USER_NOT_FOUND;
    }

    @ExceptionHandler(UserDuplicationException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected CommonResponse UserDuplicationException(HttpServletRequest request, UserNotFoundException e){
        return CommonResponse.USER_NOT_FOUND;
    }

}
