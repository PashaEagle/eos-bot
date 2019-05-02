package io.crypto.beer.telegram.bot.handler.action.callBack.cart.buyBeer;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.handler.action.callBack.BaseCallBackAction;
import io.crypto.beer.telegram.bot.handler.keyboard.callBack.cart.buyBeer.BuyBeerVariationKeyboard;
import io.crypto.beer.telegram.bot.model.Variation;
import io.crypto.beer.telegram.bot.service.VariationService;
import io.crypto.beer.telegram.bot.session.Session;

@Service(value = BuyBeerVariationAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class BuyBeerVariationAction extends BaseCallBackAction {

    public static final String BEAN_NAME = "buyBeerVariationAction";

    private final Bot bot;
    private final VariationService variationService;
    
    public BuyBeerVariationAction(ApplicationContext ctx, Bot bot, VariationService variationService) {
        super(ctx, bot);
        this.bot = bot;
        this.variationService = variationService;
    }

    @Override
    protected void makeAction(Update update) {
        Session session = bot.getSession(update);
        
        List<Variation> variations = variationService.getByPagination(0, null);
        session.getShopData().setVariations(variations);
        if (variations.isEmpty()) {
            session.getShopData().setVariations(Collections.emptyList());
        }
    }

    @Override
    protected String generateSendMessage(Update update) {
        return "*Select one of varaition*";
    }

    @Override
    protected String getKeyboard(Update update) {
        return BuyBeerVariationKeyboard.BEAN_NAME;
    }

}