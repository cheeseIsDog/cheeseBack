package cheese.cheese.controller.v1;

import cheese.cheese.Advice.Exception.GlobalException;
import cheese.cheese.dto.AnswerDto;
import cheese.cheese.dto.CommentDto;
import cheese.cheese.service.AnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cheese.cheese.dto.Enum.ExceptionConsts.SERVER_ERROR;

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
        Boolean result = answerService.create(gen);
        if(!result) {
            throw new GlobalException(SERVER_ERROR);
        }
        return result;
    }

    @Operation(summary = "get answer List", description = "답변(댓글) 생성")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })
    @PostMapping("/list")
    public List<AnswerDto.res> ofQuestion(@RequestBody AnswerDto.req res) throws Exception {
        return this.answerService.ofQuestion(res.getQuestionId(), res.getUserId());
    }

    @Operation(summary = "choose as right answer", description = "답변 채택")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })
    @PostMapping("/chooseAsRightAnswer")
    public Boolean chooseAsRightAnswer(@RequestBody AnswerDto.chooseAnswer choose) throws Exception {
        return this.answerService.chooseAsRightAnswer(choose);
    }

    @Operation(summary = "createAnswerLikeDislike", description = "답변 좋아요 여부 생성")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })
    @PostMapping("/createAnswerLikeDislike")
    public Boolean createAnswerLikeDislike(@RequestBody AnswerDto.answerLikeDislike answerLikeDislike) throws Exception {
        return this.answerService.createAnswerLikeDislike(answerLikeDislike);
    }

    @Operation(summary = "updateAnswerLikeDislike", description = "답변 좋아요 여부 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })
    @PostMapping("/updateAnswerLikeDislike")
    public Boolean updateAnswerLikeDislike(@RequestBody AnswerDto.answerLikeDislike answerLikeDislike) throws Exception {
        return this.answerService.updateAnswerLikeDislike(answerLikeDislike);
    }
}
