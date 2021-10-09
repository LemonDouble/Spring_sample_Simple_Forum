package study.forum.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import study.forum.domain.Post;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemoryPostRepositoryTest {

    PostRepository postRepository;

    // @BeforeEach annotation : 각 테스트가 실행되기 전에 실행됨.
    // MemoryPostRepository 를 초기화시켜 준다.
    @BeforeEach
    public void beforeEach(){
        postRepository = new MemoryPostRepository();
    }

    @Test
    public void 저장() throws Exception {
        //given
        Post testPost = getTestPost(1L, "this is title" , "this is content");
        //when
        Post savedPost = postRepository.save(testPost);

        //then
        assertEquals(testPost, savedPost);
    }
    private Post getTestPost(Long userId, String title, String content) {
        return new Post(userId, title, content);
    }

    @Test
    public void findById_성공() throws Exception {
        //given
        Post testPost = getTestPost(1L, "this is title" , "this is content");
        Post savedPost = postRepository.save(testPost);
        Long savedPostId = savedPost.getId();
        //when
        Optional<Post> findPost = postRepository.findById(savedPostId);

        //then
        assertEquals(testPost, findPost.get());
    }

    @Test
    public void findById_실패() throws Exception {
        //given
        Post testPost = getTestPost(1L, "this is title" , "this is content");
        Post savedPost = postRepository.save(testPost);
        Long savedPostId = savedPost.getId();
        //when
        Optional<Post> findPost = postRepository.findById(1L);

        //then
        assertFalse(findPost.isPresent());
    }

    @Test
    public void findAll() throws Exception {
        //given
        Post testPost = getTestPost(1L, "this is title" , "this is content");
        Post anotherTestPost = getTestPost(1L, "this is another title" , "this is another content");

        postRepository.save(testPost);
        postRepository.save(anotherTestPost);
        //when
        List<Post> allPost = postRepository.findAll();

        //then
        assertEquals(2, allPost.size());
    }

    @Test
    public void modify() throws Exception {
        //given
        Post testPost = getTestPost(1L, "this is title" , "this is content");
        Post anotherTestPost = getTestPost(1L, "this is another title" , "this is another content");

        Post savedPost = postRepository.save(testPost);
        Long savedPostId = savedPost.getId();
        anotherTestPost.setId(savedPostId);
        //when
        Post modifyPost = postRepository.modify(savedPostId, anotherTestPost);
        //then
        assertEquals(anotherTestPost, modifyPost);
    }

    @Test
    public void modify실패_유효하지_않은_ID() throws Exception {
        //given
        Post testPost = getTestPost(1L, "this is title" , "this is content");
        Post anotherTestPost = getTestPost(1L, "this is another title" , "this is another content");

        Post savedPost = postRepository.save(testPost);
        Long savedPostId = savedPost.getId();
        anotherTestPost.setId(savedPostId);
        //when
        IllegalStateException excpetion = assertThrows(IllegalStateException.class,() ->{
            Post modifyPost = postRepository.modify(1L, anotherTestPost);
        });
        //then
        String message = excpetion.getMessage();
        assertEquals("존재하지 않는 POST 입니다.", message);
    }

    @Test
    public void delete() throws Exception {
        //given
        Post testPost = getTestPost(1L, "this is title" , "this is content");
        Post savedPost = postRepository.save(testPost);
        Long savedPostId = savedPost.getId();
        //when
        postRepository.delete(savedPostId);
        Optional<Post> deletedPost = postRepository.findById(savedPostId);

        //then
        assertFalse(deletedPost.isPresent());
    }

    @Test
    public void delete실패_유효하지_않은_ID() throws Exception {
        //given
        Post testPost = getTestPost(1L, "this is title" , "this is content");
        Post savedPost = postRepository.save(testPost);
        Long savedPostId = savedPost.getId();
        //when
        IllegalStateException exception = assertThrows(IllegalStateException.class,()->{
            postRepository.delete(1L);
        });

        //then
        String message = exception.getMessage();
        assertEquals("존재하지 않는 POST 입니다.", message);
    }
}