package io.crypto.beer.telegram.bot.handler.keyboard.response.address;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.constants.ActionConstants;
import io.crypto.beer.telegram.bot.handler.keyboard.response.BaseReplyKeyboard;

@Service(value = AddressEditKeyboard.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class AddressEditKeyboard extends BaseReplyKeyboard {
	
	public static final String BEAN_NAME = "addressEditKeyboard";

	@Override
	public void fillMenuRows(Update update) {
		fillFirstRow(ActionConstants.BTN_TXT_COUNTRY, ActionConstants.BTN_TXT_CITY, ActionConstants.BTN_TXT_STREET);
		fillSecondRow(ActionConstants.BTN_TXT_ORDER, ActionConstants.BTN_TXT_BACK);
	}
	
}
