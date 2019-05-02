package io.crypto.beer.telegram.bot.handler.action.callBack;

import java.util.Optional;

import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.handler.action.ICallBackAction;
import io.crypto.beer.telegram.bot.handler.keyboard.IKeyboardInline;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public abstract class BaseCallBackAction implements ICallBackAction {

    private final ApplicationContext ctx;
    private final Bot bot;

    protected abstract void makeAction(Update update);

    protected abstract String generateSendMessage(Update update);

    protected abstract String getKeyboard(Update update);

    @Override
    public void perform(Update update) throws TelegramApiException {
        log.debug("Method perform of callBack action, update: {}", update);

        if (!bot.getSession(update).getProfile().isBot()) {
            makeAction(update);

            EditMessageText message = new EditMessageText().setChatId(update.getCallbackQuery().getMessage().getChatId())
                    .setMessageId(update.getCallbackQuery().getMessage().getMessageId()).enableMarkdown(true)
                    .setText(generateSendMessage(update));

            Optional.ofNullable(getKeyboard(update)).map(ctx::getBean).map(k -> (IKeyboardInline) k)
                    .ifPresent(k -> message.setReplyMarkup(k.generateMenu(update)));

            bot.execute(message);
        } else {
            SendMessage message = new SendMessage().setChatId(update.getCallbackQuery().getMessage().getChatId()).enableMarkdown(true)
                    .setText("Sorry, you can't work with this bot");

            bot.execute(message);
        }
    }
}
