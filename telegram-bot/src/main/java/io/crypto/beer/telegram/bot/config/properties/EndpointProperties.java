package io.crypto.beer.telegram.bot.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import io.crypto.beer.telegram.bot.service.properties.AddressProperties;
import io.crypto.beer.telegram.bot.service.properties.BreweryProperties;
import io.crypto.beer.telegram.bot.service.properties.CartLineProperties;
import io.crypto.beer.telegram.bot.service.properties.CartProperties;
import io.crypto.beer.telegram.bot.service.properties.ItemProperties;
import io.crypto.beer.telegram.bot.service.properties.UserProperties;
import io.crypto.beer.telegram.bot.service.properties.VariationProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Component
@RequiredArgsConstructor
@ConfigurationProperties("endpoint")
public class EndpointProperties {

	private final ApiProperties api;
	private final UserProperties user;
	private final AddressProperties address;
	private final VariationProperties variation;
	private final BreweryProperties brewery;
	private final ItemProperties item;
	private final CartProperties cart;
	private final CartLineProperties cartLine;
	
}
