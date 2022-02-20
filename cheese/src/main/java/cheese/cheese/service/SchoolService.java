package cheese.cheese.service;

import cheese.cheese.dto.SchoolDto;
import cheese.cheese.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public List<SchoolDto.res> getSchoolList() {
        return this.schoolRepository.findAll()
                .stream()
                .map(school -> new SchoolDto.res(school.getSchoolName(), school.getSchoolId()))
                .collect(Collectors.toList());
    }
}
