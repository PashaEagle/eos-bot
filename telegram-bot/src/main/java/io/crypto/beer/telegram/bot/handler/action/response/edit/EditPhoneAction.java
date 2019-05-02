package io.crypto.beer.telegram.bot.handler.action.response.edit;

import java.util.Optional;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.handler.action.response.BaseResponseAction;
import io.crypto.beer.telegram.bot.model.Profile;

@Service(value = EditPhoneAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class EditPhoneAction extends BaseResponseAction {

    public static final String BEAN_NAME = "editPhoneAction";

    private final Bot bot;

    public EditPhoneAction(ApplicationContext ctx, Bot bot) {
        super(ctx, bot);
        this.bot = bot;
    }

    @Override
    protected void makeAction(Update update) {
        bot.getSession(update).getEditState().clickProfilePhone();
    }

    @Override
    protected String generateSendMessage(Update update) {
        Profile profile = bot.getSession(update).getProfile();
        String phone = Optional.ofNullable(profile).map(Profile::getPhone).orElse("+3809999999999");
        return String.format("*You current phone:* _%s_\nPlease text your phone", phone);
    }

    @Override
    protected String getKeyboard(Update update) {
        return null;
    }

}