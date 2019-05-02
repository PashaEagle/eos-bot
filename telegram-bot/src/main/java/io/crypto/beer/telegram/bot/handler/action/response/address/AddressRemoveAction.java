package io.crypto.beer.telegram.bot.handler.action.response.address;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.constants.KeyboardConstants;
import io.crypto.beer.telegram.bot.handler.action.response.BaseResponseAction;
import io.crypto.beer.telegram.bot.handler.keyboard.response.address.AddressKeyboard;
import io.crypto.beer.telegram.bot.model.Address;
import io.crypto.beer.telegram.bot.service.AddressService;
import io.crypto.beer.telegram.bot.session.Session;

@Service(value = AddressRemoveAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class AddressRemoveAction extends BaseResponseAction {

    public static final String BEAN_NAME = "addressRemveAction";
    private final AddressService service;
    private final Bot bot;

    public AddressRemoveAction(ApplicationContext ctx, Bot bot, AddressService service) {
        super(ctx, bot);
        this.bot = bot;
        this.service = service;
    }

	@Override
    protected void makeAction(Update update) {
    	Session session = bot.getSession(update);
    	session.nextKeyboard(KeyboardConstants.KEYBOARD_ADDRESS);
    	
    	Address address = session.getProfile().getAddresses().get(session.getEditState().getNumberOfAddress() - 1);
    	session.getProfile().getAddresses().remove(address);
    	service.delete(address.getId());
	}

    @Override
    protected String generateSendMessage(Update update) {
    	return "You have successfully removed the address";
    }

    @Override
    protected String getKeyboard(Update update) {
        return AddressKeyboard.BEAN_NAME;
    }
	
}
