package io.crypto.beer.telegram.bot.handler.keyboard.callBack.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import io.crypto.beer.telegram.bot.handler.keyboard.IKeyboardReply;

public abstract class BaseCartKeyboard implements IKeyboardReply {

    private List<List<InlineKeyboardButton>> rows;
    private List<InlineKeyboardButton> firstRow;
    private List<InlineKeyboardButton> secondRow;
    private List<InlineKeyboardButton> thirdRow;
    private List<InlineKeyboardButton> fourthRow;
    private List<InlineKeyboardButton> fifthRow;

    public BaseCartKeyboard() {
        this.rows = new ArrayList<>();
        this.firstRow = new ArrayList<>();
        this.secondRow = new ArrayList<>();
        this.thirdRow = new ArrayList<>();
        this.fourthRow = new ArrayList<>();
        this.fifthRow = new ArrayList<>();
    }

    public abstract void fillMenuRows(Update update);

    public InlineKeyboardMarkup generateMenu(Update update) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        fillMenuRows(update);

        Stream.of(firstRow, secondRow, thirdRow, fourthRow, fifthRow).forEachOrdered(this::addRow);

        keyboardMarkup.setKeyboard(rows);

        return keyboardMarkup;
    }

    private void addRow(List<InlineKeyboardButton> row) {
        if (!row.isEmpty()) {
            rows.add(row);
        }
    }

    protected void fillFirstRow(Map<String, String> button) {
        button.forEach((k, v) -> firstRow.add(new InlineKeyboardButton().setText(k).setCallbackData(v)));
    }

    protected void fillSecondRow(Map<String, String> button) {
        button.forEach((k, v) -> secondRow.add(new InlineKeyboardButton().setText(k).setCallbackData(v)));
    }

    protected void fillThirdRow(Map<String, String> button) {
        button.forEach((k, v) -> thirdRow.add(new InlineKeyboardButton().setText(k).setCallbackData(v)));
    }

    protected void fillFourthRow(Map<String, String> button) {
        button.forEach((k, v) -> fourthRow.add(new InlineKeyboardButton().setText(k).setCallbackData(v)));
    }

    protected void fillFifthRow(Map<String, String> button) {
        button.forEach((k, v) -> fifthRow.add(new InlineKeyboardButton().setText(k).setCallbackData(v)));
    }

}