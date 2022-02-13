package cheese.cheese.repository;

import cheese.cheese.dto.Enum.YN;
import cheese.cheese.dto.QuestionDto;
import cheese.cheese.dto.TagDto;
import cheese.cheese.entity.*;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static cheese.cheese.entity.QQuestion.question;
import static cheese.cheese.entity.QUser.user;
import static cheese.cheese.entity.QTagMaster.tagMaster;
import static cheese.cheese.entity.QTagWord.tagWord;



@Slf4j
@Repository
@RequiredArgsConstructor
public class QuestionDslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<QuestionDto.res> getQuestionsWithTag(QuestionDto.req req) {
        Long schoolId = req.getSchoolId();
        List<QuestionDto.res> result = this.jpaQueryFactory.select(
                        Projections.constructor(
                                QuestionDto.res.class,
                                question,
                                user
                        )
                )
                .from(question)
                .where(question.schoolId.eq(schoolId))
                .offset(req.getOffset())
                .limit(req.getLimit())
                .leftJoin(user).on(user.userId.eq(question.userId))
                .fetch();

        return this.makeTagsForQuestions(result);
    }

    public List<QuestionDto.res> searchQuestionsByTitle(QuestionDto.searchReqByTitle req) {
        Long schoolId = req.getSchoolId();
        List<QuestionDto.res> result = this.jpaQueryFactory.select(
                        Projections.constructor(
                                QuestionDto.res.class,
                                question,
                                user
                        )
                )
                .from(question)
                .where(question.schoolId.eq(schoolId)
                        .and(question.title.contains(req.getTitle()))
                )
                .offset(req.getOffset())
                .limit(req.getLimit())
                .leftJoin(user).on(user.userId.eq(question.userId))
                .fetch();

        return this.makeTagsForQuestions(result);
    }

    public List<QuestionDto.res> searchQuestionsByTag(QuestionDto.searchReqByTag req) {
        TagWord tagWord = this.jpaQueryFactory.selectFrom(QTagWord.tagWord)
                .where(QTagWord.tagWord.tagName.contains(req.getTagName()))
                .fetchFirst();

        if ( tagWord == null ) {
            return new ArrayList<>();
        }

        List<TagMaster> tagMasters = this.jpaQueryFactory.selectFrom(QTagMaster.tagMaster)
                .where(tagMaster.tagWordId.eq(tagWord.getTagWordId()))
                .fetch();

        List<QuestionDto.res> result = new ArrayList<>();

        tagMasters.forEach(tagMaster -> {
            QuestionDto.req newReq = QuestionDto.req.builder()
                    .schoolId(tagMaster.getSchoolId())
                    .limit(req.getLimit())
                    .offset(req.getOffset())
                    .build();
            result.addAll(this.getQuestionsWithTag(newReq));
        });

        return this.makeTagsForQuestions(result);
    }

    private List<QuestionDto.res> makeTagsForQuestions(List<QuestionDto.res> result) {
        result.forEach(queryRes -> {
            List<TagMaster> tagMasters = this.jpaQueryFactory.select(tagMaster)
                    .from(tagMaster)
                    .where(tagMaster.questionId.eq(queryRes.getQuestionId()))
                    .fetch();
            tagMasters.forEach(tagMasterRes -> {
                if(tagMasterRes != null) {
                    List<TagWord> tagWords = this.jpaQueryFactory.select(tagWord)
                            .from(tagWord)
                            .where(tagWord.tagWordId.eq(tagMasterRes.getTagWordId()))
                            .fetch();
                    tagWords.forEach(tagWord -> queryRes.getTagList().add(new TagDto.res(tagMasterRes, tagWord)));
                }
            });
        });
        return result;
    }

    public QuestionDto.resOfUserQuestions getUserQuestionsInfo(Long userId) {
        List<Question> result = this.jpaQueryFactory.selectFrom(question)
                .where(question.userId.eq(userId))
                .fetch();
        Integer totalQuestions = result.size();
        Integer solvedQuestions = result.stream().filter(element -> element.getSolved_YN() == YN.Yes).toArray().length;
        return new QuestionDto.resOfUserQuestions(totalQuestions, solvedQuestions);
    }

    public QuestionDto.resOfSchoolQuestions getQuestionsOfSchoolInfo(Long schoolId) {
        List<Question> result = this.jpaQueryFactory.selectFrom(question)
                .where(question.schoolId.eq(schoolId))
                .fetch();
        Integer totalQuestions = result.size();
        Integer solvedQuestions = result.stream().filter(element -> element.getSolved_YN() == YN.Yes).toArray().length;
        return new QuestionDto.resOfSchoolQuestions(totalQuestions, solvedQuestions);
    }
}
