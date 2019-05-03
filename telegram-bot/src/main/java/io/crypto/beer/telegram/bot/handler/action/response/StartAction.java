package io.crypto.beer.telegram.bot.handler.action.response;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.config.properties.BotProperties;
import io.crypto.beer.telegram.bot.constants.KeyboardConstants;
import io.crypto.beer.telegram.bot.handler.keyboard.response.MainKeyboard;
import io.crypto.beer.telegram.bot.model.Address;
import io.crypto.beer.telegram.bot.model.Profile;
import io.crypto.beer.telegram.bot.service.AddressService;
import io.crypto.beer.telegram.bot.service.UserService;
import io.crypto.beer.telegram.bot.service.transformer.UserTransformer;
import io.crypto.beer.telegram.bot.session.Session;

@Service(value = StartAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class StartAction extends BaseResponseAction {

	public static final String BEAN_NAME = "startAction";

	@Autowired
	private BotProperties botProperties;
	private final AddressService addressService;
	private final UserService service;
	private final UserTransformer transformer;
	private final Bot bot;
	

	public StartAction(ApplicationContext ctx, Bot bot, UserService service, UserTransformer transformer,
			AddressService addressService) {
		super(ctx, bot);
		this.bot = bot;
		this.service = service;
		this.transformer = transformer;
		this.addressService = addressService;
	}

	@Override
	protected void makeAction(Update update) {
		Session session = bot.getSession(update);
		session.nextKeyboard(KeyboardConstants.KEYBOARD_START);

		Profile profile = service.getByTelegaId(update.getMessage().getFrom().getId());
		if (profile == null) {
			User user = update.getMessage().getFrom();
			profile = service.add(transformer.transform(session.getProfile(), user));
		}
		session.setProfile(profile);

		List<Address> addresses = addressService.getByUserId(profile.getId());
		session.getProfile().setAddresses(addresses);
		if (addresses.isEmpty()) {
			session.getProfile().setAddress(Address.builder().build());
		} else {
			session.getProfile().setAddress(addresses.get(0));
		}
		
		botProperties.setChatId(update.getMessage().getChatId().toString());
	}

	@Override
	protected String generateSendMessage(Update update) {
		return String.format("Hi, %s", update.getMessage().getFrom().getFirstName());
	}

	@Override
	protected String getKeyboard(Update update) {
		return MainKeyboard.BEAN_NAME;
	}

}