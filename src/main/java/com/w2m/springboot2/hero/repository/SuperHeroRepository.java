package com.w2m.springboot2.hero.repository;

import com.w2m.springboot2.hero.model.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SuperHeroRepository extends JpaRepository<SuperHero, Long>{
    List<SuperHero> findByNameContaining(String name);

}