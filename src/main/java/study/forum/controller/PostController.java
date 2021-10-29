package study.forum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import study.forum.domain.Post;
import study.forum.domain.User;
import study.forum.exception.NotExistIdException;
import study.forum.service.PostService;
import study.forum.service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
// localhost:8080/posts 로 시작하는 요청은 모두 이 컨트롤러에서 받는다.
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;

    // GET localhost:8080/posts 는 아래 showPosts 함수가 처리한다.
    @GetMapping
    public String showPosts(Model model){
        List<Post> allPosts = postService.findAll();
        model.addAttribute("posts", allPosts);

        return "/posts/index";
    }

    // GET localhost:8080/posts/save 는 아래 showSaveForm 함수가 처리한다.
    @GetMapping("/save")
    public String showSaveForm(Model model){
        List<User> allUsers = userService.findAll();
        model.addAttribute("users", allUsers);

        return "/posts/save";
    }

    @PostMapping("/save")
    public String savePosts(@RequestParam Long userId, @RequestParam String title, @RequestParam String content){
        User author = userService.findOne(userId);

        Post post = new Post(author, title, content);

        postService.savePost(post);

        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String showModifyDeleteForm(@PathVariable Long id, Model model){
        Post post = postService.findOne(id);
        List<User> allUser = userService.findAll();
        model.addAttribute("users", allUser);

        model.addAttribute("post", post);
        return "/posts/modify_delete";

    }

    @PostMapping("/modify")
    public String modifyPost(@RequestParam Long postId, @RequestParam Long userId, @RequestParam String title, @RequestParam String content){

        User author = userService.findOne(userId);
        Post modifyPost = new Post(author, title, content);
        postService.modifyPost(postId, modifyPost);

        return "redirect:/posts";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id){
        postService.deletePost(id);

        return "redirect:/posts";
    }

    @GetMapping("/error")
    public String makeError(){
        postService.deletePost(10L);
        return "redirect:/posts";
    }

    @ExceptionHandler(value = NotExistIdException.class)
    @ResponseBody
    public String handleError(Exception e){
        return e.toString();
    }
}
