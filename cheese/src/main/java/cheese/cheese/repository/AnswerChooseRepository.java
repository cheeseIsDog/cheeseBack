package cheese.cheese.repository;

import cheese.cheese.entity.AnswerChoose;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerChooseRepository extends JpaRepository<AnswerChoose, Long> {
    Optional<AnswerChoose> getByAnswerIdAndUserId(Long answerId, Long userId);
}
