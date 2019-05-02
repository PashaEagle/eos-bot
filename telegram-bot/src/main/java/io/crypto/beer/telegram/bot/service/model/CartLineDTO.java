package io.crypto.beer.telegram.bot.service.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartLineDTO {

	private Integer id;
	
	private Integer itemId;
	
	private Integer cartId;
	
	private Integer count;
	
	private BigDecimal totalPrice;
	
}
