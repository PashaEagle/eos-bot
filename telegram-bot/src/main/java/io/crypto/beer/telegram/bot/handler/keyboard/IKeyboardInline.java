package io.crypto.beer.telegram.bot.handler.keyboard;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@FunctionalInterface
public interface IKeyboardInline {
    
    InlineKeyboardMarkup generateMenu(Update update);
    
}

