package cheese.cheese.Advice;

import cheese.cheese.Advice.Exception.UserNotFoundException;
import cheese.cheese.ResultForm.CommonResult;
import cheese.cheese.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {

    private final ResponseService responseService;

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected CommonResult UserNotFoundException(HttpServletRequest request, UserNotFoundException e){
        return responseService.getFailResult();
    }

}
