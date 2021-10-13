package study.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.forum.domain.User;
import study.forum.repository.UserRepository;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long register(User user){
        userRepository.save(user);
        return user.getId();
    }

    public Optional<User> findOne(Long id){
        return userRepository.findOne(id);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void clear(){
        userRepository.Clear();
    }

}
