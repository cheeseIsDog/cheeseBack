package cheese.cheese.repository;

import cheese.cheese.entity.TagWord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagWordRepository extends JpaRepository<TagWord, Long> {
    TagWord getByTagName(String tagName);
    List<TagWord> getByTagNameContaining(String tagName);
}
