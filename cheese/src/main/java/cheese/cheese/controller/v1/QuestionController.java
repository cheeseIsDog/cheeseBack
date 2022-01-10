package cheese.cheese.controller.v1;

import cheese.cheese.dto.QuestionDto;
import cheese.cheese.dto.UserDto;
import cheese.cheese.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("web/v1/question")
@CrossOrigin(origins = "*")
public class QuestionController {
    private final QuestionService questionService;

    @Operation(summary = "post Question", description = "질문 생성")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })
    @PostMapping("/create")
    public Boolean createQuestion(@RequestBody QuestionDto.gen gen) throws Exception {
        return questionService.create(gen);
    }

    @Operation(summary = "post Question", description = "질문 생성")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })
    @PostMapping("/getBySchool")
    public List<QuestionDto.res> getQuestions(@RequestBody QuestionDto.req req) throws Exception {
        return questionService.getQuestionsBySchoolId(req.getSchoolId());
    }
}
