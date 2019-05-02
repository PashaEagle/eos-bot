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
import io.crypto.beer.telegram.bot.model.Variation;

@Service(value = BuyBeerVariationKeyboard.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class BuyBeerVariationKeyboard extends BaseInlineKeyboard {

    public static final String BEAN_NAME = "buyBeerVariationKeyboard";

    private final Bot bot;
    
    public BuyBeerVariationKeyboard(Bot bot) {
        this.bot = bot;
    }
    
    @Override
    public void fillMenuRows(Update update) {

        List<Variation> variations = bot.getSession(update).getShopData().getVariations();
        
        Map<String, String> firstRow = variations.stream().collect(Collectors.toMap(Variation::getName,
                v -> String.format("%s%s", CallBackConstants.BTN_TXT_VARIATION_SELECT_CALL_BACK, v.getName())));
        
        fillFirstRow(firstRow);
        
        Map<String, String> secondRow = new LinkedHashMap<>();
        secondRow.put(CallBackConstants.BTN_TXT_BACK, CallBackConstants.BTN_TXT_BACK_CALL_BACK);
        
        fillSecondRow(secondRow);
    }
}