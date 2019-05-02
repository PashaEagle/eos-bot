package io.crypto.beer.telegram.bot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Flag;
import org.telegram.abilitybots.api.objects.Reply;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import io.crypto.beer.telegram.bot.config.properties.BotProperties;
import io.crypto.beer.telegram.bot.constants.KeyboardConstants;
import io.crypto.beer.telegram.bot.handler.CallBackHandler;
import io.crypto.beer.telegram.bot.handler.ResponseHandler;
import io.crypto.beer.telegram.bot.model.Address;
import io.crypto.beer.telegram.bot.model.Profile;
import io.crypto.beer.telegram.bot.session.EditState;
import io.crypto.beer.telegram.bot.session.OrderState;
import io.crypto.beer.telegram.bot.session.Session;
import io.crypto.beer.telegram.bot.session.ShopData;
import io.crypto.beer.telegram.bot.templ.InlineModeHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Bot extends AbilityBot {

    private final ResponseHandler responseHandler;
    private final CallBackHandler callBackHandler;
    private final InlineModeHandler inlineModeHandler;

    private final BotProperties properties;

    private Map<Long, Session> sessions;

    @Lazy
    @Autowired
    public Bot(ResponseHandler responseHandler, CallBackHandler callBackHandler, InlineModeHandler inlineModeHandler,
            BotProperties properties) {
        super(properties.getToken(), properties.getUsername());
        this.sessions = new HashMap<>();
        this.responseHandler = responseHandler;
        this.callBackHandler = callBackHandler;
        this.inlineModeHandler = inlineModeHandler;
        this.properties = properties;
    }

    public Reply replyToMessage() {
        Consumer<Update> action = upd -> responseHandler.replyToMessage(upd);
        return Reply.of(action, Flag.MESSAGE);
    }

    public Reply replyToCallBack() {
        Consumer<Update> action = upd -> callBackHandler.replyToCallBack(upd);
        return Reply.of(action, Flag.CALLBACK_QUERY);
    }

    public Reply replyToInlineMode() {
        Consumer<Update> action = upd -> inlineModeHandler.replyToInlineMode(upd);
        return Reply.of(action, Flag.INLINE_QUERY);
    }

    @Override
    public int creatorId() {
        return properties.getCreator().getId();
    }

    public Session getSession(Update update) {
        log.debug("Method getSession, update: {}", update);
        LocalDateTime now = LocalDateTime.now();
        Session session = null;
        if (update.hasCallbackQuery()) {
            session = this.sessions.get(update.getCallbackQuery().getMessage().getChatId());
        } else {
            session = this.sessions.get(update.getMessage().getChatId());
        }

        if (session == null) {
            User user = update.getMessage().getFrom();
            String fullName = String.format("%s %s", user.getFirstName(), user.getLastName());
            session = Session.builder().chatId(update.getMessage().getChatId())
                    .keyboard(KeyboardConstants.KEYBOARD_START)
                    .fullPath(String.format("/%s", KeyboardConstants.KEYBOARD_START))
                    .profile(Profile.builder().fullName(fullName).bot(user.getBot()).address(Address.builder().build())
                            .build())
                    .editState(new EditState()).orderState(OrderState.builder().items(new ArrayList<>()).build())
                    .shopData(ShopData.builder().build())
                    .lastActiveDateTime(LocalDateTime.now()).build();
            sessions.put(update.getMessage().getChatId(), session);
        }
        if (session.getProfile() == null || now.isBefore(session.getLastActiveDateTime())
                || now.isAfter(session.getLastActiveDateTime().plusMinutes(properties.getSession().getMinutes()))) {
            session = Session.builder().chatId(update.getMessage().getChatId()).keyboard(session.getKeyboard())
                    .fullPath("").profile(session.getProfile()).lastActiveDateTime(LocalDateTime.now())
                    .orderState(OrderState.builder().items(new ArrayList<>()).build())
                    .shopData(ShopData.builder().build())
                    .editState(new EditState()).build();
            sessions.put(update.getMessage().getChatId(), session);

        }
        log.debug("Method getSession, session: {}", session);
        return session;
    }

}
