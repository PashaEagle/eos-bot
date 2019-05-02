package io.crypto.beer.telegram.bot.handler.keyboard.callBack.cart.buyBeer;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.constants.CallBackConstants;
import io.crypto.beer.telegram.bot.handler.keyboard.callBack.BaseInlineKeyboard;
import io.crypto.beer.telegram.bot.model.Brewery;

@Service(value = BuyBeerBreweryKeyboard.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class BuyBeerBreweryKeyboard extends BaseInlineKeyboard {

    public static final String BEAN_NAME = "buyBeerBreweryKeyboard";

    private final Bot bot;
    
    public BuyBeerBreweryKeyboard(Bot bot) {
        this.bot = bot;
    }
    
    @Override
    public void fillMenuRows(Update update) {

        List<Brewery> breweries = bot.getSession(update).getShopData().getBreweries();
        
        Map<String, String> firstRow = breweries.stream().collect(Collectors.toMap(Brewery::getName,
                b -> String.format("%s%s", CallBackConstants.BTN_TXT_BREWERY_SELECT_CALL_BACK, b.getName())));
        
        fillFirstRow(firstRow);
        
        Map<String, String> secondRow = new LinkedHashMap<>();
        secondRow.put(CallBackConstants.BTN_TXT_BACK, CallBackConstants.BTN_TXT_BACK_CALL_BACK);
        
        fillSecondRow(secondRow);
    }
}
