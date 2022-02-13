package cheese.cheese.service;

import cheese.cheese.dto.QuestionDto;
import cheese.cheese.entity.Question;
import cheese.cheese.entity.TagMaster;
import cheese.cheese.entity.TagWord;
import cheese.cheese.repository.TagMasterRepository;
import cheese.cheese.repository.TagWordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagMasterRepository tagMasterRepository;
    private final TagWordRepository tagWordRepository;

    public void saveTags(QuestionDto.gen gen, Question question) {
        gen.getTags().forEach(tag -> {
            TagWord tagWord = this.tagWordRepository.save(new TagWord(tag));
            TagMaster tagMaster = TagMaster.builder()
                    .questionId(question.getQuestionId())
                    .schoolId(question.getSchoolId())
                    .tagWordId(tagWord.getTagWordId())
                    .build();
            this.tagMasterRepository.save(tagMaster);
        });
    }
}
