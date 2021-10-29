package study.forum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.forum.domain.Post;
import study.forum.exception.NotExistIdException;
import study.forum.repository.PostRepository;
import study.forum.repository.UserRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;

    // Post 받아서 저장하고, 해당 Post의 ID 리턴
    public Long savePost(Post post){
        postRepository.save(post);
        return post.getId();
    }

    // ID를 주면 해당 글 조회
    @Transactional(readOnly = true)
    public Post findOne(Long id){
        validateId(id);
        return postRepository.findById(id).get();
    }

    // 모든 글 조회
    @Transactional(readOnly = true)
    public List<Post> findAll(){
        return postRepository.findAll();
    }

    // 글 수정, 수정할 글 id와 수정 내용 받아 바꾼다.
    public Long modifyPost(Long id, Post post){
        // 수정할 글이 존재하는지 검증
        validateId(id);

        Optional<Post> findPost = postRepository.findById(id);
        Post modifiedPost = findPost.get();
        modifiedPost.setTitle(post.getTitle());
        modifiedPost.setContent(post.getContent());
        modifiedPost.setAuthor(post.getAuthor());
        return modifiedPost.getId();
    }

    public Long deletePost(Long id){
        //지울 글이 존재하는지 검증
        validateId(id);
        postRepository.delete(id);
        return id;
    }

    public void clear(){
        clear();
    }

    // 해당 ID가 유효한 값인지 검증
    @Transactional(readOnly = true)
    private void validateId(Long id){
        Optional<Post> findPost = postRepository.findById(id);

        if(findPost.isEmpty()){
            throw new NotExistIdException("해당 ID가 존재하지 않습니다!");
        }
    }

}
