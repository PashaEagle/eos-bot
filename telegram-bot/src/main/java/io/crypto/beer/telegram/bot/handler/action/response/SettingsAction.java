package io.crypto.beer.telegram.bot.handler.action.response;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.constants.KeyboardConstants;
import io.crypto.beer.telegram.bot.handler.keyboard.response.SettingsKeyboard;

@Service(value = SettingsAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class SettingsAction extends BaseResponseAction {

    public static final String BEAN_NAME = "settingsAction";

    private final Bot bot;

    public SettingsAction(ApplicationContext ctx, Bot bot) {
        super(ctx, bot);
        this.bot = bot;
    }

    @Override
    protected void makeAction(Update update) {
        bot.getSession(update).nextKeyboard(KeyboardConstants.KEYBOARD_SETTINGS);
    }

    @Override
    protected String generateSendMessage(Update update) {
        return "Choose one of settings:";
    }

    @Override
    protected String getKeyboard(Update update) {
        return SettingsKeyboard.BEAN_NAME;
    }

}