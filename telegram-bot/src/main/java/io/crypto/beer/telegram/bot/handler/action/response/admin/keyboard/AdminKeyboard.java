package io.crypto.beer.telegram.bot.handler.action.response.admin.keyboard;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.constants.ActionConstants;
import io.crypto.beer.telegram.bot.handler.keyboard.response.BaseReplyKeyboard;

@Service(value = AdminKeyboard.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class AdminKeyboard extends BaseReplyKeyboard {

	public static final String BEAN_NAME = "adminKeyboard";

	@Override
	public void fillMenuRows(Update update) {
		fillFirstRow(ActionConstants.BTN_TXT_ADMIN_VARIATION, ActionConstants.BTN_TXT_ADMIN_BREWERY, ActionConstants.BTN_TXT_ADMIN_ITEM);
		fillSecondRow(ActionConstants.BTN_TXT_MAIN_MENU, ActionConstants.BTN_TXT_BACK);
	}

}