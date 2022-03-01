package cheese.cheese.repository;

import cheese.cheese.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByEmail(String email);
    Optional<User> findByNickName(String nickName);
    Optional<User> findByUserId(Long id);
}
