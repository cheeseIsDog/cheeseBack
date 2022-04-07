package cheese.cheese.repository;

import cheese.cheese.entity.AnswerLikeDislike;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnswerLikeDislikeRepository extends JpaRepository<AnswerLikeDislike, Long> {
    AnswerLikeDislike getByAnswerIdAndUserId(Long answerId, Long userId);
}
