package cheese.cheese.service;

import cheese.cheese.dto.AnswerDto;
import cheese.cheese.entity.Answer;
import cheese.cheese.repository.AnswerChooseRepository;
import cheese.cheese.repository.AnswerDslRepository;
import cheese.cheese.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final AnswerChooseRepository answerChooseRepository;
    private final AnswerDslRepository answerDslRepository;

    public Boolean create(AnswerDto.gen gen) {
        Boolean result = true;
        try {
            this.answerRepository.save(gen.toEntity());
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public List<AnswerDto.res> ofQuestion(Long questionId) {
        return answerDslRepository.ofQuestion(questionId);
    }

    public Boolean chooseAsRightAnswer(AnswerDto.chooseAnswer choose) {
        try {
            this.answerChooseRepository.save(choose.toEntity());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Boolean modifyAnswerChoiceState(Long answerId) {
        try {
            Answer answer = this.answerRepository.getById(answerId);
            this.answerRepository.save(answer);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
