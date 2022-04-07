package cheese.cheese.service;

import cheese.cheese.dto.AnswerDto;
import cheese.cheese.entity.AnswerLikeDislike;
import cheese.cheese.repository.AnswerChooseRepository;
import cheese.cheese.repository.AnswerDslRepository;
import cheese.cheese.repository.AnswerLikeDislikeRepository;
import cheese.cheese.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final AnswerChooseRepository answerChooseRepository;
    private final AnswerLikeDislikeRepository answerLikeDislikeRepository;
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
        return this.answerDslRepository.ofQuestion(questionId);
    }

    public Boolean chooseAsRightAnswer(AnswerDto.chooseAnswer choose) {
        try {
            this.answerChooseRepository.save(choose.toEntity());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Boolean createAnswerLikeDislike(AnswerDto.answerLikeDislike answerLikeDislike) {
        Boolean result = true;
        try {
            this.answerLikeDislikeRepository.save(answerLikeDislike.toEntity());
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public Boolean updateAnswerLikeDislike(AnswerDto.answerLikeDislike answerLikeDislikeDto) {
        Boolean result = true;
        try {
            AnswerLikeDislike answerLikeDislike = this.answerLikeDislikeRepository
                    .getByAnswerIdAndUserId(
                            answerLikeDislikeDto.getAnswerId(),
                            answerLikeDislikeDto.getUserId()
                    );
            answerLikeDislike.changeState();
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
}
