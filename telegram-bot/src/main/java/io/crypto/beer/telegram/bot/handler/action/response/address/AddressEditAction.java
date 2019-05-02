package io.crypto.beer.telegram.bot.handler.action.response.address;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.constants.KeyboardConstants;
import io.crypto.beer.telegram.bot.handler.action.response.BaseResponseAction;
import io.crypto.beer.telegram.bot.handler.keyboard.response.address.AddressEditKeyboard;

@Service(value = AddressEditAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class AddressEditAction extends BaseResponseAction {

    public static final String BEAN_NAME = "addressEditAction";

    private final Bot bot;

    public AddressEditAction(ApplicationContext ctx, Bot bot) {
        super(ctx, bot);
        this.bot = bot;
    }

    @Override
    protected void makeAction(Update update) {
        bot.getSession(update).nextKeyboard(KeyboardConstants.KEYBOARD_EDIT_ADDRESS);
    }

    @Override
    protected String generateSendMessage(Update update) {
        return "Select one of options";
    }

    @Override
    protected String getKeyboard(Update update) {
        return AddressEditKeyboard.BEAN_NAME;
    }

}
