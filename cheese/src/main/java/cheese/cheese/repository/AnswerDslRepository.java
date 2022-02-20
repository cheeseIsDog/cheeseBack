package cheese.cheese.repository;

import cheese.cheese.dto.AnswerDto;
import cheese.cheese.dto.CommentDto;
import cheese.cheese.dto.QuestionDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;


import java.util.List;

import static cheese.cheese.entity.QComment.comment;
import static cheese.cheese.entity.QUser.user;
import static cheese.cheese.entity.QAnswer.answer;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AnswerDslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<AnswerDto.res> ofQuestion(Long questionId) {
        List<AnswerDto.res> resList = this.jpaQueryFactory.select(
                        Projections.constructor(
                                AnswerDto.res.class,
                                answer,
                                user
                        )
                )
                .from(answer)
                .where(answer.questionId.eq(questionId))
                .leftJoin(user).on(user.userId.eq(answer.userId))
                .fetch();
        resList.forEach(res -> {
            List<CommentDto.res> commentRes = this.jpaQueryFactory.select(
                            Projections.constructor(
                                    CommentDto.res.class,
                                    comment,
                                    user
                            )
                    )
                    .from(comment)
                    .where(comment.answerId.eq(res.getAnswerId()))
                    .leftJoin(user).on(user.userId.eq(comment.userId))
                    .fetch();
            res.getComments().addAll(commentRes);
        });
        return resList;
    }
}
