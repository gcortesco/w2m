package com.w2m.springboot2.hero;



import com.w2m.springboot2.hero.model.SuperHero;
import com.w2m.springboot2.hero.repository.SuperHeroRepository;

import com.w2m.springboot2.hero.service.SuperHeroService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class SuperHeroServiceTest {

    @Autowired
    private SuperHeroService superHeroService;

    @MockBean
    private SuperHeroRepository superHeroRepository;

    @Test
    public void getAllHeroesTest() {
        List<SuperHero> list = new ArrayList<>();
        SuperHero superHeroOne = new SuperHero("Superman");
        SuperHero superHeroTwo = new SuperHero("Spiderman");
        SuperHero superHeroThree = new SuperHero("AquaMan");

        list.add(superHeroOne);
        list.add(superHeroTwo);
        list.add(superHeroThree);

        when(superHeroRepository.findAll()).thenReturn(list);


        List<SuperHero> empList = superHeroService.getAllHeroes();

        assertEquals(3, empList.size());
        verify(superHeroRepository, times(1)).findAll();
    }

}
