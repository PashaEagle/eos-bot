package io.crypto.beer.telegram.bot.handler.command.response;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import io.crypto.beer.telegram.bot.Bot;
import io.crypto.beer.telegram.bot.handler.command.ICommandResponse;
import io.crypto.beer.telegram.bot.handler.keyboard.IKeyboardReply;
import io.crypto.beer.telegram.bot.handler.keyboard.response.ProfileEditKeyboard;
import io.crypto.beer.telegram.bot.model.Profile;
import io.crypto.beer.telegram.bot.service.UserService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProfilePhoneEditCommand implements ICommandResponse {

    private final ApplicationContext ctx;
    private final UserService service;
    private final Bot bot;

    @Override
    public void makeAction(Update update) {

    }

    @Override
    public boolean check(Update update) {
        return bot.getSession(update).getEditState().isProfilePhone();
    }

    @Override
    public SendMessage execute(Update update) {
        String message = update.getMessage().getText();

        Profile profile = bot.getSession(update).getProfile();
        profile.setPhone(message);
        profile = service.update(profile.getId(), profile);

        IKeyboardReply keyboard = (IKeyboardReply) ctx.getBean(ProfileEditKeyboard.BEAN_NAME);
        return new SendMessage().setChatId(update.getMessage().getChatId()).enableMarkdown(true)
                .setText(String.format("*Your profile*:\n  _Full name:_ %s\n  _Phone:_ %s\n  _Address:_ %s",
                        profile.getFullName(), profile.getPhone(), profile.getAddress()))
                .setReplyMarkup(keyboard.generateMenu(update));
    }

}
