package io.crypto.beer.telegram.bot.handler.command.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.config.properties.BotProperties;
import io.crypto.beer.telegram.bot.handler.command.ICommandResponse;
import io.crypto.beer.telegram.bot.handler.keyboard.IKeyboardReply;
import io.crypto.beer.telegram.bot.handler.keyboard.response.MainKeyboard;
import io.crypto.beer.telegram.bot.integration.properties.WalletProperties;
import io.crypto.beer.telegram.bot.model.Profile;
import io.crypto.beer.telegram.bot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProfileAccountNameEditCommand implements ICommandResponse {

	@Autowired
	private WalletProperties walletProperties;
	@Autowired
	private BotProperties botProperties;
	
    private final ApplicationContext ctx;
    private final UserService service;
    private final Bot bot;

    @Override
    public void makeAction(Update update) {

    }

    @Override
    public boolean check(Update update) {
        return bot.getSession(update).getEditState().isAccountName();
    }

    @Override
    public SendMessage execute(Update update) {
        String message = update.getMessage().getText();
        log.info("IM HEEREREEEE!");
        
        if (message.length() != 12) {
        	return new SendMessage().setChatId(update.getMessage().getChatId()).enableMarkdown(true)
                    .setText("*Account name must be 12 character length!*");
        }
        
        walletProperties.setActor(message);
        botProperties.setTrxAmount(0);
        botProperties.setPrevAmount(0);
        Profile profile = bot.getSession(update).getProfile();
        profile.setAccountName(message);
        profile = service.update(profile.getId(), profile);

        IKeyboardReply keyboard = (IKeyboardReply) ctx.getBean(MainKeyboard.BEAN_NAME);
        return new SendMessage().setChatId(update.getMessage().getChatId()).enableMarkdown(true)
                .setText("Account name successfully set");
    }

}
