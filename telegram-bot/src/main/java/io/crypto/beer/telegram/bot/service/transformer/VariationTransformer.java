package io.crypto.beer.telegram.bot.service.transformer;

import org.springframework.stereotype.Component;

import io.crypto.beer.telegram.bot.model.Variation;
import io.crypto.beer.telegram.bot.service.model.VariationDTO;

@Component
public class VariationTransformer {
	
	public Variation transform(VariationDTO variationDTO) {
		return Variation.builder()
				.id(variationDTO.getId())
				.name(variationDTO.getName())
				.build();
	}
	
	public VariationDTO transform(Variation variation) {
		return VariationDTO.builder()
				.id(variation.getId())
				.name(variation.getName())
				.build();
	}
	
}
