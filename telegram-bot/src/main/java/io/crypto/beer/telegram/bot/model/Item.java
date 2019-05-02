package io.crypto.beer.telegram.bot.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {

	private Integer id;

	private Integer variationId;

	private Integer breweryId;

	private String name;

	private String description;

	private String smallImageUrl;

	private String mediumImageUrl;

	private String largeImageUrl;

	@Override
	public String toString() {
		return String.format("- Name: %s, Description: %s", name, description);
	}

}
