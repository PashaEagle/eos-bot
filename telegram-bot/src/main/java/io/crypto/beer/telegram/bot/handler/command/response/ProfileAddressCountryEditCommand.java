package io.crypto.beer.telegram.bot.handler.command.response;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.handler.command.ICommandResponse;
import io.crypto.beer.telegram.bot.handler.keyboard.IKeyboardReply;
import io.crypto.beer.telegram.bot.handler.keyboard.response.address.AddressEditKeyboard;
import io.crypto.beer.telegram.bot.model.Address;
import io.crypto.beer.telegram.bot.service.AddressService;
import io.crypto.beer.telegram.bot.session.Session;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProfileAddressCountryEditCommand implements ICommandResponse {

    private final ApplicationContext ctx;
    private final AddressService service;
    private final Bot bot;

    @Override
    public void makeAction(Update update) {

    }

    @Override
    public boolean check(Update update) {
        Session session = bot.getSession(update);
        return session.getEditState().isAddressCountry() && session.getEditState().getNumberOfAddress() != null;
    }

    @Override
    public SendMessage execute(Update update) {
        Session session = bot.getSession(update);
        String message = update.getMessage().getText();

        Address address = session.getProfile().getAddresses().get(session.getEditState().getNumberOfAddress() - 1);
        address.setCountry(message);
        address = service.update(address.getId(), address);

        IKeyboardReply keyboard = (IKeyboardReply) ctx.getBean(AddressEditKeyboard.BEAN_NAME);
        return new SendMessage().setChatId(update.getMessage().getChatId()).enableMarkdown(true)
                .setText(String.format("*Info about this address:*\n  Country: _%s_\n  City: _%s_\n  Street: _%s_",
                        address.getCountry(), address.getCity(), address.getStreet()))
                .setReplyMarkup(keyboard.generateMenu(update));
    }

}
