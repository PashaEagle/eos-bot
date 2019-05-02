package io.crypto.beer.telegram.bot.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderLine {

	private Integer id;
	
	private Integer itemId;
	
	private Integer cartId;
	
	private Integer count;
	
	private BigDecimal totalPrice;
	
}
