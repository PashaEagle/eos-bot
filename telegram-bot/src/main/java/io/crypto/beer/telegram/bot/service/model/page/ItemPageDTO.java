package io.crypto.beer.telegram.bot.service.model.page;

import java.util.List;

import io.crypto.beer.telegram.bot.service.model.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemPageDTO {

	private List<ItemDTO> content;
	
}
