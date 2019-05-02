package io.crypto.beer.telegram.bot.service.model.page;

import java.util.List;

import io.crypto.beer.telegram.bot.service.model.CartLineDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartLinePageDTO {

	private List<CartLineDTO> content;
	
}
