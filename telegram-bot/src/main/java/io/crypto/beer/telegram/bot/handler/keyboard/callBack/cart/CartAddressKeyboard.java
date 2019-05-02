package io.crypto.beer.telegram.bot.handler.keyboard.callBack.cart;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.constants.CallBackConstants;
import io.crypto.beer.telegram.bot.handler.keyboard.callBack.BaseInlineKeyboard;
import io.crypto.beer.telegram.bot.model.Address;
import io.crypto.beer.telegram.bot.session.Session;

@Service(value = CartAddressKeyboard.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class CartAddressKeyboard extends BaseInlineKeyboard {

    public static final String BEAN_NAME = "cartAddressKeyboard";

    private final Bot bot;

    public CartAddressKeyboard(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void fillMenuRows(Update update) {
        Session session = bot.getSession(update);
        List<Address> addresses = session.getProfile().getAddresses();
        
        Map<Integer, Address> rows = new LinkedHashMap<>();
        IntStream.range(0, addresses.size()).forEach(idx -> rows.put(idx, addresses.get(idx)));

        generateAddressKeyboard(rows);
        
        Map<String, String> sixthRow = new LinkedHashMap<>();
        sixthRow.put(CallBackConstants.BTN_TXT_BACK_TO_CART, CallBackConstants.BTN_TXT_BACK_TO_CART_CALL_BACK);
        
        fillSixthRow(sixthRow);
    }
}
