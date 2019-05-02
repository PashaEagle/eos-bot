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

@Service(value = EditCityAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class EditCityAction extends BaseResponseAction {

    public static final String BEAN_NAME = "editCityAction";

    private final Bot bot;

    public EditCityAction(ApplicationContext ctx, Bot bot) {
        super(ctx, bot);
        this.bot = bot;
    }

    @Override
    protected void makeAction(Update update) {
        bot.getSession(update).getEditState().clickProfileAddressCity();
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
        
        String city = Optional.ofNullable(address).map(Address::getCity).orElse("null");
        return String.format("*You current city:* _%s_\nPlease text your city", city);
    }

    @Override
    protected String getKeyboard(Update update) {
        return null;
    }

}