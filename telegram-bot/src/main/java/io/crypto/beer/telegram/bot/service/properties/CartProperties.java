package io.crypto.beer.telegram.bot.service.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Component
@ConfigurationProperties("cart")
public class CartProperties {
	
	private Path path;
	
	@Setter
	@Getter
	@NoArgsConstructor
	@ConfigurationProperties("path")
	public static class Path {
		private String byDefault;
	}

}