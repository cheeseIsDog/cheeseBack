package cheese.cheese.repository;

import cheese.cheese.entity.QuestionLikeDislike;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuestionLikeDislikeRepository extends JpaRepository<QuestionLikeDislike, Long> {
    QuestionLikeDislike getByQuestionIdAndUserId(Long questionId, Long userId);
    QuestionLikeDislike getByQuestionId(Long questionId);
    QuestionLikeDislike getByUserId(Long userId);
}
