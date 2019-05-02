package io.crypto.beer.telegram.bot.handler.keyboard.callBack.cart;

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
import io.crypto.beer.telegram.bot.model.Item;
import io.crypto.beer.telegram.bot.service.ItemService;
import io.crypto.beer.telegram.bot.session.Session;
import io.crypto.beer.telegram.bot.session.ShopData;

@Service(value = CartBuyBeerKeyboard.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class CartBuyBeerKeyboard extends BaseInlineKeyboard {

    public static final String BEAN_NAME = "cartBuyBeerKeyboard";
    
    private final Bot bot;
    
    public CartBuyBeerKeyboard(Bot bot, ItemService itemService) {
        this.bot = bot;
    }

    @Override
    public void fillMenuRows(Update update) {
        Session session = bot.getSession(update);
        ShopData shopData = session.getShopData();
        
        Map<String, String> firstRow = new LinkedHashMap<>();
        firstRow.put(CallBackConstants.BTN_TXT_VARIATION, CallBackConstants.BTN_TXT_VARIATION_CALL_BACK);
        firstRow.put(CallBackConstants.BTN_TXT_BREWERY, CallBackConstants.BTN_TXT_BREWERY_CALL_BACK);
        firstRow.put(CallBackConstants.BTN_TXT_CLEAR_FILTERS, CallBackConstants.BTN_TXT_CLEAR_FILTERS_CALL_BACK);
        
        fillFirstRow(firstRow);
        
        if(shopData.getVariation() != null && shopData.getBrewery() != null && !shopData.getItems().isEmpty()) {
            List<Item> items = bot.getSession(update).getShopData().getItems();
            Map<String, String> secondRow = items.stream().collect(Collectors.toMap(Item::getName,
                    i -> String.format("%s%s", CallBackConstants.BTN_TXT_ITEM_SELECT_CALL_BACK, i.getName())));
            fillSecondRow(secondRow);
        }
        
        Map<String, String> thirdRow = new LinkedHashMap<>();
        thirdRow.put(CallBackConstants.BTN_TXT_BACK_TO_CART, CallBackConstants.BTN_TXT_BACK_TO_CART_CALL_BACK);
        
        fillThirdRow(thirdRow);
    }
}
