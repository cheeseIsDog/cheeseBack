package cheese.cheese.repository;

import cheese.cheese.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SchoolRepository extends JpaRepository<School, Long> {
    List<School> findAll();
}
