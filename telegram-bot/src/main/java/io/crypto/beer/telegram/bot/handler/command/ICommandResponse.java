package io.crypto.beer.telegram.bot.handler.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface ICommandResponse {
    void makeAction(Update update);
	boolean check(Update update);
	SendMessage execute(Update update);
}
