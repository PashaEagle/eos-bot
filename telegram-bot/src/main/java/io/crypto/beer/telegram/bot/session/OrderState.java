package io.crypto.beer.telegram.bot.session;

import java.util.List;

import io.crypto.beer.telegram.bot.model.Item;
import io.crypto.beer.telegram.bot.model.Order;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderState {

	private Order order;
	
	private Item item;
	
	private List<Item> items;
	
}
