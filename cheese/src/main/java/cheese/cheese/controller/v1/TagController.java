package cheese.cheese.controller.v1;

import cheese.cheese.dto.QuestionDto;
import cheese.cheese.dto.TagDto;
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
@RequestMapping("web/v1/tag")
@CrossOrigin(origins = "*")
public class TagController {
    private final TagService tagService;

    @Operation(summary = "search Tags", description = "학교 내 태그 목록 검색")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })

    @PostMapping("/searchTags")
    public List<TagDto.res> searchTags(@RequestBody TagDto.req req) throws Exception {
        return this.tagService.searchTags(req);
    }
}
