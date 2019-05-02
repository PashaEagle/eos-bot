package io.crypto.beer.telegram.bot.templ;

import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputMessageContent;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InlineModeArticle {
	
	private String id;
	
	private String description;
	
	private String title;
	
	private String url;
	
	private String thumbUrl;
	
	private Integer thumbHeight;
	
	private Integer thumbWidth;
	
	private InlineKeyboardMarkup inlineKeyboardMarkup;
	
	private InputMessageContent inputMessageContent;
	
}
