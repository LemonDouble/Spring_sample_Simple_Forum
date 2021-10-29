package study.forum.repository;

import study.forum.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);
    Optional<User> findById(Long id);
    List<User> findAll();

    // Test 위해 사용
    void Clear();
}
