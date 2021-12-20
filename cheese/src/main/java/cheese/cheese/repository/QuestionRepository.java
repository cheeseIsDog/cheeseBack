package cheese.cheese.repository;

import cheese.cheese.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findBySchoolId(Long SchoolId);
}