package com.w2m.springboot2.hero.controller;

import com.w2m.springboot2.hero.exception.ResourceNotFoundException;
import com.w2m.springboot2.hero.model.SuperHero;
import com.w2m.springboot2.hero.repository.SuperHeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1")
public class SuperHeroController {

    @Autowired
    private SuperHeroRepository superHeroRepository;

    @GetMapping("/superheroes")
    public List<SuperHero> getAllHeroes() {
        return superHeroRepository.findAll();
    }

    @GetMapping("/superheroes/search")
    public ResponseEntity<List<SuperHero>> getSuperHeroByName(@RequestParam String name)
            throws ResourceNotFoundException {
        List<SuperHero> superHeroes = superHeroRepository.findByNameContaining(name);
        if (superHeroes.isEmpty() ) {
            throw new ResourceNotFoundException("SuperHero not found for this name");
        }
        return ResponseEntity.ok().body(superHeroes);
    }

    @GetMapping("/superheroes/{id}")
    public ResponseEntity<SuperHero> getSuperHeroById(@PathVariable(value = "id") Long superHeroId)
            throws ResourceNotFoundException {
        SuperHero superHero = superHeroRepository.findById(superHeroId)
                .orElseThrow(() -> new ResourceNotFoundException("SuperHero not found for this id :: " + superHeroId));
        return ResponseEntity.ok().body(superHero);
    }


    @PostMapping("/superheroes/create")
    public SuperHero createSuperHero(@Valid @RequestBody SuperHero superHero) {
        return superHeroRepository.save(superHero);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<SuperHero> updateSuperHero(@PathVariable(value = "id") Long superHeroId,
                                                   @Valid @RequestBody SuperHero superHeroDetails) throws ResourceNotFoundException {
        SuperHero superHero = superHeroRepository.findById(superHeroId)
                .orElseThrow(() -> new ResourceNotFoundException("SuperHero not found for this id :: " + superHeroId));
        superHero.setName(superHeroDetails.getName());
        return ResponseEntity.ok().body(superHero);
    }

    @DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long superHeroId)
            throws ResourceNotFoundException {

        SuperHero superHero = superHeroRepository.findById(superHeroId)
                .orElseThrow(() -> new ResourceNotFoundException("SuperHero not found for this id :: " + superHeroId));

        superHeroRepository.delete(superHero);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }





}
