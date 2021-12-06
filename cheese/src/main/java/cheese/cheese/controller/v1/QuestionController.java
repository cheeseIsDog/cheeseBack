package cheese.cheese.controller.v1;

import cheese.cheese.service.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("web/v1/question")
@CrossOrigin(origins = "*")
public class QuestionController {
    private final ResponseService responseService;

//    @ApiOperation(value = "login", notes = "로그인")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "OK !!"),
//            @ApiResponse(code = 500, message = "Internal Server Error !!"),
//            @ApiResponse(code = 404, message = "Not Found !!")
//    })
//    @PostMapping("/getQuestionList")
//    public CommonResult login(@RequestBody UserDto.loginReq login)throws Exception {
//        return responseService.getSingleResult();
//    }
}
