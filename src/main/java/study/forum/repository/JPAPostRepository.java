package study.forum.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.forum.domain.Post;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class JPAPostRepository implements PostRepository{

    private final EntityManager em;

    @Override
    public void save(Post post) {
        em.persist(post);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(em.find(Post.class, id));
    }

    @Override
    public List<Post> findAll() {
        return em.createQuery("select p from Post p",Post.class).getResultList();
    }

    @Override
    public void delete(Long id) {
        Post findPost =em.find(Post.class, id);
        em.remove(findPost);
    }

    @Override
    public void clear() {

    }
}
