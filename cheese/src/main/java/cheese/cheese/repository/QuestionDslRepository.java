package cheese.cheese.repository;

import cheese.cheese.dto.QuestionDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static cheese.cheese.entity.QQuestion.question;
import static cheese.cheese.entity.QTag.tag;

@Slf4j
@Repository
@RequiredArgsConstructor
public class QuestionDslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<QuestionDto.res> getQuestionsWithTag(Long schoolId) {
        return this.jpaQueryFactory.select(
                Projections.constructor(
                        QuestionDto.res.class,
                        question,
                        tag)
                )
                .from(question).on(question.schoolId.eq(schoolId))
                .join(tag).on(tag.QuestionId.eq(question.questionId))
                .fetch();
    }
}
