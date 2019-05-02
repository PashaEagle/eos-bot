package io.crypto.beer.telegram.bot.handler.action.response.admin.action;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.constants.KeyboardConstants;
import io.crypto.beer.telegram.bot.handler.action.response.BaseResponseAction;
import io.crypto.beer.telegram.bot.handler.action.response.admin.keyboard.AdminKeyboard;

@Service(value = AdminMenuAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class AdminMenuAction extends BaseResponseAction {

	public static final String BEAN_NAME = "AdminMenuAction";

	private final Bot bot;

	public AdminMenuAction(ApplicationContext ctx, Bot bot) {
		super(ctx, bot);
		this.bot = bot;
	}

	@Override
	protected void makeAction(Update update) {
		bot.getSession(update).nextKeyboard(KeyboardConstants.KEYBOARD_ADMIN_MENU);
	}

	@Override
	protected String generateSendMessage(Update update) {
		return "Please, select one of options";
	}

	@Override
	protected String getKeyboard(Update update) {
		return AdminKeyboard.BEAN_NAME;
	}
}
