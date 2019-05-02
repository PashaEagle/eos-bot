package io.crypto.beer.telegram.bot.handler.action.response;

import java.util.Map;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.constants.KeyboardConstants;
import io.crypto.beer.telegram.bot.session.Session;

@Service(value = BackToMenuAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class BackToMenuAction extends BaseResponseAction {

	public static final String BEAN_NAME = "backToMenuAction";

	private final Bot bot;
	private final Map<String, String> keyboardMap;

	public BackToMenuAction(ApplicationContext ctx, Bot bot, Map<String, String> keyboardMap) {
		super(ctx, bot);
		this.bot = bot;
		this.keyboardMap = keyboardMap;
	}

	@Override
	protected void makeAction(Update update) {
		Session session = bot.getSession(update);
		session.backKeyboard();
		if (KeyboardConstants.KEYBOARD_ADDRESS.equals(session.getKeyboard())) {
			session.getEditState().cleanEditStates();
			session.getEditState().cleanNumberOfAddress();
		}

	}

	@Override
	protected String generateSendMessage(Update update) {
		return "Select one of action:";
	}

	@Override
	protected String getKeyboard(Update update) {
		return keyboardMap.get(bot.getSession(update).getKeyboard());
	}

}