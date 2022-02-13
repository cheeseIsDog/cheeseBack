package cheese.cheese.controller.v1;

import cheese.cheese.dto.QuestionDto;
import cheese.cheese.entity.Question;
import cheese.cheese.service.QuestionService;
import cheese.cheese.service.TagService;
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
    private final TagService tagService;

    @Operation(summary = "create Question", description = "학교 내 질문 생성")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })
    @PostMapping("/create")
    public Boolean createQuestion(@RequestBody QuestionDto.gen gen) throws Exception {
        this.tagService.saveTags(gen, this.questionService.create(gen));
        return true;
    }

    @Operation(summary = "get Question", description = "학교 내 전체 질문 쿼리")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })

    @PostMapping("/getBySchool")
    public List<QuestionDto.res> getQuestions(@RequestBody QuestionDto.req req) throws Exception {
        return this.questionService.getQuestionsBySchoolId(req);
    }

    @Operation(summary = "search Question", description = "학교 내 질문을 제목으로 검색")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })

    @PostMapping("/searchQuestionsByTitle")
    public List<QuestionDto.res> searchQuestionsByTitle(@RequestBody QuestionDto.searchReqByTitle req) throws Exception {
        return this.questionService.searchQuestionsByTitle(req);
    }

    @Operation(summary = "search Question", description = "학교 내 질문을 태그로 검색")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })

    @PostMapping("/searchQuestionsByTag")
    public List<QuestionDto.res> searchQuestionsByTag(@RequestBody QuestionDto.searchReqByTag req) throws Exception {
        return this.questionService.searchQuestionsByTag(req);
    }

    @Operation(summary = "get user's questions info", description = "유저의 질문 관련 정보 쿼리")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })

    @PostMapping("/getInfoByUser")
    public QuestionDto.resOfUserQuestions getUserQuestionsInfo(@RequestBody QuestionDto.reqOfUserQuestions req) throws Exception {
        return this.questionService.getUserQuestionsInfo(req.getUserId());
    }

    @Operation(summary = "get user's questions info", description = "유저의 학교 전체 질문 관련 정보 쿼리")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })
    @PostMapping("/getInfoBySchool")
    public QuestionDto.resOfSchoolQuestions getQuestionsOfSchoolInfo(@RequestBody QuestionDto.reqOfSchoolQuestions req) throws Exception {
        return this.questionService.getQuestionsOfSchoolInfo(req.getSchoolId());
    }
}
