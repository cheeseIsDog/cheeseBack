package cheese.cheese.repository;

import cheese.cheese.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface SchoolRepository extends JpaRepository<School, Long> {
    List<School> findAll();
    Optional<School> getBySchoolId(Long schoolId);
}
