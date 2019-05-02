package io.crypto.beer.telegram.bot.handler.action.callBack.cart.removeItem;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.handler.action.callBack.BaseCallBackAction;
import io.crypto.beer.telegram.bot.handler.keyboard.callBack.cart.CartRemoveKeyboard;

@Service(value = CartRemoveAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class CartRemoveAction extends BaseCallBackAction {

    public static final String BEAN_NAME = "cartRemoveAction";

    public CartRemoveAction(ApplicationContext ctx, Bot bot) {
        super(ctx, bot);
    }

    @Override
    protected void makeAction(Update update) {

    }

    @Override
    protected String generateSendMessage(Update update) {
        return "*Select the items you want to delete*";
    }

    @Override
    protected String getKeyboard(Update update) {
        return CartRemoveKeyboard.BEAN_NAME;
    }

}