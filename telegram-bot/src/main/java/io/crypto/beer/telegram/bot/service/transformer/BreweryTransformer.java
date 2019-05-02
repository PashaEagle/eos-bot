package io.crypto.beer.telegram.bot.service.transformer;

import org.springframework.stereotype.Component;

import io.crypto.beer.telegram.bot.model.Brewery;
import io.crypto.beer.telegram.bot.service.model.BreweryDTO;

@Component
public class BreweryTransformer {
	
	public Brewery transform(BreweryDTO breweryDTO) {
		return Brewery.builder()
				.id(breweryDTO.getId())
				.name(breweryDTO.getName())
				.build();
	}
	
	public BreweryDTO transform(Brewery brewery) {
		return BreweryDTO.builder()
				.id(brewery.getId())
				.name(brewery.getName())
				.build();
	}
	
}