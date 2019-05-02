package io.crypto.beer.telegram.bot.service.transformer;

import org.springframework.stereotype.Component;

import io.crypto.beer.telegram.bot.model.OrderLine;
import io.crypto.beer.telegram.bot.service.model.CartLineDTO;

@Component
public class CartLineTransformer {

	public OrderLine transform(CartLineDTO cartLineDTO) {
		return OrderLine.builder()
				.id(cartLineDTO.getId())
				.itemId(cartLineDTO.getItemId())
				.cartId(cartLineDTO.getCartId())
				.count(cartLineDTO.getCount())
				.totalPrice(cartLineDTO.getTotalPrice())
				.build();
	}
	
	public CartLineDTO transform(OrderLine cartLine) {
		return CartLineDTO.builder()
				.id(cartLine.getId())
				.itemId(cartLine.getItemId())
				.cartId(cartLine.getCartId())
				.count(cartLine.getCount())
				.totalPrice(cartLine.getTotalPrice())
				.build();
	}
	
}
