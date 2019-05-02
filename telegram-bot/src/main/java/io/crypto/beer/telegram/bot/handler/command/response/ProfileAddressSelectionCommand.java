package io.crypto.beer.telegram.bot.handler.command.response;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.constants.ActionConstants;
import io.crypto.beer.telegram.bot.handler.command.ICommandResponse;
import io.crypto.beer.telegram.bot.handler.keyboard.IKeyboardReply;
import io.crypto.beer.telegram.bot.handler.keyboard.response.address.AddressBodyKeyboard;
import io.crypto.beer.telegram.bot.model.Address;
import io.crypto.beer.telegram.bot.session.Session;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProfileAddressSelectionCommand implements ICommandResponse {

    private final ApplicationContext ctx;
    private final Bot bot;

    @Override
    public void makeAction(Update update) {
        Session session = bot.getSession(update);
        session.nextKeyboard(update.getMessage().getText());
        session.getEditState().clickNumberOfAddress(Integer.valueOf(update.getMessage().getText()));
    }

    @Override
    public boolean check(Update update) {
        Session session = bot.getSession(update);
        return ActionConstants.BTN_TXT_ADDRESS.equals(session.getKeyboard())
                && !ActionConstants.BTN_TXT_ADD_ADDRESS.equals(update.getMessage().getText())
                && !ActionConstants.BTN_TXT_BACK.equals(update.getMessage().getText())
                && !ActionConstants.BTN_TXT_ORDER.equals(update.getMessage().getText());
    }

    @Override
    public SendMessage execute(Update update) {
        Address address = bot.getSession(update).getProfile().getAddresses()
                .get(Integer.valueOf(update.getMessage().getText()) - 1);

        IKeyboardReply keyboard = (IKeyboardReply) ctx.getBean(AddressBodyKeyboard.BEAN_NAME);
        return new SendMessage().setChatId(update.getMessage().getChatId()).enableMarkdown(true)
                .setText(String.format("*Info about this address:*\n  Country: _%s_\n  City: _%s_\n  Street: _%s_",
                        address.getCountry(), address.getCity(), address.getStreet()))
                .setReplyMarkup(keyboard.generateMenu(update));
    }

}