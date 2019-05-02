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
public class CartDTO {
	
	private Integer id;
	
	private String address;
	
	private String status;
	
	private BigDecimal totalPrice;
	
	private Integer userId;
	
	private String phoneNumber;
	
}
