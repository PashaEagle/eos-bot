package io.crypto.beer.telegram.bot.handler.command.callBack;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import io.crypto.beer.telegram.bot.session.Session;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BuyBeerItemSelectionCommand implements ICommandCallBack {

    private final ApplicationContext ctx;
    private final Bot bot;

    @Override
    public void makeAction(Update update) {
        Session session = bot.getSession(update);
        String data = update.getCallbackQuery().getData();
        String message = data.substring(data.lastIndexOf("_") + 1, data.length());

        List<Item> items = session.getShopData().getItems();
        Item item = items.stream().filter(i -> i.getName().equals(message)).findFirst().get();
        if (item != null) {
            session.getOrderState().getItems().add(item);
        }
    }

    @Override
    public boolean check(Update update) {
        if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();
            String command = data.substring(0, data.lastIndexOf("_") + 1);
            return command.equals(CallBackConstants.BTN_TXT_ITEM_SELECT_CALL_BACK);
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
        String items = session.getOrderState().getItems().isEmpty() ? "- Items: _No items are found_"
                : session.getOrderState().getItems().stream().map(Item::toString)
                        .collect(Collectors.joining("\n     "));

        IKeyboardInline keyboard = (IKeyboardInline) ctx.getBean(CartBuyBeerKeyboard.BEAN_NAME);
        return new EditMessageText().setChatId(update.getCallbackQuery().getMessage().getChatId()).enableMarkdown(true)
                .setMessageId(update.getCallbackQuery().getMessage().getMessageId())
                .setText(String.format("*Selected filters:*\n   %s\n   %s\n   - Items:\n     _%s_", variationToString,
                        breweryToString, items))
                .setReplyMarkup(keyboard.generateMenu(update));
    }

}
