package com.abnamro.recipe.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @GetMapping("/")
    String home() {
        return "Hello, the recipe application!";
    }
}