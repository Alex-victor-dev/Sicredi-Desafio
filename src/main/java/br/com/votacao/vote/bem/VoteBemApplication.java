package br.com.votacao.vote.bem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@SpringBootApplication
public class VoteBemApplication {
	@GetMapping
	public String voteBem() {
		return "Bem Vindo ao Vote Bem";
	}
	public static void main(String[] args) {
		SpringApplication.run(VoteBemApplication.class, args);
	}

}
