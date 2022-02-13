package cheese.cheese.repository;

import cheese.cheese.dto.QuestionDto;
import cheese.cheese.dto.TagDto;
import cheese.cheese.entity.TagWord;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static cheese.cheese.entity.QQuestion.question;
import static cheese.cheese.entity.QTagMaster.tagMaster;
import static cheese.cheese.entity.QTagWord.tagWord;
import static cheese.cheese.entity.QUser.user;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TagDslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<TagDto.res> searchTags(TagDto.req req) {
        return this.jpaQueryFactory.select(
                        Projections.constructor(
                                TagDto.res.class,
                                tagMaster,
                                tagWord
                        )
                )
                .from(tagWord)
                .where(
                        tagWord.tagWordId.eq(tagMaster.tagWordId)
                                .and(tagWord.tagName.contains(req.getTagName())))
                .leftJoin(tagMaster)
                .on(tagMaster.tagWordId.eq(tagWord.tagWordId))
                .fetch();
    }
}
