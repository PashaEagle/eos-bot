package io.crypto.beer.telegram.bot.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {

	private Integer id;

	private String address;

	private Integer userId;

	private String phoneNumber;

	private String status;

	private BigDecimal totalPrice;

}
