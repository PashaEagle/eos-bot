package io.crypto.beer.telegram.bot.templ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;

import io.crypto.beer.telegram.bot.constants.Constants;

@Service
public class InlineModeArticleService {

	public List<InlineQueryResult> listOfArticle(String partOfSearch) {
		
		List<InlineModeArticle> articles = new ArrayList<>();

		articles.add(InlineModeArticle.builder().id("0").title("bmw #1").description("New BMW").thumbHeight(300)
				.thumbWidth(300).thumbUrl(Constants.BMW).inlineKeyboardMarkup(InlineModeKeyboard.inlineKeyboard())
				.build());

		articles.add(InlineModeArticle.builder().id("1").title("nissan").description("New Nissan").thumbHeight(300)
				.thumbWidth(300).thumbUrl(Constants.NISSAN).inlineKeyboardMarkup(InlineModeKeyboard.inlineKeyboard())
				.build());

		articles.add(InlineModeArticle.builder().id("2").title("mercedes #1").description("New Mercedes")
				.thumbHeight(300).thumbWidth(300).thumbUrl(Constants.MERCEDES)
				.inlineKeyboardMarkup(InlineModeKeyboard.inlineKeyboard()).build());

		articles.add(InlineModeArticle.builder().id("3").title("mercedes #2").description("New Mercedes-Benz")
				.thumbHeight(300).thumbWidth(300).thumbUrl(Constants.MERCEDES)
				.inlineKeyboardMarkup(InlineModeKeyboard.inlineKeyboard()).build());

		articles.add(InlineModeArticle.builder().id("4").title("nissan").description("New Nissan Skyline")
				.thumbHeight(300).thumbWidth(300).thumbUrl(Constants.NISSAN)
				.inlineKeyboardMarkup(InlineModeKeyboard.inlineKeyboard()).build());

		return listOfArticle(
				articles.stream().filter(line -> partOfSearch == null || partOfSearch.equals(line.getTitle()))
						.collect(Collectors.toList()));
	}

	public List<InlineQueryResult> emptyListOfArticle() {
		return Collections.emptyList();
	}

	private List<InlineQueryResult> listOfArticle(List<InlineModeArticle> articles) {
		List<InlineQueryResult> results = new ArrayList<>();

		articles.forEach(article -> {
			results.add(new InlineQueryResultArticle().setId(article.getId()).setTitle(article.getTitle())
					.setDescription(article.getDescription()).setThumbHeight(article.getThumbHeight())
					.setThumbWidth(article.getThumbWidth()).setInputMessageContent(forArticle(article))
					.setThumbUrl(article.getThumbUrl()).setReplyMarkup(article.getInlineKeyboardMarkup()));
		});

		return results;
	}

	private InputMessageContent forArticle(InlineModeArticle article) {
		return new InputTextMessageContent().enableHtml(true).enableWebPagePreview().setMessageText("text");
	}

}
