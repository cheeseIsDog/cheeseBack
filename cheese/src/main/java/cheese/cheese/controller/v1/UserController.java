package cheese.cheese.controller.v1;

import cheese.cheese.ResultForm.CommonResult;
import cheese.cheese.dto.UserDto;
import cheese.cheese.service.ResponseService;
import cheese.cheese.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("web/v1/user")
@CrossOrigin(origins = "*")
public class UserController {
    private final ResponseService responseService;
    private final UserService userService;

    @ApiOperation(value = "signIn", notes = "로그인")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 500, message = "Internal Server Error !!"),
            @ApiResponse(code = 404, message = "Not Found !!")
    })
    @PostMapping("/signIn")
    public CommonResult signIn(@RequestBody UserDto.loginReq login)throws Exception {
        return responseService.getSingleResult(userService.signIn(login));
    }

    @ApiOperation(value = "signUp", notes = "회원가")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 500, message = "Internal Server Error !!"),
            @ApiResponse(code = 404, message = "Not Found !!")
    })
    @PostMapping("/signUp")
    public CommonResult signUp(@RequestBody UserDto.SignUpReq dto ) {
        return responseService.getBooleanResult(userService.signUp(dto));
    }
}
