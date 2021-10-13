package study.forum.repository;

import study.forum.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);
    Optional<User> findOne(Long id);
    List<User> findAll();

    // Test 위해
    void Clear();
}
