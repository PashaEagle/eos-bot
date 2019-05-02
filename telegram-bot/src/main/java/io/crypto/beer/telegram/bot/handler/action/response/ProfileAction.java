package io.crypto.beer.telegram.bot.handler.action.response;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.constants.KeyboardConstants;
import io.crypto.beer.telegram.bot.handler.keyboard.response.ProfileKeyboard;
import io.crypto.beer.telegram.bot.model.Profile;

@Service(value = ProfileAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class ProfileAction extends BaseResponseAction {

    public static final String BEAN_NAME = "profileAction";

    private final Bot bot;

    public ProfileAction(ApplicationContext ctx, Bot bot) {
        super(ctx, bot);
        this.bot = bot;
    }

    @Override
    protected void makeAction(Update update) {
        bot.getSession(update).nextKeyboard(KeyboardConstants.KEYBOARD_PROFILE);
    }

    @Override
    protected String generateSendMessage(Update update) {
        Profile profile = bot.getSession(update).getProfile();
        return String.format("*Your profile*:\n  _Full name:_ %s\n  _Phone:_ %s\n  _Address:_ %s",
                profile.getFullName(), profile.getPhone(), profile.getAddress());
    }

    @Override
    protected String getKeyboard(Update update) {
        return ProfileKeyboard.BEAN_NAME;
    }

}