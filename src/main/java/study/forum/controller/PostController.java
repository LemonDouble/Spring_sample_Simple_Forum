package study.forum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import study.forum.domain.Post;
import study.forum.domain.User;
import study.forum.service.PostService;
import study.forum.service.UserService;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public String showModifyDeleteForm(@PathVariable Long id, Model model){
        Optional<Post> post = postService.findOne(id);
        List<User> allUser = userService.findAll();
        model.addAttribute("users", allUser);

        if(post.isPresent()){
            model.addAttribute("post", post.get());
            return "/posts/modify_delete";
        }else{
            return "redirect:/";
        }
    }

    @PostMapping("/modify")
    public String modifyPost(@RequestParam Long postId, @RequestParam Long userId, @RequestParam String title, @RequestParam String content){

        Post modifyPost = new Post(userId, title, content);
        postService.modifyPost(postId, modifyPost);

        return "redirect:/posts";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id){
        postService.deletePost(id);

        return "redirect:/posts";
    }
}
