package cheese.cheese.repository;

import cheese.cheese.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepository  extends JpaRepository<Answer, Long> {
}
