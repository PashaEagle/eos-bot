package io.crypto.beer.telegram.bot.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Brewery {
	
	private Integer id;
	
	private String name;
	
}
