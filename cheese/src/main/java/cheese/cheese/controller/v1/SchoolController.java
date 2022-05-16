package cheese.cheese.controller.v1;

import cheese.cheese.dto.SchoolDto;
import cheese.cheese.service.SchoolService;
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
@RequestMapping("web/v1/school")
@CrossOrigin(origins = "*")
public class SchoolController {
    private final SchoolService schoolService;

    @Operation(summary = "전체 학교 리스트", description = "전체 학교 리스트를 가져온다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })
    @PostMapping("allSchoolList")
    public List<SchoolDto.res> getSchoolList() throws Exception {
        return this.schoolService.getSchoolList();
    }

    @Operation(summary = "검색한 학교 리스트", description = "검색 결과 학교 리스트를 가져온다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
    })
    @PostMapping("listBySearching")
    public List<SchoolDto.res> searchSchool(@RequestBody SchoolDto.req req) throws Exception {
        return this.schoolService.searchSchoolList(req);
    }
}
