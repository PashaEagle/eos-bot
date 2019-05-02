package io.crypto.beer.telegram.bot.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VariationDTO {
	
	private Integer id;
	
	private String name;

}
