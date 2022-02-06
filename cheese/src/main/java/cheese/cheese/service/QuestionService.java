package cheese.cheese.service;

import cheese.cheese.dto.QuestionDto;
import cheese.cheese.entity.Question;
import cheese.cheese.repository.QuestionRepository;
import cheese.cheese.repository.QuestionDslRepository;
import cheese.cheese.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final TagRepository tagRepository;
    private final QuestionDslRepository questionDslRepository;

    public Boolean create(QuestionDto.gen gen) {
        Boolean result = false;
        if (this.isNotExistedQuestionTitle(gen.getTitle(), gen.getSchoolId())) {
            Question question = questionRepository.save(gen.toEntity());
            tagRepository.saveAll(gen.getTagEntities(question.getQuestionId()));
            result = true;
        }
        return result;
    }

    private Boolean isNotExistedQuestionTitle(String title, Long schoolId) {
        Question question = questionRepository.findByTitleAndSchoolId(title, schoolId).orElse(null);
        return question == null;
    }

    public List<QuestionDto.res> getQuestionsBySchoolId(QuestionDto.req req) {
        return questionDslRepository.getQuestionsWithTag(req.getSchoolId(), req.getOffset() ,req.getLimit());
    }

    public QuestionDto.resOfUserQuestions getUserQuestionsInfo(Long userId) {
        return this.questionDslRepository.getUserQuestionsInfo(userId);
    }

    public QuestionDto.resOfSchoolQuestions getQuestionsOfSchoolInfo(Long schoolId) {
        return this.questionDslRepository.getQuestionsOfSchoolInfo(schoolId);
    }
}
