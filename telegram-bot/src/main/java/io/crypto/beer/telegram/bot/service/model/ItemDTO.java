package io.crypto.beer.telegram.bot.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
	
	private Integer id;
	
	private Integer variationId;
	
	private Integer breweryId;
	
	private String name;
	
	private String description;
	
	private String smallImageUrl;
	
	private String mediumImageUrl;
	
	private String largeImageUrl;
	
}
