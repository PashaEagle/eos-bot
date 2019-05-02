package io.crypto.beer.telegram.bot.handler.command.callBack;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.constants.CallBackConstants;
import io.crypto.beer.telegram.bot.handler.command.ICommandCallBack;
import io.crypto.beer.telegram.bot.handler.keyboard.IKeyboardInline;
import io.crypto.beer.telegram.bot.handler.keyboard.callBack.cart.CartBuyBeerKeyboard;
import io.crypto.beer.telegram.bot.model.Brewery;
import io.crypto.beer.telegram.bot.model.Item;
import io.crypto.beer.telegram.bot.model.Variation;
import io.crypto.beer.telegram.bot.service.ItemService;
import io.crypto.beer.telegram.bot.session.Session;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BuyBeerBrewerySelectionCommand implements ICommandCallBack {

    private final ApplicationContext ctx;
    private final ItemService itemService;
    private final Bot bot;

    @Override
    public void makeAction(Update update) {
        Session session = bot.getSession(update);
        String data = update.getCallbackQuery().getData();
        String message = data.substring(data.lastIndexOf("_") + 1, data.length());

        List<Brewery> breweries = session.getShopData().getBreweries();
        breweries.stream().filter(v -> v.getName().equals(message)).findFirst()
                .ifPresent(session.getShopData()::setBrewery);

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
    public boolean check(Update update) {
        if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();
            String command = data.substring(0, data.lastIndexOf("_") + 1);
            return command.equals(CallBackConstants.BTN_TXT_BREWERY_SELECT_CALL_BACK);
        }
        return false;
    }

    @Override
    public EditMessageText execute(Update update) {
        Session session = bot.getSession(update);
        Variation variation = session.getShopData().getVariation();
        Brewery brewery = session.getShopData().getBrewery();
        String variationToString = Optional.ofNullable(variation).isPresent()
                ? String.format("- Variation: _%s_", variation.getName())
                : "- Variation: _Not chosen variation_";
        String breweryToString = Optional.ofNullable(brewery).isPresent()
                ? String.format("- Brewery: _%s_", brewery.getName())
                : "- Brewery: _Not chosen brewery_";

        IKeyboardInline keyboard = (IKeyboardInline) ctx.getBean(CartBuyBeerKeyboard.BEAN_NAME);
        return new EditMessageText().setChatId(update.getCallbackQuery().getMessage().getChatId()).enableMarkdown(true)
                .setMessageId(update.getCallbackQuery().getMessage().getMessageId())
                .setText(String.format("*Selected filters:*\n   %s\n   %s", variationToString, breweryToString))
                .setReplyMarkup(keyboard.generateMenu(update));
    }

}
