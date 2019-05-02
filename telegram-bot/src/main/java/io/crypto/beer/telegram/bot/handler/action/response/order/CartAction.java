package io.crypto.beer.telegram.bot.handler.action.response.order;

import java.math.BigDecimal;
import java.util.stream.Collectors;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.handler.action.response.BaseResponseAction;
import io.crypto.beer.telegram.bot.handler.keyboard.callBack.cart.CartKeyboard;
import io.crypto.beer.telegram.bot.model.Address;
import io.crypto.beer.telegram.bot.model.Item;
import io.crypto.beer.telegram.bot.model.Order;
import io.crypto.beer.telegram.bot.model.Profile;
import io.crypto.beer.telegram.bot.model.enums.CartStatus;
import io.crypto.beer.telegram.bot.session.Session;

@Service(value = CartAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class CartAction extends BaseResponseAction {

	public static final String BEAN_NAME = "cartAction";

	private final Bot bot;

	public CartAction(ApplicationContext ctx, Bot bot) {
		super(ctx, bot);
		this.bot = bot;
	}

	@Override
	protected void makeAction(Update update) {
		Session session = bot.getSession(update);

		if (session.getOrderState().getOrder() == null) {
			Profile profile = session.getProfile();
			Order order = Order.builder().address(profile.getAddress().toString())
					.userId(profile.getId()).status(CartStatus.NEW.toString()).phoneNumber(profile.getPhone())
					.totalPrice(new BigDecimal(25)).build();
			session.getOrderState().setOrder(order);
		}
	}

	@Override
	protected String generateSendMessage(Update update) {
		Session session = bot.getSession(update);
		Order order = session.getOrderState().getOrder();
		Address address = session.getProfile().getAddress();
		String items = session.getOrderState().getItems().isEmpty() ? "- No items are found"
				: session.getOrderState().getItems().stream().map(Item::toString)
						.collect(Collectors.joining("\n     "));
		return String.format(
				"*Info about your cart:*\n Address: _%s_\n Phone: _%s_\n Status: _%s_\n Items: \n     _%s_\n Total price: _%s_",
				address.toString(), order.getPhoneNumber(), order.getStatus(), items, order.getTotalPrice());
	}

	@Override
	protected String getKeyboard(Update update) {
		return CartKeyboard.BEAN_NAME;
	}

}