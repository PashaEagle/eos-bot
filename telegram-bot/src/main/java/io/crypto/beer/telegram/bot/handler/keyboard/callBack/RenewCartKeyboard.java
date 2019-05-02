package io.crypto.beer.telegram.bot.handler.keyboard.callBack;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.constants.CallBackConstants;
import io.crypto.beer.telegram.bot.model.Item;
import io.crypto.beer.telegram.bot.session.Session;

@Service(value = RenewCartKeyboard.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class RenewCartKeyboard extends BaseInlineKeyboard {

    public static final String BEAN_NAME = "renewCartKeyboard";

    private final Bot bot;

    public RenewCartKeyboard(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void fillMenuRows(Update update) {
        Session session = bot.getSession(update);
        List<Item> items = session.getOrderState().getItems();

        Map<String, String> firstRow = new LinkedHashMap<>();
        firstRow.put(CallBackConstants.BTN_TXT_BUY_BEER, CallBackConstants.BTN_TXT_BUY_BEER_CALL_BACK);
        
        fillFirstRow(firstRow);
        
        Map<String, String> secondRow = new LinkedHashMap<>();
        secondRow.put(CallBackConstants.BTN_TXT_PAYMENT, CallBackConstants.BTN_TXT_PAYMENT_CALL_BACK);
        secondRow.put(CallBackConstants.BTN_TXT_ADDRESS, CallBackConstants.BTN_TXT_ADDRESS_CALL_BACK);

        fillSecondRow(secondRow);

        if (!items.isEmpty()) {
            Map<String, String> thirdRow = new LinkedHashMap<>();
            thirdRow.put(CallBackConstants.BTN_TXT_REMOVE_ITEMS, CallBackConstants.BTN_TXT_REMOVE_CALL_BACK);

            fillThirdRow(thirdRow);

            if (session.getProfile().getAddress() != null) {
                Map<String, String> fourthRow = new LinkedHashMap<>();
                fourthRow.put(CallBackConstants.BTN_TXT_CHECKOUT, CallBackConstants.BTN_TXT_CHECKOUT_CALL_BACK);

                fillFourthRow(fourthRow);
            }
        }
    }

}
