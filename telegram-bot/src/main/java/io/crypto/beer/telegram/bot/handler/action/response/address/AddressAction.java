package io.crypto.beer.telegram.bot.handler.action.response.address;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.constants.KeyboardConstants;
import io.crypto.beer.telegram.bot.handler.action.response.BaseResponseAction;
import io.crypto.beer.telegram.bot.handler.keyboard.response.address.AddressKeyboard;
import io.crypto.beer.telegram.bot.model.Address;
import io.crypto.beer.telegram.bot.session.Session;

@Service(value = AddressAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class AddressAction extends BaseResponseAction {

	public static final String BEAN_NAME = "addressAction";
	
	private final Bot bot;

	public AddressAction(ApplicationContext ctx, Bot bot) {
		super(ctx, bot);
		this.bot = bot;
	}

	@Override
	protected void makeAction(Update update) {
		Session session = bot.getSession(update);
		session.nextKeyboard(KeyboardConstants.KEYBOARD_ADDRESS);
		
		session.getEditState().cleanNumberOfAddress();
	}

	@Override
	protected String generateSendMessage(Update update) {
		Address address = bot.getSession(update).getProfile().getAddress();
		return String.format(
				"*You current address:*\n  Country: _%s_\n  City: _%s_\n  Street: _%s_\n  If you want change you current address, choose one of the options",
				address.getCountry(), address.getCity(), address.getStreet());
	}

	@Override
	protected String getKeyboard(Update update) {
		return AddressKeyboard.BEAN_NAME;
	}

}