package cheese.cheese.service;

import cheese.cheese.dto.QuestionDto;
import cheese.cheese.entity.Question;
import cheese.cheese.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public Boolean create(QuestionDto.gen gen) {
        Boolean result = false;
        if (this.isNotExistedQuestionTitle(gen.getTitle(), gen.getSchoolId())) {
            questionRepository.save(gen.toEntity());
            result = true;
        }
        return result;
    }

    private Boolean isNotExistedQuestionTitle(String title, Long schoolId) {
        Question question = questionRepository.findByTitleAndSchoolId(title, schoolId).orElse(null);
        return question == null;
    }

    public void getQuestions() {

    }
}
