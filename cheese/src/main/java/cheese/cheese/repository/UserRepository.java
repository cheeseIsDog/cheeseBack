package cheese.cheese.repository;

import cheese.cheese.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByIdAndPassword(String id, String password);
    Optional<User> findById(String id);
}
