package io.crypto.beer.telegram.bot.handler.action.response.address;

import java.util.Optional;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.handler.action.response.BaseResponseAction;
import io.crypto.beer.telegram.bot.handler.keyboard.response.address.AddressKeyboard;
import io.crypto.beer.telegram.bot.model.Address;
import io.crypto.beer.telegram.bot.session.Session;

@Service(value = AddressSelectAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class AddressSelectAction extends BaseResponseAction {

	public static final String BEAN_NAME = "addressSelectAction";

	private final Bot bot;

	public AddressSelectAction(ApplicationContext ctx, Bot bot) {
		super(ctx, bot);
		this.bot = bot;
	}

	@Override
	protected void makeAction(Update update) {
		Session session = bot.getSession(update);
		Optional.ofNullable(session.getEditState().getNumberOfAddress()).ifPresent(s -> {
			Address address = session.getProfile().getAddresses().get(s - 1);
			session.getProfile().setAddress(address);
			session.backKeyboard();
		});
	}

	@Override
	protected String generateSendMessage(Update update) {
		return "You have successfully changed the address";
	}

	@Override
	protected String getKeyboard(Update update) {
		return AddressKeyboard.BEAN_NAME;
	}

}
