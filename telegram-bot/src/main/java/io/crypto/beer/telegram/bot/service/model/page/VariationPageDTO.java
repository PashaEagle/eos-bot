package io.crypto.beer.telegram.bot.service.model.page;

import java.util.List;

import io.crypto.beer.telegram.bot.service.model.VariationDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VariationPageDTO {
	
	private List<VariationDTO> content;

}
