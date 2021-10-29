package study.forum.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import study.forum.domain.Post;
import study.forum.domain.User;
import study.forum.exception.NotExistIdException;
import study.forum.repository.JPAPostRepository;
import study.forum.repository.PostRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;


    @Test
    public void 글저장() throws Exception {
        //given
        User registeredUser = getRegisteredUser("id","pw");
        Post testPost = getTestPost(registeredUser, "this is title" , "this is content");
        //when
        Long saveId = postService.savePost(testPost);
        //then
        Post savedPost = postService.findOne(saveId);
        org.assertj.core.api.Assertions.assertThat(testPost).usingRecursiveComparison().isEqualTo(savedPost);
    }

    private User getRegisteredUser(String id, String password) {
        User testUser = new User(id, password);
        Long registerId = userService.register(testUser);
        return userService.findOne(registerId);
    }

    private Post getTestPost(User author, String title, String content) {
        return new Post(author, title, content);
    }

    @Test
    public void 글_조회_성공() throws Exception {
        //given
        User registeredUser = getRegisteredUser("id","pw");
        Post testPost = getTestPost(registeredUser, "this is title" , "this is content");
        Long savedPost = postService.savePost(testPost);
        //when
        Post findPost = postService.findOne(savedPost);

        //then
        assertEquals(testPost,findPost);
    }

    @Test
    public void 글_조회_실패() throws Exception {
        //given
        User registeredUser = getRegisteredUser("id","pw");
        Post testPost = getTestPost(registeredUser, "this is title" , "this is content");
        Long savedPost = postService.savePost(testPost);
        //when
        NotExistIdException exception = assertThrows(NotExistIdException.class, () -> {
            Post findPost = postService.findOne(1L);
        });
        //then

        String message = exception.getMessage();
        assertEquals("해당 ID가 존재하지 않습니다!", message);
    }

    @Test
    public void 모든_글_조회() throws Exception {
        //given
        User registeredUser = getRegisteredUser("id","pw");
        Post testPost = getTestPost(registeredUser, "this is title" , "this is content");
        Post anotherTestPost = getTestPost(registeredUser, "this is another title" , "this is another content");

        postService.savePost(testPost);
        postService.savePost(anotherTestPost);

        //when

        List<Post> allPost = postService.findAll();

        //then
        assertEquals(2,allPost.size());
    }

    @Test
    public void 글수정() throws Exception {
        //given
        User registeredUser = getRegisteredUser("id","pw");
        User anotherRegisteredUser = getRegisteredUser("id1", "pw1");
        Post testPost = getTestPost(registeredUser, "this is title" , "this is content");
        Post anotherTestPost = getTestPost(anotherRegisteredUser, "this is another title" , "this is another content");

        Long savedId = postService.savePost(testPost);
        //when

        Long modifiedId  = postService.modifyPost(savedId, anotherTestPost);
        Post modifiedPost = postService.findOne(modifiedId);

        //then
        assertEquals(savedId, modifiedId);
        assertEquals(anotherTestPost.getTitle(), modifiedPost.getTitle());
        assertEquals(anotherTestPost.getContent(), modifiedPost.getContent());
        assertEquals(anotherTestPost.getAuthor(), modifiedPost.getAuthor());
    }

    @Test
    public void 글삭제() throws Exception {
        //given
        User registeredUser = getRegisteredUser("id","pw");
        Post testPost = getTestPost(registeredUser, "this is title" , "this is content");
        Long savedId = postService.savePost(testPost);

        //when
        Long deletedId = postService.deletePost(savedId);
        NotExistIdException exception = assertThrows(NotExistIdException.class, () -> {
            Post deletedPost = postService.findOne(deletedId);
        });
        //then
        String message = exception.getMessage();
        assertEquals("해당 ID가 존재하지 않습니다!",message);

    }
}