package study.forum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.forum.domain.Post;
import study.forum.repository.PostRepository;
import study.forum.repository.UserRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // Post 받아서 저장하고, 해당 Post의 ID 리턴
    public Long savePost(Post post){
        postRepository.save(post);
        return post.getId();
    }

    // ID를 주면 해당 글 조회
    public Optional<Post> findOne(Long id){
        return (postRepository.findById(id));
    }

    // 모든 글 조회
    public List<Post> findAll(){
        return postRepository.findAll();
    }

    // 글 수정, 수정할 글 id와 수정 내용 받아 바꾼다.
    public Long modifyPost(Long id, Post post){
        Post modify = postRepository.modify(id, post);
        return modify.getId();
    }

    public Long deletePost(Long id){
        Post deleted = postRepository.delete(id);
        return deleted.getId();
    }


}
