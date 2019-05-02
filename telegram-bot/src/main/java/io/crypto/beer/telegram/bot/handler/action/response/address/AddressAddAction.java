package io.crypto.beer.telegram.bot.handler.action.response.address;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.constants.KeyboardConstants;
import io.crypto.beer.telegram.bot.handler.action.response.BaseResponseAction;
import io.crypto.beer.telegram.bot.handler.keyboard.response.address.AddressAddKeyboard;
import io.crypto.beer.telegram.bot.model.Address;
import io.crypto.beer.telegram.bot.model.Profile;
import io.crypto.beer.telegram.bot.service.AddressService;
import io.crypto.beer.telegram.bot.session.Session;

@Service(value = AddressAddAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class AddressAddAction extends BaseResponseAction {

	public static final String BEAN_NAME = "addressAddAction";
	private final AddressService service;
	private final Bot bot;

	public AddressAddAction(ApplicationContext ctx, Bot bot, AddressService service) {
		super(ctx, bot);
		this.bot = bot;
		this.service = service;
	}

	@Override
	protected void makeAction(Update update) {
		Session session = bot.getSession(update);
		Profile profile = session.getProfile();
		session.nextKeyboard(KeyboardConstants.KEYBOARD_ADD_ADDRESS);
		if (profile.getAddresses().size() < 5) {
			Address address = Address.builder().userId(profile.getId()).build();
			address = service.add(address);
			session.getProfile().setAddress(address);
			session.getProfile().getAddresses().add(address);
		}
	}

	@Override
	protected String generateSendMessage(Update update) {
		Address address = bot.getSession(update).getProfile().getAddress();
		return String.format("*Address fill pattern:*\n  Country: _%s_\n  City: _%s_\n  Street: _%s_",
				address.getCountry(), address.getCity(), address.getStreet());
	}

	@Override
	protected String getKeyboard(Update update) {
		return AddressAddKeyboard.BEAN_NAME;
	}

}
