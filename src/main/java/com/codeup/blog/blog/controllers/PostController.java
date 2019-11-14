package com.codeup.blog.blog.controllers;

import com.codeup.blog.blog.models.User;
import com.codeup.blog.blog.services.EmailService;
import com.codeup.blog.blog.models.Post;
import com.codeup.blog.blog.models.Tag;
import com.codeup.blog.blog.repositories.PostRepository;
import com.codeup.blog.blog.repositories.UserRepository;
import com.codeup.blog.blog.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final TagRepository tagDao;
    private final UserRepository userDao;

    @Autowired
    private final EmailService emailService;

    public PostController(PostRepository postDao, TagRepository tagDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.tagDao = tagDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @GetMapping("/post-tags/{id}")
    public String showPostTag(@PathVariable long id, Model vModel) {
        vModel.addAttribute("post", postDao.getOne(id));
        return "posts/tags";
    }

    @PostMapping("/tags/posts/{id}")
    public String assignNewTagToPost(@PathVariable int id, @RequestParam String name) {
        Post post = postDao.getOne((long) id);
        tagDao.save(new Tag(name, Arrays.asList(post)));
        return "redirect:/post-tags/" + id;
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

    @GetMapping("/posts/{id}/history")
    public String postHistory(@PathVariable long id, Model viewModel) {
        Post post = postDao.getOne(id);
        viewModel.addAttribute("post", post);
        return "posts/postHistory";
    }

    @GetMapping("/posts/create")
    public String showCreateForm(Model vModel) {
        vModel.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String create(@ModelAttribute Post postToBeCreated) {
        User current = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        postToBeCreated.setUser(current);
        Post savedPost = postDao.save(postToBeCreated);
        emailService.prepareAndSend(savedPost, "Created Post", "A Post has been created with the id of " + savedPost.getId());
        return "redirect:/posts/" + savedPost.getId();
    }

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
//    @GetMapping("/posts/length")
//    public List<String> returnPostsByLength() {
//        return postDao.getPostsOfCertainTitleLength();
//    }
//
//    @ResponseBody
//    @GetMapping("/posts/length/native")
//    public List<String> returnPostsByLengthNative() {
//        return postDao.getPostsOfCertainTitleLengthNative();
//    }

    //    @GetMapping("/posts/search")
//    @ResponseBody
//    public Post search(@PathVariable String title) {
//        return postDao.findByTitle(title);
//    }
}
