package study.forum.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import study.forum.domain.User;
import study.forum.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void register() throws Exception {
        //given
        User testUser = getTestUser("id", "pw");

        //when
        Long registerId = userService.register(testUser);
        User findUser = userService.findOne(registerId);
        //then
        assertEquals(testUser.getLoginId(), findUser.getLoginId());
        assertEquals(testUser.getLoginPassword(), findUser.getLoginPassword());
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