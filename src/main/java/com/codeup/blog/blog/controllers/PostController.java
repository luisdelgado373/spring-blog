package com.codeup.blog.blog.controllers;


import com.codeup.blog.blog.Ad;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class PostController {

    ArrayList<Ad> adsList;

    public PostController() {
        adsList = new ArrayList<Ad>();

        adsList.add(new Ad(1, "First Ad", "First Description"));
        adsList.add(new Ad(2, "Second Ad", "Second Description"));
        adsList.add(new Ad(3, "Third Ad", "Third Description"));
    }

    @GetMapping(path = "/ads")
    public String index(Model viewModel) {
        viewModel.addAttribute("ads", adsList);

        return "/ads/index";
    }

    @GetMapping("/ads/{id}")
    public String show(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("ad", adsList.get((int) id - 1));
        return "ads/show";
    }

    @GetMapping("/ads/create")
    @ResponseBody
    public String showCreateForm() {
        return "<h2>View the form for creating an ad</h2>";
    }

    @PostMapping("/ads/create")
    @ResponseBody
    public String create(@RequestParam String title, @RequestParam String body) {
        System.out.println("title: " + title);
        System.out.println("body: " + body);
        return "Create a new ad";
    }

}
