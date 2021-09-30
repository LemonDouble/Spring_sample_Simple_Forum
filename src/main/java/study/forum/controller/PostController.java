package study.forum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import study.forum.domain.Post;
import study.forum.domain.User;
import study.forum.service.PostService;
import study.forum.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @GetMapping
    public String showPosts(Model model){
        List<Post> allPosts = postService.findAll();
        model.addAttribute("posts", allPosts);

        return "/posts/index";
    }

    @GetMapping("/save")
    public String showSaveForm(Model model){
        List<User> allUsers = userService.findAll();
        model.addAttribute("users", allUsers);

        return "/posts/save";
    }

    @PostMapping("/save")
    public String savePosts(@RequestParam Long userId, @RequestParam String title, @RequestParam String content){
        Post post = new Post(userId, title, content);

        postService.savePost(post);

        return "redirect:/";
    }
}
