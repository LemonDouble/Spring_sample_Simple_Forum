package study.forum.repository;

import study.forum.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    void save(Post post);
    Optional<Post> findById(Long id);
    List<Post> findAll();
    void delete(Long id);

    // Test 위해 사용
    void clear();
}
