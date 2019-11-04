package com.codeup.blog.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MathController {

    @GetMapping("/")
    @ResponseBody
    public String index(){
        return "<h2>This is the landing page!</h2>";
    }

    @RequestMapping(path = "/add/{num1}/and/{num2}", method = RequestMethod.GET)
    @ResponseBody
    public String add(@PathVariable int num1, @PathVariable int num2){
        return "<h2>" + num1 + " plus " + num2 + " is " + (num1 + num2)+ "</h2>";
    }

    @RequestMapping(path = "/subtract/{num1}/from/{num2}", method = RequestMethod.GET)
    @ResponseBody
    public String subtract(@PathVariable int num1, @PathVariable int num2){
        return "<h2>" + num1 + " minus " + num2 + " is " + (num2 - num1)+ "</h2>";
    }

    @RequestMapping(path = "/multiply/{num1}/and/{num2}", method = RequestMethod.GET)
    @ResponseBody
    public String multiply(@PathVariable int num1, @PathVariable int num2){
        return "<h2>" + num1 + " times " + num2 + " is " + (num1 * num2)+ "</h2>";
    }

    @RequestMapping(path = "/divide/{num1}/from/{num2}", method = RequestMethod.GET)
    @ResponseBody
    public String divide(@PathVariable int num1, @PathVariable int num2){
        return "<h2>" + num1 + " divided by " + num2 + " is " + (num1/num2)+ "</h2>";
    }
}
