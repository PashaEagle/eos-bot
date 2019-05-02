package io.crypto.beer.telegram.bot.handler.action.callBack.cart.buyBeer;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.handler.action.callBack.BaseCallBackAction;
import io.crypto.beer.telegram.bot.handler.keyboard.callBack.cart.CartBuyBeerKeyboard;
import io.crypto.beer.telegram.bot.service.VariationService;

@Service(value = CartBuyBeerAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class CartBuyBeerAction extends BaseCallBackAction {

    public static final String BEAN_NAME = "сartBuyBeerAction";

    
    public CartBuyBeerAction(ApplicationContext ctx, Bot bot, VariationService variationService) {
        super(ctx, bot);
    }

    @Override
    protected void makeAction(Update update) {
    }

    @Override
    protected String generateSendMessage(Update update) {
        return "*Select the varaition and brewery*";
    }

    @Override
    protected String getKeyboard(Update update) {
        return CartBuyBeerKeyboard.BEAN_NAME;
    }

}