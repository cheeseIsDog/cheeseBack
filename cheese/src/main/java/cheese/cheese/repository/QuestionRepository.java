package cheese.cheese.repository;

import cheese.cheese.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findByTitleAndSchoolId(String title, Long SchoolId);
    Optional<Question> findByQuestionId(Long questionId);
    Integer countQuestionsByUserId(Long userId);
}