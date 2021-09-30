package study.forum.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import study.forum.domain.Post;
import study.forum.repository.MemoryPostRepository;
import study.forum.repository.PostRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class PostServiceTest {
    PostRepository postRepository;
    PostService postService;

    @BeforeEach
    public void beforeEach(){
        postRepository = new MemoryPostRepository();
        postService = new PostService(postRepository);
    }


    @Test
    public void 글저장() throws Exception {
        //given
        Post testPost = getTestPost(1L, "this is title" , "this is content");
        //when
        Long saveId = postService.savePost(testPost);
        //then
        Assertions.assertEquals(0,saveId); //0번 글로 저장
    }

    private Post getTestPost(Long userId, String title, String content) {
        return new Post(userId, title, content);
    }

    @Test
    public void 글_조회_성공() throws Exception {
        //given
        Post testPost = getTestPost(1L, "this is title" , "this is content");
        Long savedPost = postService.savePost(testPost);
        //when
        Optional<Post> findPost = postService.findOne(savedPost);

        //then
        assertEquals(testPost,findPost.get());
    }

    @Test
    public void 글_조회_실패() throws Exception {
        //given
        Post testPost = getTestPost(1L, "this is title" , "this is content");
        Long savedPost = postService.savePost(testPost);
        //when
        Optional<Post> findPost = postService.findOne(1L);

        //then
        assertFalse(findPost.isPresent());
    }

    @Test
    public void 모든_글_조회() throws Exception {
        //given
        Post testPost = getTestPost(1L, "this is title" , "this is content");
        Post anotherTestPost = getTestPost(1L, "this is another title" , "this is another content");

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
        Post testPost = getTestPost(1L, "this is title" , "this is content");
        Post anotherTestPost = getTestPost(1L, "this is another title" , "this is another content");

        Long savedId = postService.savePost(testPost);
        //when
        Long modifiedId  = postService.modifyPost(savedId, anotherTestPost);
        Optional<Post> findPost = postService.findOne(modifiedId);

        //then
        assertEquals(anotherTestPost, findPost.get());
    }

    @Test
    public void 글삭제() throws Exception {
        //given
        Post testPost = getTestPost(1L, "this is title" , "this is content");
        Long savedId = postService.savePost(testPost);

        //when
        Long deletedId = postService.deletePost(savedId);
        Optional<Post> deletedPost = postService.findOne(deletedId);
        //then
        assertFalse(deletedPost.isPresent());
    }
}