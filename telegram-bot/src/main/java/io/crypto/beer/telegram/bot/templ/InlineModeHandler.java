package io.crypto.beer.telegram.bot.templ;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import io.crypto.beer.telegram.bot.Bot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class InlineModeHandler {

	private final Bot bot;
	private final InlineModeArticleService articleService;
	
	public void replyToInlineMode(Update update) {
		try {
			String request = update.getInlineQuery().getQuery().split(" ")[0];
			switch (request) {
			case "search":
				replyToSearch(update);
				break;
			default:
				replyToDefault(update);
				break;
			}
		} catch (TelegramApiException e) {
			log.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		
	}

	private void replyToSearch(Update update) throws TelegramApiException {
		String searchParam = null;
		if (update.getInlineQuery().getQuery().trim().split(" ").length > 1) {
			searchParam = update.getInlineQuery().getQuery().split(" ")[1];
		}
		bot.execute(new AnswerInlineQuery().setInlineQueryId(update.getInlineQuery().getId()).setCacheTime(0)
				.setResults(articleService.listOfArticle(searchParam)));

	}

	private void replyToDefault(Update update) throws TelegramApiException {
		bot.execute(new AnswerInlineQuery().setInlineQueryId(update.getInlineQuery().getId()).setCacheTime(0)
				.setResults(articleService.emptyListOfArticle()));
	}

}
