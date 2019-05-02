package io.crypto.beer.telegram.bot.handler.command;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface ICommandCallBack {
    void makeAction(Update update);
    boolean check(Update update);
    EditMessageText execute(Update update);
}
