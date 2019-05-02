package io.crypto.beer.telegram.bot.service.model.page;

import java.util.List;

import io.crypto.beer.telegram.bot.service.model.BreweryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BreweryPageDTO {
	
	private List<BreweryDTO> content;

}

