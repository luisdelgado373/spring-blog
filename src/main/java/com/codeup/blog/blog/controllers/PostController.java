package com.codeup.blog.blog.controllers;

import com.codeup.blog.blog.Post;
import com.codeup.blog.blog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping(path = "/posts")
    public String index(Model viewModel) {
        viewModel.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String show(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("post", postDao.getOne(id));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String showCreateForm() {
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String create(@RequestParam String title, @RequestParam String body) {
        Post post = postDao.save(new Post(title, body));
        return "redirect:/posts/" + post.getId();
    }

//    @GetMapping("/posts/search")
//    @ResponseBody
//    public Post search(@PathVariable String title) {
//        return postDao.findByTitle(title);
//    }

    @GetMapping("/posts/{id}/edit")
    public String edit(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("post", postDao.getOne(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String update(@PathVariable long id, @RequestParam String title, @RequestParam String description) {
        Post oldPost = postDao.getOne(id);
        oldPost.setTitle(title);
        oldPost.setDescription(description);
        postDao.save(oldPost);
        return "redirect:/posts/" + id;
    }

    @PostMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id) {
        postDao.deleteById(id);
        return "redirect:/posts";
    }

//    @ResponseBody
//    @GetMapping("/ads/length")
//    public List<String> returnAdsByLength() {
//        return postDao.getPostsOfCertainTitleLength();
//    }
//
//    @ResponseBody
//    @GetMapping("/ads/length/native")
//    public List<String> returnAdsByLengthNative() {
//        return postDao.getPostsOfCertainTitleLengthNative();
//    }

}
