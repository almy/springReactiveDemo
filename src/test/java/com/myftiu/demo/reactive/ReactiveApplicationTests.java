package com.myftiu.demo.reactive;

import com.myftiu.demo.reactive.service.ReactiveApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ReactiveApplication.class})
public class ReactiveApplicationTests {

	private WebTestClient webTestClient;

	@Before
	public void setup() {
		this.webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();
	}

	@Test
	public void contextLoads() {

		webTestClient.get().uri("/events/21").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().is2xxSuccessful();
	}

}
