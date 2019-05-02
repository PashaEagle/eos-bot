package io.crypto.beer.telegram.bot.handler.action.callBack.cart.payment;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.handler.action.callBack.BaseCallBackAction;

@Service(value = CartPaymentAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class CartPaymentAction extends BaseCallBackAction {

    public static final String BEAN_NAME = "cartPaymentAction";

    public CartPaymentAction(ApplicationContext ctx, Bot bot) {
        super(ctx, bot);
    }

    @Override
    protected void makeAction(Update update) {
        
    }

    @Override
    protected String generateSendMessage(Update update) {
        return null;
    }

    @Override
    protected String getKeyboard(Update update) {
        return null;
    }

}