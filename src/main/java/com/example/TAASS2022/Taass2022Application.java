package com.example.TAASS2022;

import com.example.TAASS2022.model.Ticket;
import com.example.TAASS2022.service.TicketService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Taass2022Application {

	public static void main(String[] args) {
		SpringApplication.run(Taass2022Application.class, args);
	}

	@Bean
	CommandLineRunner runner(TicketService ticketService){
		return args -> {
			ticketService.initialBoot();
		};


	}
}
