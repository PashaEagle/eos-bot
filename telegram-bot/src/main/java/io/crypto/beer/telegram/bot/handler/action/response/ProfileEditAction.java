package io.crypto.beer.telegram.bot.handler.action.response;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.constants.KeyboardConstants;
import io.crypto.beer.telegram.bot.handler.keyboard.response.ProfileEditKeyboard;

@Service(value = ProfileEditAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class ProfileEditAction extends BaseResponseAction {

    public static final String BEAN_NAME = "profileEditAction";

    private final Bot bot;

    public ProfileEditAction(ApplicationContext ctx, Bot bot) {
        super(ctx, bot);
        this.bot = bot;
    }

    @Override
    protected void makeAction(Update update) {
        bot.getSession(update).nextKeyboard(KeyboardConstants.KEYBOARD_EDIT_PROFILE);
    }

    @Override
    protected String generateSendMessage(Update update) {
        return "Select a action:";
    }

    @Override
    protected String getKeyboard(Update update) {
        return ProfileEditKeyboard.BEAN_NAME;
    }

}