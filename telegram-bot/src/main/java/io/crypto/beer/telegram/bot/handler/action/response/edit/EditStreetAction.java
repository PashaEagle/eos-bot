package io.crypto.beer.telegram.bot.handler.action.response.edit;

import java.util.Optional;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.handler.action.response.BaseResponseAction;
import io.crypto.beer.telegram.bot.model.Address;
import io.crypto.beer.telegram.bot.session.Session;

@Service(value = EditStreetAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class EditStreetAction extends BaseResponseAction {

    public static final String BEAN_NAME = "editStreetAction";

    private final Bot bot;

    public EditStreetAction(ApplicationContext ctx, Bot bot) {
        super(ctx, bot);
        this.bot = bot;
    }

    @Override
    protected void makeAction(Update update) {
        bot.getSession(update).getEditState().clickProfileAddressStreet();
    }

    @Override
    protected String generateSendMessage(Update update) {
    	Session session = bot.getSession(update);
        Address address;
        
        if (session.getEditState().getNumberOfAddress() == null) {
        	address = session.getProfile().getAddress();
        } else {
        	address = session.getProfile().getAddresses().get(session.getEditState().getNumberOfAddress() - 1);
        }
        
        String street = Optional.ofNullable(address).map(Address::getStreet).orElse("null");
        return String.format("*You current street:* _%s_\nPlease text your street", street);
    }

    @Override
    protected String getKeyboard(Update update) {
        return null;
    }

}