package cheese.cheese.controller.v1;

import cheese.cheese.Advice.Exception.GlobalException;
import cheese.cheese.dto.CommentDto;
import cheese.cheese.dto.QuestionDto;
import cheese.cheese.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static cheese.cheese.dto.Enum.ExceptionConsts.SERVER_ERROR;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("web/v1/comment")
@CrossOrigin(origins = "*")
public class CommentController {
    private final CommentService commentService;

    @Operation(summary = "create Comment", description = "코멘트(대댓글) 생성")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })
    @PostMapping("/create")
    public Boolean createComment(@RequestBody CommentDto.gen gen) throws Exception {
        Boolean result = commentService.create(gen);
        if(!result) {
            throw new GlobalException(SERVER_ERROR);
        }
        return result;
    }
}
