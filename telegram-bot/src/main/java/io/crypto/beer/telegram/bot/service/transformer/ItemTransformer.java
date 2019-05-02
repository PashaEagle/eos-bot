package io.crypto.beer.telegram.bot.service.transformer;

import org.springframework.stereotype.Component;

import io.crypto.beer.telegram.bot.model.Item;
import io.crypto.beer.telegram.bot.service.model.ItemDTO;

@Component
public class ItemTransformer {
	
	public Item transform(ItemDTO itemDTO) {
		return Item.builder()
				.id(itemDTO.getId())
				.variationId(itemDTO.getVariationId())
				.breweryId(itemDTO.getBreweryId())
				.name(itemDTO.getName())
				.description(itemDTO.getDescription())
				.smallImageUrl(itemDTO.getSmallImageUrl())
				.mediumImageUrl(itemDTO.getMediumImageUrl())
				.largeImageUrl(itemDTO.getLargeImageUrl())
				.build();
	}
	
	public ItemDTO transform(Item item) {
		return ItemDTO.builder()
				.id(item.getId())
				.variationId(item.getVariationId())
				.breweryId(item.getBreweryId())
				.name(item.getName())
				.description(item.getDescription())
				.smallImageUrl(item.getSmallImageUrl())
				.mediumImageUrl(item.getMediumImageUrl())
				.largeImageUrl(item.getLargeImageUrl())
				.build();
	}
	
}
