package com.w2m.springboot2.hero.service;

import com.w2m.springboot2.hero.exception.ResourceNotFoundException;
import com.w2m.springboot2.hero.model.SuperHero;
import com.w2m.springboot2.hero.repository.SuperHeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SuperHeroService {

    @Autowired
    private SuperHeroRepository superHeroRepository;

    public List<SuperHero> getAllHeroes() {
        return superHeroRepository.findAll();
    }

    public List<SuperHero> findByName(String name){
        return superHeroRepository.findByNameContaining(name);
    }

    public Optional<SuperHero> findById(Long superHeroId){
        return superHeroRepository.findById(superHeroId);
    }

    public SuperHero save(SuperHero superHero){
        return superHeroRepository.save(superHero);
    }

    public void delete(SuperHero superHero){
        superHeroRepository.delete(superHero);
    }


}
