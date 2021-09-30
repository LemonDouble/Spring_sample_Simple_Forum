package study.forum.repository;

import study.forum.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post);
    Optional<Post> findById(Long id);
    List<Post> findAll();
    Post modify(Long id, Post post);
    Post delete(Long id);
}
