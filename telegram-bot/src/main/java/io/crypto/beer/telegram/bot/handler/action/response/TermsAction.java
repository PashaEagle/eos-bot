package io.crypto.beer.telegram.bot.handler.action.response;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;

@Service(value = TermsAction.BEAN_NAME)
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class TermsAction extends BaseResponseAction {

    public static final String BEAN_NAME = "termsAction";

    public TermsAction(ApplicationContext ctx, Bot bot) {
        super(ctx, bot);
    }

    @Override
    protected void makeAction(Update update) {
    }

    @Override
    protected String generateSendMessage(Update update) {
        return "Terms\n\nWe have a great selection of products that you need.\n\nYou can execute commands:\n  - *search* - _command to search orders_\n    - firstParams - a name of the product\n\nContact information: @dkrivak\n";
    }

    @Override
    protected String getKeyboard(Update update) {
        return null;
    }

}