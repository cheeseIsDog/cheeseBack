package cheese.cheese.controller.v1;

import cheese.cheese.ResultForm.CommonResult;
import cheese.cheese.dto.UserDto;
import cheese.cheese.service.ResponseService;
import cheese.cheese.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "signIn", description = "로그인")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })
    @PostMapping("/signIn")
    public CommonResult signIn(@RequestBody UserDto.loginReq login)throws Exception {
        return responseService.getSingleResult(userService.signIn(login));
    }

    @Operation(summary = "signUp", description = "회원가")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })
    @PostMapping("/signUp")
    public CommonResult signUp(@RequestBody UserDto.SignUpReq dto ) {
        return responseService.getBooleanResult(userService.signUp(dto));
    }
}
