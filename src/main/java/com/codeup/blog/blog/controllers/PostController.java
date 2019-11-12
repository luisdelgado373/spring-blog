package com.codeup.blog.blog.controllers;

import com.codeup.blog.blog.Post;
import com.codeup.blog.blog.Tag;
import com.codeup.blog.blog.repositories.PostRepository;
import com.codeup.blog.blog.repositories.UserRepository;
import com.codeup.blog.blog.repositories.TagRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
public class PostController {

    private PostRepository postDao;
    private TagRepository tagDao;
    private UserRepository userDao;

    public PostController(PostRepository postDao, TagRepository tagDao, UserRepository userDao) {
        this.postDao = postDao;
        this.tagDao = tagDao;
        this.userDao = userDao;
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
    public String showCreateForm() {
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String create(@RequestParam String title, @RequestParam String description) {
        Post postToInsert = new Post(title, description);
        postToInsert.setUser(userDao.getOne(1L));
        Post post = postDao.save(postToInsert);
        return "redirect:/posts/" + post.getId();
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

    //    @GetMapping("/posts/search")
//    @ResponseBody
//    public Post search(@PathVariable String title) {
//        return postDao.findByTitle(title);
//    }
}
