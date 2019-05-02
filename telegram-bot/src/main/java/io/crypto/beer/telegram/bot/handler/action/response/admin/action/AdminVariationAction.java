package io.crypto.beer.telegram.bot.handler.action.response.admin.action;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.constants.KeyboardConstants;
import io.crypto.beer.telegram.bot.handler.action.response.BaseResponseAction;
import io.crypto.beer.telegram.bot.handler.action.response.admin.keyboard.AdminVariationKeyboard;

@Service(value = AdminVariationAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class AdminVariationAction extends BaseResponseAction {

	public static final String BEAN_NAME = "AdminVariationAction";

	private final Bot bot;

	public AdminVariationAction(ApplicationContext ctx, Bot bot) {
		super(ctx, bot);
		this.bot = bot;
	}

	@Override
	protected void makeAction(Update update) {
		bot.getSession(update).nextKeyboard(KeyboardConstants.KEYBOARD_ADMIN_VARIATION);

	}

	@Override
	protected String generateSendMessage(Update update) {

		return "You are on AdminVariation page";
	}

	@Override
	protected String getKeyboard(Update update) {
	
		return AdminVariationKeyboard.BEAN_NAME;
	}

}
