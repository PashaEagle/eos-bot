package io.crypto.beer.telegram.bot.handler.action.response.address;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.handler.action.response.BaseResponseAction;
import io.crypto.beer.telegram.bot.handler.keyboard.response.address.AddressBodyKeyboard;
import io.crypto.beer.telegram.bot.model.Address;
import io.crypto.beer.telegram.bot.session.Session;

@Service(value = AddressSelectionAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class AddressSelectionAction extends BaseResponseAction {

    public static final String BEAN_NAME = "addressSelectionAction";
    private final Bot bot;

    public AddressSelectionAction(ApplicationContext ctx, Bot bot) {
        super(ctx, bot);
        this.bot = bot;
    }

    @Override
    protected void makeAction(Update update) {
        Session session = bot.getSession(update);
        session.nextKeyboard(update.getMessage().getText());
        session.getEditState().clickNumberOfAddress(Integer.valueOf(update.getMessage().getText()));
    }

    @Override
    protected String generateSendMessage(Update update) {
        Address address = bot.getSession(update).getProfile().getAddresses()
                .get(Integer.valueOf(update.getMessage().getText()) - 1);
        return String.format("*Info about this address:*\n  Country: _%s_\n  City: _%s_\n  Street: _%s_",
                address.getCountry(), address.getCity(), address.getStreet());
    }

    @Override
    protected String getKeyboard(Update update) {
        return AddressBodyKeyboard.BEAN_NAME;
    }

}
