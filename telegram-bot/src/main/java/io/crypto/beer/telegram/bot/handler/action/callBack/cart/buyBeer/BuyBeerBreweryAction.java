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
import io.crypto.beer.telegram.bot.handler.keyboard.callBack.cart.buyBeer.BuyBeerBreweryKeyboard;
import io.crypto.beer.telegram.bot.model.Brewery;
import io.crypto.beer.telegram.bot.service.BreweryService;
import io.crypto.beer.telegram.bot.session.Session;

@Service(value = BuyBeerBreweryAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class BuyBeerBreweryAction extends BaseCallBackAction {

    public static final String BEAN_NAME = "buyBeerBreweryAction";
    
    private final Bot bot;
    private final BreweryService breweryService;
    
    public BuyBeerBreweryAction(ApplicationContext ctx, Bot bot, BreweryService breweryService) {
        super(ctx, bot);
        this.bot = bot;
        this.breweryService = breweryService;
    }

    @Override
    protected void makeAction(Update update) {
        Session session = bot.getSession(update);
        
        List<Brewery> breweries = breweryService.getByPagination(0, null);
        session.getShopData().setBreweries(breweries);
        if (breweries.isEmpty()) {
            session.getShopData().setBreweries(Collections.emptyList());
        }
    }

    @Override
    protected String generateSendMessage(Update update) {
        return "*Select one of brewery*";
    }

    @Override
    protected String getKeyboard(Update update) {
        return BuyBeerBreweryKeyboard.BEAN_NAME;
    }

}