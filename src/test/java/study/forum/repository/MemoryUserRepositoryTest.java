/*
package study.forum.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import study.forum.domain.User;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemoryUserRepositoryTest {

    UserRepository userRepository;

    // @BeforeEach annotation : 각 테스트가 실행되기 전에 실행됨.
    // MemoryUserRepository 를 초기화시켜 준다.
    @BeforeEach
    public void beforeEach(){
        userRepository = new MemoryUserRepository();
    }

    @Test
    public void save() throws Exception {
        //given
        User testUser = getTestUser("id", "pw");

        //when
        User savedUser = userRepository.save(testUser);

        //then
        assertEquals(testUser, savedUser);
    }
    private User getTestUser(String id, String password) {
        return new User(id,password);
    }

    @Test
    public void findOne() throws Exception {
        //given
        User testUser = getTestUser("id", "pw");
        User savedUser = userRepository.save(testUser);
        Long savedUserId = savedUser.getId();
        //when
        Optional<User> findUser = userRepository.findOne(savedUserId);

        //then
        assertEquals(testUser, findUser.get());
    }

    @Test
    public void findAll() throws Exception {
        //given
        User testUser = getTestUser("id", "pw");
        User testUser2 = getTestUser("id2", "pw2");
        userRepository.save(testUser);
        userRepository.save(testUser2);

        //when
        List<User> allUser = userRepository.findAll();

        //then
        assertEquals(2, allUser.size());
    }
}
*/