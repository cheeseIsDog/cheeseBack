package cheese.cheese.service;

import cheese.cheese.dto.AnswerDto;
import cheese.cheese.dto.Enum.YN;
import cheese.cheese.entity.AnswerLikeDislike;
import cheese.cheese.entity.Question;
import cheese.cheese.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
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
        List<AnswerDto.res> result = this.answerDslRepository.ofQuestion(questionId);
        result.forEach(res -> {
            AnswerLikeDislike answerLikeDislike = this.answerLikeDislikeRepository
                    .getByAnswerId(res.getAnswerId());
            if (answerLikeDislike != null) {
                res.setUserLikeDislikeAction(YN.Yes);
            } else {
                res.setUserLikeDislikeAction(YN.No);
            }
        });
        return result;
    }

    public Boolean chooseAsRightAnswer(AnswerDto.chooseAnswer choose) {
        try {
            this.answerChooseRepository.save(choose.toEntity());
            Question question = this.questionRepository.findByQuestionId(choose.getQuestionId())
                    .orElseThrow(Exception::new);
            question.setAsSolved();
            this.questionRepository.save(question);
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
            if (answerLikeDislike != null) {
                answerLikeDislike.changeState(answerLikeDislike.getLikes(), answerLikeDislike.getDislikes());
                this.answerLikeDislikeRepository.save(answerLikeDislike);
            } else {
                this.answerLikeDislikeRepository.save(answerLikeDislikeDto.toEntity());
            }
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
}
