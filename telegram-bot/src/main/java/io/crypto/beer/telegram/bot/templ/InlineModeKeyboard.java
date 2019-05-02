package io.crypto.beer.telegram.bot.templ;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class InlineModeKeyboard {

	public static InlineKeyboardMarkup inlineKeyboard() {
		InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup();
		
		List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
		List<InlineKeyboardButton> rowInline = new ArrayList<>();
		
//		rowInline.add(new InlineKeyboardButton().setText(Constants.BUY).setCallbackData(Constants.BUY));
//		rowInline.add(new InlineKeyboardButton().setText(Constants.REVIEW).setCallbackData(Constants.REVIEW));
		
		rowsInline.add(rowInline);
		
		inlineKeyboard.setKeyboard(rowsInline);
		
		return inlineKeyboard;
	}
	
}
