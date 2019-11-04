package com.codeup.blog.blog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
public class PostController {

    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    @ResponseBody
    public String postsPage(){
        return "<h2>This is the posts index page</h2>";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getPost(@PathVariable int id){
        return "<h2>View an individual post with the id of " + id + "</h2>";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    @ResponseBody
    public String showCreate(){
        return "<h2>View the form for creating a post</h2>";
    }

    @PostMapping(path = "/posts/create")
    @ResponseBody
    public String createPost(){
        return "<h2>create a new post</h2>";
    }

    HashMap<String, Boolean> items = new HashMap<>();



}
