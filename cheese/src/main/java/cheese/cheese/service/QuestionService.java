package cheese.cheese.service;

import cheese.cheese.dto.Enum.Consts;
import cheese.cheese.dto.QuestionDto;
import cheese.cheese.entity.Question;
import cheese.cheese.repository.QuestionRepository;
import cheese.cheese.repository.QuestionDslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionDslRepository questionDslRepository;

    public Question create(QuestionDto.gen gen) {
        Question question = null;
        if (this.isNotExistedQuestionTitle(gen.getTitle(), gen.getSchoolId())) {
            question = questionRepository.save(gen.toEntity());
        }
        return question;
    }

    private Boolean isNotExistedQuestionTitle(String title, Long schoolId) {
        Question question = questionRepository.findByTitleAndSchoolId(title, schoolId).orElse(null);
        return question == null;
    }

    public List<QuestionDto.res> getQuestionsBySchoolId(QuestionDto.req req) {
        return questionDslRepository.getQuestionsWithTag(req);
    }

    public List<QuestionDto.res> searchQuestionsByTitle(QuestionDto.searchReqByTitle req) {
        if ( Consts.BLANK.equals(req.getTitle()) ) {
            return new ArrayList<>();
        }
        return questionDslRepository.searchQuestionsByTitle(req);
    }

    public List<QuestionDto.res> searchQuestionsByTag(QuestionDto.searchReqByTag req) {
        if ( Consts.BLANK.equals(req.getTagName()) ) {
            return new ArrayList<>();
        }
        return questionDslRepository.searchQuestionsByTag(req);
    }

    public QuestionDto.resOfUserQuestions getUserQuestionsInfo(Long userId) {
        return this.questionDslRepository.getUserQuestionsInfo(userId);
    }

    public QuestionDto.resOfSchoolQuestions getQuestionsOfSchoolInfo(Long schoolId) {
        return this.questionDslRepository.getQuestionsOfSchoolInfo(schoolId);
    }
}
