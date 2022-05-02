package cheese.cheese.service;

import cheese.cheese.dto.SchoolDto;
import cheese.cheese.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import static cheese.cheese.dto.Enum.Consts.UNIVERSE_POSTFIX;

@Service
@Slf4j
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public List<SchoolDto.res> getSchoolList() {
        return this.schoolRepository.findAll()
                .stream()
                .map(SchoolDto.res::new)
                .collect(Collectors.toList());
    }

    public List<SchoolDto.res> searchSchoolList(SchoolDto.req req) {
        String searchWord = req.getSchoolName();
        if (StringUtils.endsWith(searchWord, UNIVERSE_POSTFIX)) {
            searchWord = StringUtils.removeEnd(searchWord, UNIVERSE_POSTFIX);
        }
        return this.schoolRepository.findBySchoolNameContains(searchWord)
                .stream()
                .map(SchoolDto.res::new)
                .collect(Collectors.toList());
    }
}
