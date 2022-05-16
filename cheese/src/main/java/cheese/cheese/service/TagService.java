package cheese.cheese.service;

import cheese.cheese.dto.Enum.Consts;
import cheese.cheese.dto.QuestionDto;
import cheese.cheese.dto.TagDto;
import cheese.cheese.entity.Question;
import cheese.cheese.entity.TagMaster;
import cheese.cheese.entity.TagWord;
import cheese.cheese.repository.TagDslRepository;
import cheese.cheese.repository.TagMasterRepository;
import cheese.cheese.repository.TagWordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagMasterRepository tagMasterRepository;
    private final TagWordRepository tagWordRepository;
    private final TagDslRepository tagDslRepository;

    public void saveTags(QuestionDto.gen gen, Question question) {
        gen.getTags().forEach(tag -> {
            TagWord tagWord = this.tagWordRepository.getByTagName(tag);
            if(tagWord == null) {
                tagWord = this.tagWordRepository.save(new TagWord(tag));
            }
            TagMaster tagMaster = TagMaster.builder()
                    .questionId(question.getQuestionId())
                    .schoolId(question.getSchoolId())
                    .tagWordId(tagWord.getTagWordId())
                    .build();
            this.tagMasterRepository.save(tagMaster);
        });
    }

    public List<TagDto.res> searchTags(TagDto.req req) {
        if ( Consts.BLANK.equals(req.getTagName()) ) {
            return new ArrayList<>();
        }
        List<TagWord> tempList = this.tagWordRepository.getByTagNameContaining(req.getTagName());
        return tempList
                .stream()
                .map(tagWord -> new TagDto.res(tagWord))
                .collect(Collectors.toList());
    }
}
