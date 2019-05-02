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

@Service(value = CartRemoveKeyboard.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class CartRemoveKeyboard extends BaseInlineKeyboard {

    public static final String BEAN_NAME = "cartRemoveKeyboard";

    private final Bot bot;

    public CartRemoveKeyboard(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void fillMenuRows(Update update) {
        List<Item> items = bot.getSession(update).getOrderState().getItems();
        Map<String, String> firstRow = items.stream().collect(Collectors.toMap(Item::getName,
                i -> String.format("%s%s", CallBackConstants.BTN_TXT_REMOVE_ITEMS_CALL_BACK, i.getName())));

        fillFirstRow(firstRow);
        
        Map<String, String> secondRow = new LinkedHashMap<>();
        secondRow.put(CallBackConstants.BTN_TXT_BACK_TO_CART, CallBackConstants.BTN_TXT_BACK_TO_CART_CALL_BACK);
        
        fillSixthRow(secondRow);
    }
}
