package com.w2m.springboot2.hero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SuperHeroRepository extends JpaRepository<SuperHeroRepository, Long>{

}