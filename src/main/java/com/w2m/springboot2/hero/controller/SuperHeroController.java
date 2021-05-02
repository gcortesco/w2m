package com.w2m.springboot2.hero.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class SuperHeroController {

    @GetMapping("/superheroes")
    public List<String> getAllSuperHeroes() {
        return List.of("1", "2");
    }


}
