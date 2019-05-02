package io.crypto.beer.telegram.bot.handler.action.response.admin.action;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.constants.KeyboardConstants;
import io.crypto.beer.telegram.bot.handler.action.response.BaseResponseAction;
import io.crypto.beer.telegram.bot.handler.action.response.admin.keyboard.AdminItemKeyboard;

@Service(value = AdminItemAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class AdminItemAction extends BaseResponseAction {

	public static final String BEAN_NAME = "AdminItemAction";

	private final Bot bot;

	public AdminItemAction(ApplicationContext ctx, Bot bot) {
		super(ctx, bot);
		this.bot = bot;
	}

	@Override
	protected void makeAction(Update update) {
		bot.getSession(update).nextKeyboard(KeyboardConstants.KEYBOARD_ADMIN_ITEM);

	}

	@Override
	protected String generateSendMessage(Update update) {

		return "You are on admin item page";
	}

	@Override
	protected String getKeyboard(Update update) {
	
		return AdminItemKeyboard.BEAN_NAME;
	}

}
