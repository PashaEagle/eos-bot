package io.crypto.beer.telegram.bot.handler;

import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import io.crypto.beer.telegram.bot.handler.action.IResponseAction;
import io.crypto.beer.telegram.bot.handler.command.CommandResponseFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ResponseHandler {

    private final Map<String, String> actionMap;
    private final CommandResponseFactory commandFactory;

    @Resource
    private ApplicationContext ctx;

    public void replyToMessage(Update update) {
        commandFactory.perform(update);

        Optional.ofNullable(update.getMessage().getText()).map(actionMap::get).map(ctx::getBean)
                .map(a -> (IResponseAction) a).ifPresent(a -> {
                    try {
                        a.perform(update);
                    } catch (TelegramApiException e) {
                        log.error(e.getMessage());
                        throw new RuntimeException(e.getMessage());
                    }
                });

    }

}