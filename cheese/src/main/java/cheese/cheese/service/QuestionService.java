package cheese.cheese.service;

import cheese.cheese.Advice.Exception.QuestionException;
import cheese.cheese.Advice.Exception.UserException;
import cheese.cheese.dto.Enum.Consts;
import cheese.cheese.dto.QuestionDto;
import cheese.cheese.entity.Question;
import cheese.cheese.entity.QuestionLikeDislike;
import cheese.cheese.entity.User;
import cheese.cheese.repository.QuestionLikeDislikeRepository;
import cheese.cheese.repository.QuestionRepository;
import cheese.cheese.repository.QuestionDslRepository;
import cheese.cheese.repository.UserRepository;
import cheese.cheese.util.IdGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static cheese.cheese.dto.Enum.Consts.*;
import static cheese.cheese.dto.Enum.ExceptionConsts.EXISTED_NICKNAME;
import static cheese.cheese.dto.Enum.ExceptionConsts.EXISTED_TITLE;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final QuestionLikeDislikeRepository questionLikeDislikeRepository;
    private final QuestionDslRepository questionDslRepository;
    private final IdGenerator idGenerator;

    public Question create(QuestionDto.gen gen) throws Exception {
        Question question = null;
        if (this.isNotExistedQuestionTitle(gen.getTitle(), gen.getSchoolId())) {
            question = gen.toEntity();
            question.setQuestionId(idGenerator.getNewId());
            question = this.questionRepository.save(question);
        }
        return question;
    }

    private Boolean isNotExistedQuestionTitle(String title, Long schoolId) {
        Question question = questionRepository.findByTitleAndSchoolId(title, schoolId).orElse(null);
        boolean existed = question != null;
        if (existed) {
            throw new QuestionException(EXISTED_TITLE);
        }
        return true;
    }

    public QuestionDto.res getQuestionById(Long questionId, Long userId) {
        Question question = this.questionRepository.findByQuestionId(questionId).orElse(null);
        User user = this.userRepository.findByUserId(question.getUserId()).orElse(null);
        List<QuestionDto.res> listForGettingTags = new ArrayList<>();
        QuestionDto.res res = QuestionDto.res.builder()
                .question(question)
                .user(user)
                .build();
        listForGettingTags.add(res);
        this.questionDslRepository.makeTagsForQuestions(listForGettingTags);
        this.checkUserLikeDisLikeAction(res, questionId, userId);
        return res;
    }

    public List<QuestionDto.res> getQuestionsBySchoolId(QuestionDto.req req) {
        List<QuestionDto.res> result = this.questionDslRepository.getQuestionsWithTag(req);
        result.forEach(res -> this.checkUserLikeDisLikeAction(res, res.getQuestionId(), req.getUserId()));
        return result;
    }

    public List<QuestionDto.res> getCheckedQuestionsBySchoolId(QuestionDto.req req) {
        List<QuestionDto.res> result = this.questionDslRepository.getCheckedQuestionsWithTag(req);
        result.forEach(res -> this.checkUserLikeDisLikeAction(res, res.getQuestionId(), req.getUserId()));
        return result;
    }

    public List<QuestionDto.res> getUnCheckedQuestionsBySchoolId(QuestionDto.req req) {
        List<QuestionDto.res> result = this.questionDslRepository.getUnCheckedQuestionsWithTag(req);
        result.forEach(res -> this.checkUserLikeDisLikeAction(res, res.getQuestionId(), req.getUserId()));
        return result;
    }

    public List<QuestionDto.res> searchQuestionsByTitle(QuestionDto.searchReqByTitle req) {
        if ( Consts.BLANK.equals(req.getTitle()) ) {
            return new ArrayList<>();
        }
        List<QuestionDto.res> result = questionDslRepository.searchQuestionsByTitle(req);
        result.forEach(res -> this.checkUserLikeDisLikeAction(res, res.getQuestionId(), req.getUserId()));
        return result;
    }

    public List<QuestionDto.res> searchQuestionsByTag(QuestionDto.searchReqByTag req) {
        if ( Consts.BLANK.equals(req.getTagName()) ) {
            return new ArrayList<>();
        }        List<QuestionDto.res> result = questionDslRepository.searchQuestionsByTag(req);
        result.forEach(res -> this.checkUserLikeDisLikeAction(res, res.getQuestionId(), req.getUserId()));
        return result;
    }

    public QuestionDto.resOfUserQuestions getUserQuestionsInfo(Long userId) {
        return this.questionDslRepository.getUserQuestionsInfo(userId);
    }

    public QuestionDto.resOfSchoolQuestions getQuestionsOfSchoolInfo(Long schoolId) {
        return this.questionDslRepository.getQuestionsOfSchoolInfo(schoolId);
    }

    public Boolean createQuestionLikeDislike(QuestionDto.questionLikeDislike questionLikeDislike) {
        Boolean result = true;
        try {
            this.questionLikeDislikeRepository.save(questionLikeDislike.toEntity());
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public Boolean updateQuestionLikeDislike(QuestionDto.questionLikeDislike questionLikeDislikeDto) {
        Boolean result = true;
        try {
            QuestionLikeDislike questionLikeDislike = this.questionLikeDislikeRepository
                    .getByQuestionIdAndUserId(
                            questionLikeDislikeDto.getQuestionId(),
                            questionLikeDislikeDto.getUserId()
                    );
            if (questionLikeDislike != null) {
                questionLikeDislike.changeState(questionLikeDislikeDto.getLike(), questionLikeDislikeDto.getDislike());
                this.questionLikeDislikeRepository.save(questionLikeDislike);
            } else {
                this.questionLikeDislikeRepository.save(questionLikeDislikeDto.toEntity());
            }
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    private void checkUserLikeDisLikeAction(QuestionDto.res res, Long questionId, Long userId) {
        QuestionLikeDislike questionLikeDislike = this.questionLikeDislikeRepository
                .getByQuestionIdAndUserId(questionId, userId);
        if (questionLikeDislike == null) {
            res.setUserLikeDislikeAction(DO_NOTHING);
        } else {
            if (questionLikeDislike.getGood()) {
                res.setUserLikeDislikeAction(LIKE);
            } else if (questionLikeDislike.getBad()) {
                res.setUserLikeDislikeAction(DIS_LIKE);
            } else {
                res.setUserLikeDislikeAction(DO_NOTHING);
            }
        }
    }
}
