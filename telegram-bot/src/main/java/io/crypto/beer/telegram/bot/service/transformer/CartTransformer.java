package io.crypto.beer.telegram.bot.service.transformer;

import org.springframework.stereotype.Component;

import io.crypto.beer.telegram.bot.model.Order;
import io.crypto.beer.telegram.bot.service.model.CartDTO;

@Component
public class CartTransformer {

	public Order transform(CartDTO cartDTO) {
		return Order.builder()
				.id(cartDTO.getId())
				.address(cartDTO.getAddress())
				.userId(cartDTO.getUserId())
				.status(cartDTO.getStatus())
				.totalPrice(cartDTO.getTotalPrice())
				.phoneNumber(cartDTO.getPhoneNumber())
				.build();
	}

	public CartDTO transform(Order cart) {
		return CartDTO.builder()
				.id(cart.getId())
				.address(cart.getAddress())
				.userId(cart.getUserId())
				.status(cart.getStatus())
				.totalPrice(cart.getTotalPrice())
				.phoneNumber(cart.getPhoneNumber())
				.build();
	}

}
