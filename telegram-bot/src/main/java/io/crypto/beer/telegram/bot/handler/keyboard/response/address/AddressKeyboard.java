package io.crypto.beer.telegram.bot.handler.keyboard.response.address;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.constants.ActionConstants;
import io.crypto.beer.telegram.bot.handler.keyboard.response.BaseReplyKeyboard;
import io.crypto.beer.telegram.bot.model.Address;

@Service(value = AddressKeyboard.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class AddressKeyboard extends BaseReplyKeyboard {

	public static final String BEAN_NAME = "addressKeyboard";

	public final Bot bot;

	public AddressKeyboard(Bot bot) {
		super();
		this.bot = bot;
	}

	@Override
	public void fillMenuRows(Update update) {

		List<Address> addresses = bot.getSession(update).getProfile().getAddresses();

		List<String> list = new ArrayList<>();
		IntStream.range(1, addresses.size() + 1).forEach(n -> list.add(Integer.toString(n)));

		if (addresses.size() < 5) {
			list.add(ActionConstants.BTN_TXT_ADD_ADDRESS);
		}
		
		fillFirstRow(list.stream().toArray(String[]::new));
		fillSecondRow(ActionConstants.BTN_TXT_ORDER, ActionConstants.BTN_TXT_BACK);
	}
}
