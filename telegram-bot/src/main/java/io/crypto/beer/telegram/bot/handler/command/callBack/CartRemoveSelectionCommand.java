package io.crypto.beer.telegram.bot.handler.command.callBack;

import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.constants.CallBackConstants;
import io.crypto.beer.telegram.bot.handler.command.ICommandCallBack;
import io.crypto.beer.telegram.bot.handler.keyboard.IKeyboardInline;
import io.crypto.beer.telegram.bot.handler.keyboard.callBack.RenewCartKeyboard;
import io.crypto.beer.telegram.bot.model.Address;
import io.crypto.beer.telegram.bot.model.Item;
import io.crypto.beer.telegram.bot.model.Order;
import io.crypto.beer.telegram.bot.session.Session;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CartRemoveSelectionCommand implements ICommandCallBack {

    private final ApplicationContext ctx;
    private final Bot bot;

    @Override
    public void makeAction(Update update) {
        String data = update.getCallbackQuery().getData();
        String itemName = data.substring(data.lastIndexOf("_") + 1, data.length());

        Session session = bot.getSession(update);

        Item item = session.getOrderState().getItems().stream().filter(i -> i.getName().equals(itemName)).findFirst()
                .get();

        session.getOrderState().getItems().remove(item);
    }

    @Override
    public boolean check(Update update) {
        if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();
            String command = data.substring(0, data.lastIndexOf("_") + 1);
            return command.equals(CallBackConstants.BTN_TXT_REMOVE_ITEMS_CALL_BACK);
        }
        return false;
    }

    @Override
    public EditMessageText execute(Update update) {
        Session session = bot.getSession(update);
        Order order = session.getOrderState().getOrder();
        Address address = session.getProfile().getAddress();
        String items = session.getOrderState().getItems().isEmpty() ? "- No items are found"
                : session.getOrderState().getItems().stream().map(Item::toString)
                        .collect(Collectors.joining("\n     "));

        IKeyboardInline keyboard = (IKeyboardInline) ctx.getBean(RenewCartKeyboard.BEAN_NAME);
        return new EditMessageText().setChatId(update.getCallbackQuery().getMessage().getChatId()).enableMarkdown(true)
                .setMessageId(update.getCallbackQuery().getMessage().getMessageId())
                .setText(String.format(
                        "*Info about your cart:*\n Address: _%s_\n Phone: _%s_\n Status: _%s_\n Items: \n     _%s_\n Total price: _%s_",
                        address.toString(), order.getPhoneNumber(), order.getStatus(), items, order.getTotalPrice()))
                .setReplyMarkup(keyboard.generateMenu(update));
    }

}
