package io.crypto.beer.telegram.bot.handler.action.callBack.cart;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.handler.action.callBack.BaseCallBackAction;
import io.crypto.beer.telegram.bot.handler.keyboard.callBack.cart.CartBuyBeerKeyboard;
import io.crypto.beer.telegram.bot.model.Brewery;
import io.crypto.beer.telegram.bot.model.Item;
import io.crypto.beer.telegram.bot.model.Variation;
import io.crypto.beer.telegram.bot.service.ItemService;
import io.crypto.beer.telegram.bot.session.Session;

@Service(value = BackAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class BackAction extends BaseCallBackAction {

    public static final String BEAN_NAME = "backAction";

    private final Bot bot;
    private final ItemService itemService;

    public BackAction(ApplicationContext ctx, Bot bot, ItemService itemService) {
        super(ctx, bot);
        this.bot = bot;
        this.itemService = itemService;
    }

    @Override
    protected void makeAction(Update update) {
        Session session = bot.getSession(update);

        Variation variation = session.getShopData().getVariation();
        Brewery brewery = session.getShopData().getBrewery();

        if (variation != null && brewery != null) {
            List<Item> items = itemService.getBySearch(null, variation.getId(), brewery.getId(), null, null);
            session.getShopData().setItems(items);
            if (items.isEmpty()) {
                session.getShopData().setItems(Collections.emptyList());
            }
        }
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