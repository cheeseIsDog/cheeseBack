package cheese.cheese.controller.v1;

import cheese.cheese.dto.UserDto;
import cheese.cheese.security.JwtTokenProvider;
import cheese.cheese.service.EmailService;
import cheese.cheese.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("web/v1/user")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;
    private final EmailService emailService;

    @Operation(summary = "signIn", description = "로그인")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })
    @PostMapping("/signIn")
    public UserDto.res signIn(
            final HttpServletResponse res,
            @RequestBody UserDto.loginReq login) throws Exception {
        return this.userService.signIn(res, login);
    }

    @Operation(summary = "getToken", description = "토큰으로 정보 불러오")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })
    @GetMapping("/getUserInfo")
    public UserDto.res getToken(
            final HttpServletRequest request) throws Exception {
        String token = "";
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.isNotEmpty(bearerToken) && bearerToken.startsWith("Bearer ")) {
            token = bearerToken.substring("Bearer ".length());
        }
        String userEmail = JwtTokenProvider.getUserIdFromJWT(token);
        return this.userService.getUserInfo(userEmail);
    }

    @Operation(summary = "delete", description = "이메일로 유저 삭제 ( 일시 )")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })
    @PostMapping("/deleteUserForYongda")
    public Boolean delete(@RequestBody UserDto.delete del)  throws Exception{
        return this.userService.delete(del);
    }

    @Operation(summary = "signUp", description = "회원가입")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })
    @PostMapping("/signUp")
    public Boolean signUp(@RequestBody UserDto.SignUpReq dto )  throws Exception{
        return this.userService.signUp(dto);
    }

    @Operation(summary = "getUserInfo", description = "유저정보 가져오")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })
    @PostMapping("/getUserInfo")
    public UserDto.res getUserInfo(@RequestBody UserDto.userInfoReq dto )  throws Exception{
        return this.userService.getUserInfo(dto.getUserId());
    }

    @Operation(summary = "sendMail", description = "검증 이메일 전송")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })
    @PostMapping("/sendMail")
    public Boolean sendMail(@RequestBody UserDto.sendAuth dto) throws Exception {
        return this.emailService.sendEmail(dto.getEmail());
    }

    @Operation(summary = "authMail", description = "검증 이메일 전송")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })
    @PostMapping("/authMail")
    public Boolean authMail(@RequestBody UserDto.authEmail dto) throws Exception {
        return this.emailService.sendAuth(dto);
    }
}
