package cheese.cheese.repository;

import cheese.cheese.entity.Comment;
import cheese.cheese.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByAnswerId(Long AnswerId);
}
