package com.myftiu.demo.reactive.client;


import com.myftiu.demo.reactive.dto.Event;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@SpringBootApplication
public class ReactiveClient {

   private static final String SERVICE_URL = "http://localhost:8080";

   @Bean
   WebClient client(){
      return WebClient.create(SERVICE_URL);
   }


   @Bean
   CommandLineRunner runner() {
      return args -> {
         client().get().uri("/events/subscribe")
            .accept(MediaType.TEXT_EVENT_STREAM)
            .exchange()
            .flatMapMany(cr -> cr.bodyToFlux(Event.class))
            .subscribe(System.out::println);
      };
   }

   public static void main(String... args) {
      new SpringApplicationBuilder(ReactiveClient.class)
         .properties(Collections.singletonMap("server.port", "8081"))
         .run(args);
   }

}
