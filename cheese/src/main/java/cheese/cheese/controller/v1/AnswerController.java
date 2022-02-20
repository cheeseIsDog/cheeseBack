package cheese.cheese.controller.v1;

import cheese.cheese.dto.AnswerDto;
import cheese.cheese.dto.CommentDto;
import cheese.cheese.service.AnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("web/v1/answer")
@CrossOrigin(origins = "*")
public class AnswerController {
    private final AnswerService answerService;

    @Operation(summary = "create answer", description = "답변(댓글) 생성")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })
    @PostMapping("/create")
    public Boolean createQuestion(@RequestBody AnswerDto.gen gen) throws Exception {
        return this.answerService.create(gen);
    }
}
