package io.crypto.beer.telegram.bot.handler.keyboard.response.address;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.constants.ActionConstants;
import io.crypto.beer.telegram.bot.handler.keyboard.response.BaseReplyKeyboard;

@Service(value = AddressBodyKeyboard.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class AddressBodyKeyboard extends BaseReplyKeyboard {
	
	public static final String BEAN_NAME = "addressBodyKeyboard";

	@Override
	public void fillMenuRows(Update update) {
		fillFirstRow(ActionConstants.BTN_TXT_REMOVE_ADDRESS, ActionConstants.BTN_TXT_EDIT_ADDRESS, ActionConstants.BTN_TXT_SELECT);
		fillSecondRow(ActionConstants.BTN_TXT_ORDER, ActionConstants.BTN_TXT_BACK);
	}

}
