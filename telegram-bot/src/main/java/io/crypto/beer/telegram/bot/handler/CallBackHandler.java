package io.crypto.beer.telegram.bot.handler;

import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import io.crypto.beer.telegram.bot.handler.action.ICallBackAction;
import io.crypto.beer.telegram.bot.handler.command.CommandCallBackFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CallBackHandler {

    private final Map<String, String> callBackMap;
    private final CommandCallBackFactory commandFactory;

    @Resource
    private ApplicationContext ctx;

    public void replyToCallBack(Update update) {
        commandFactory.perform(update);

        Optional.ofNullable(update.getCallbackQuery().getData()).map(callBackMap::get).map(ctx::getBean)
                .map(a -> (ICallBackAction) a).ifPresent(a -> {
                    try {
                        a.perform(update);
                    } catch (TelegramApiException e) {
                        log.error(e.getMessage());
                        throw new RuntimeException(e.getMessage());
                    }
                });

    }

}