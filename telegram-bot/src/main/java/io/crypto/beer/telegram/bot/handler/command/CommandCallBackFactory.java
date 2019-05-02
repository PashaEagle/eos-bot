package io.crypto.beer.telegram.bot.handler.command;

import java.util.List;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import io.crypto.beer.telegram.bot.Bot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommandCallBackFactory {

    private final Bot bot;
    private final List<ICommandCallBack> commands;

    public void perform(Update update) {
        log.debug("Method perform of command, update: {}", update);

        commands.stream().filter(c -> c.check(update)).findFirst().ifPresent(c -> {
            try {
                c.makeAction(update);
                bot.execute(c.execute(update));
            } catch (TelegramApiException e) {
                log.error(e.getMessage());
                throw new RuntimeException(e.getMessage());
            }
        });
    }
}
