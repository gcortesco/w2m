package com.w2m.springboot2.hero;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.w2m.springboot2.hero.Application;
import com.w2m.springboot2.hero.model.SuperHero;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SuperHeroControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllEmployees() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/v1/superheroes",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetSuperHeroById() {
		SuperHero superHero = restTemplate.getForObject(getRootUrl() + "/api/v1/superheroes/1", SuperHero.class);
		assertNotNull(superHero);
		assertEquals(superHero.getName(),"Superman");
	}

	@Test
	public void testCreateSuperHero() {
		SuperHero superHero = new SuperHero();
		superHero.setId(4);
		superHero.setName("Manoloito El Fuerte");

		ResponseEntity<SuperHero> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/v1/superheroes", superHero, SuperHero.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
		assertEquals(postResponse.getBody().getName(),"Manoloito El Fuerte");
	}

	@Test
	public void testUpdateSuperHero() {
		int id = 1;
		SuperHero superHero = restTemplate.getForObject(getRootUrl() + "/api/v1/superheroes/" + id, SuperHero.class);
		superHero.setName("pablo el fuerte");

		restTemplate.put(getRootUrl() + "/api/v1/superheroes/" + id, superHero);

		SuperHero updatedEmployee = restTemplate.getForObject(getRootUrl() + "/api/v1/superheroes/" + id, SuperHero.class);
		assertNotNull(updatedEmployee);
		assertEquals(updatedEmployee.getName(), "pablo el fuerte");
	}

	@Test
	public void testDeleteSuperHero() {
		int id = 2;
		SuperHero superHero = restTemplate.getForObject(getRootUrl() + "/api/v1/superheroes/" + id, SuperHero.class);
		assertNotNull(superHero);

		restTemplate.delete(getRootUrl() + "/api/v1/superheroes/" + id);

		try {
			superHero = restTemplate.getForObject(getRootUrl() + "/api/v1/superheroes/" + id, SuperHero.class);
		} catch (HttpClientErrorException e ) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}
