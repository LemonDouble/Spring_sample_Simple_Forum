package study.forum.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import study.forum.domain.User;
import study.forum.service.UserService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserService userService;

    // GET users/register 로 갔을 때, "회원 가입 창" 이 있어야 한다.
    @Test
    public void showRegister() throws Exception {
        // GET /users/register
        mvc.perform(
                MockMvcRequestBuilders.get("/users/register") // GET users/register 로 갔을 때
        )
                .andExpect(MockMvcResultMatchers.status().isOk()) // Return Status 가 200 OK이고
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_HTML)) // content-Type 은 text/html 이다.
                .andExpect(MockMvcResultMatchers.view().name("/users/register")) // view 이름은 /users/register 이다
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("회원 가입 창"))); // "회원 가입 창" 이라는 String을 포함한 HTML 바란다.
    }

    @Test
    public void register() throws Exception {
        // GET /users/register
        mvc.perform(
                        MockMvcRequestBuilders.post("/users/register")// POST users/register 로 갔을 때
                                .param("id", "test_user")
                                .param("password", "test_password") // id, password 를 다음과 같은 Parameter 으로 가졌을 때
                )
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection()) // Return Status 가 300 Redirect
                .andExpect(MockMvcResultMatchers.redirectedUrl("/")); // Root 디렉토리("/") 로 리다이렉트된다.

        // 실제로 유저가 저장되었는지 확인한다.
        List<User> allUser = userService.findAll();
        User registeredUser = allUser.get(0);

        //입력한 ID, 비밀번호가 그대로 저장되었는지 확인
        assertEquals("test_user" ,registeredUser.getLoginId());
        assertEquals("test_password", registeredUser.getLoginPassword());
    }


    @Test
    public void showUsers() throws Exception {
        // GET /users

        //given
        User testUser1 = new User("test_user_1", "test_password_1");
        User testUser2 = new User("test_user_2", "test_password_2");
        userService.register(testUser1);
        userService.register(testUser2);

        List<User> allUser = userService.findAll();
        //when
        mvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.view().name("/users/index"))
                .andExpect(MockMvcResultMatchers.model().attribute("users", Matchers.samePropertyValuesAs(allUser))) // Model에 넘겨준 값이 userService에서 받은 값과 일치하는가?
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("test_user_1"))) // 렌더링한 HTML에 "test_user_1" 이란 String 있는가?
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("test_password_2"))); // 렌더링한 HTML에 "test_password_2" 이란 String 있는가?

        //then   
    }
}