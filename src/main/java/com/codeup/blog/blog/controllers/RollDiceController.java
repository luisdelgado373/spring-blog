package com.codeup.blog.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String index() {
        return "dice-roll";
    }

    @GetMapping("/roll-dice/{n}")
    public String diceGuess(@PathVariable int n, Model vModel) {
        int random = (int) (Math.random() * 6 + 1);

        if (n == random) {
            return "dice-win";
        } else {
            vModel.addAttribute("wrong", "Incorrect, the correct number was " + random + ".");
            return "dice-roll";
        }
    }
}
