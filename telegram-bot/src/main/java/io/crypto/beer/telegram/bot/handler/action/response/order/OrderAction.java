package io.crypto.beer.telegram.bot.handler.action.response.order;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.constants.KeyboardConstants;
import io.crypto.beer.telegram.bot.handler.action.response.BaseResponseAction;
import io.crypto.beer.telegram.bot.handler.keyboard.response.OrderKeyboard;

@Service(value = OrderAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class OrderAction extends BaseResponseAction {

    public static final String BEAN_NAME = "orderAction";

    private final Bot bot;

    public OrderAction(ApplicationContext ctx, Bot bot) {
        super(ctx, bot);
        this.bot = bot;
    }

    @Override
    protected void makeAction(Update update) {
        bot.getSession(update).nextKeyboard(KeyboardConstants.KEYBOARD_ORDER);
    }

    @Override
    protected String generateSendMessage(Update update) {
        return "You are on order page";
    }

    @Override
    protected String getKeyboard(Update update) {
        return OrderKeyboard.BEAN_NAME;
    }

}