package study.forum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.forum.domain.User;
import study.forum.exception.NotExistIdException;
import study.forum.repository.UserRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public Long register(User user){
        userRepository.save(user);
        return user.getId();
    }

    @Transactional(readOnly = true)
    public User findOne(Long id){
        validateId(id);
        Optional<User> findUser = userRepository.findById(id);
        return findUser.get();
    }

    @Transactional(readOnly = true)
    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void clear(){
        userRepository.Clear();
    }

    // 해당 ID가 유효한 값인지 검증
    @Transactional(readOnly = true)
    private void validateId(Long id){
        Optional<User> findUser = userRepository.findById(id);

        if(findUser.isEmpty()){
            throw new NotExistIdException("해당 ID가 존재하지 않습니다!");
        }
    }

}
