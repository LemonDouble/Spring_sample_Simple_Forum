package study.forum.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import study.forum.domain.User;
import study.forum.repository.MemoryUserRepository;
import study.forum.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    UserService userService;
    UserRepository userRepository;

    // @BeforeEach annotation : 각 테스트가 실행되기 전에 실행됨.
    @BeforeEach
    public void beforeEach(){
        // userRepository 와 userService를 초기화시켜준다.
        userRepository = new MemoryUserRepository();
        userService = new UserService(userRepository);
    }

    @Test
    public void register() throws Exception {
        //given
        User testUser = getTestUser("id", "pw");

        //when
        Long registerId = userService.register(testUser);
        Optional<User> findUser = userService.findOne(registerId);
        //then
        assertEquals(testUser,findUser.get());
    }

    private User getTestUser(String id, String password) {
        return new User(id,password);
    }

    @Test
    public void findAll() throws Exception {
        //given
        User testUser = getTestUser("id", "pw");
        User testUser2 = getTestUser("id2", "pw2");

        userService.register(testUser);
        userService.register(testUser2);
        //when

        List<User> allUser = userService.findAll();

        //then
        assertEquals(2, allUser.size());
    }
}