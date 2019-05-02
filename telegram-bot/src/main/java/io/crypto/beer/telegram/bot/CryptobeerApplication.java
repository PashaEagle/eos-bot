package io.crypto.beer.telegram.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class CryptobeerApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();

		SpringApplication.run(CryptobeerApplication.class, args);
	}

}
