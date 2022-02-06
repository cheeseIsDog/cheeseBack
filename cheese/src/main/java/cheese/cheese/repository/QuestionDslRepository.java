package cheese.cheese.repository;

import cheese.cheese.dto.QuestionDto;
import cheese.cheese.dto.TagDto;
import cheese.cheese.entity.QTag;
import cheese.cheese.entity.Tag;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static cheese.cheese.entity.QQuestion.question;
import static cheese.cheese.entity.QTag.tag;
import static cheese.cheese.entity.QUser.user;
import static com.querydsl.core.types.Projections.list;

@Slf4j
@Repository
@RequiredArgsConstructor
public class QuestionDslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<QuestionDto.res> getQuestionsWithTag(Long schoolId) {
        List<QuestionDto.res> result = this.jpaQueryFactory.select(
                        Projections.constructor(
                                QuestionDto.res.class,
                                question,
                                user
                        )
                )
                .from(question)
                .where(question.schoolId.eq(schoolId))
                .leftJoin(user).on(user.userId.eq(question.userId))
                .fetch();

        result.stream().forEach(res -> {
            List<Tag> tagList = this.jpaQueryFactory.select(tag)
                    .from(tag)
                    .where(tag.questionId.eq(res.getQuestionId()))
                    .fetch();
            tagList.forEach(tagRes -> {
                    if(tagRes != null) {
                        res.getTagList().add(tagRes.toDto());
                    }
            });

        });
        return result;
    }
}
