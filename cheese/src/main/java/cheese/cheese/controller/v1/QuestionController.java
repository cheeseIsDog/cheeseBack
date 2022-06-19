package cheese.cheese.controller.v1;

import cheese.cheese.Advice.Exception.GlobalException;
import cheese.cheese.dto.QuestionDto;
import cheese.cheese.service.QuestionService;
import cheese.cheese.service.TagService;
import cheese.cheese.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static cheese.cheese.dto.Enum.ExceptionConsts.SERVER_ERROR;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("web/v1/question")
@CrossOrigin(origins = "*")
public class QuestionController {
    private final QuestionService questionService;
    private final UserService userService;
    private final TagService tagService;

    @Operation(summary = "create Question", description = "학교 내 질문 생성")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })
    @PostMapping("/create")
    public Boolean createQuestion(@RequestBody QuestionDto.gen gen) throws Exception {
        this.userService.isAbusingUser(gen.getUserId(), gen.getSchoolId());
        this.tagService.saveTags(gen, this.questionService.create(gen));
        return true;
    }

    @Operation(summary = "get Questions", description = "학교 내 전체 질문 쿼리")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })

    @PostMapping("/getBySchool")
    public List<QuestionDto.res> getQuestions(@RequestBody QuestionDto.req req) throws Exception {
        List<QuestionDto.res> result = new ArrayList<>();
        try {
            this.questionService.getQuestionsBySchoolId(req);
        } catch (Exception e) {
            throw new GlobalException(SERVER_ERROR);
        }
        return result;
    }

    @Operation(summary = "get Question", description = "학교 내 특정 질문 쿼리")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })

    @PostMapping("/getByQuestionId")
    public QuestionDto.res getQuestion(@RequestBody QuestionDto.reqById req) throws Exception {
        QuestionDto.res result;
        try {
            result = this.questionService.getQuestionById(req.getQuestionId(), req.getUserId());
        } catch (Exception e) {
            throw new GlobalException(SERVER_ERROR);
        }
        return result;
    }

    @Operation(summary = "search Question By Title", description = "학교 내 질문을 제목으로 검색")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })

    @PostMapping("/searchQuestionsByTitle")
    public List<QuestionDto.res> searchQuestionsByTitle(@RequestBody QuestionDto.searchReqByTitle req) throws Exception {
        return this.questionService.searchQuestionsByTitle(req);
    }

    @Operation(summary = "search Question By Tag", description = "학교 내 질문을 태그로 검색")
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

    @Operation(summary = "get user school's questions info ", description = "유저의 학교 전체 질문 관련 정보 쿼리")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })
    @PostMapping("/getInfoBySchool")
    public QuestionDto.resOfSchoolQuestions getQuestionsOfSchoolInfo(@RequestBody QuestionDto.reqOfSchoolQuestions req) throws Exception {
        return this.questionService.getQuestionsOfSchoolInfo(req.getSchoolId());
    }

    @Operation(summary = "createQuestionLikeDislike", description = "질문 좋아요 여부 생성")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })
    @PostMapping("/createQuestionLikeDislike")
    public Boolean createQuestionLikeDislike(@RequestBody QuestionDto.questionLikeDislike questionLikeDislike) throws Exception {
        return this.questionService.createQuestionLikeDislike(questionLikeDislike);
    }

    @Operation(summary = "updateQuestionLikeDislike", description = "질문 좋아요 여부 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })
    @PostMapping("/updateQuestionLikeDislike")
    public Boolean updateQuestionLikeDislike(@RequestBody QuestionDto.questionLikeDislike questionLikeDislike) throws Exception {
        return this.questionService.updateQuestionLikeDislike(questionLikeDislike);
    }
}
