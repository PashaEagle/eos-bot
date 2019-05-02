package io.crypto.beer.telegram.bot.session;

import java.util.Collections;
import java.util.List;

import io.crypto.beer.telegram.bot.model.Brewery;
import io.crypto.beer.telegram.bot.model.Item;
import io.crypto.beer.telegram.bot.model.Variation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShopData {

	private List<Variation> variations;
	private Variation variation;

	private List<Brewery> breweries;
	private Brewery brewery;

	private List<Item> items;
	private Item item;

	public void cleanVariationState() {
		this.variation = null;
	}

	public void cleanBreweryState() {
		cleanVariationState();
		this.brewery = null;
	}

	public void cleanItemState() {
		cleanBreweryState();
		this.items = Collections.emptyList();
		this.item = null;
	}
	
}
