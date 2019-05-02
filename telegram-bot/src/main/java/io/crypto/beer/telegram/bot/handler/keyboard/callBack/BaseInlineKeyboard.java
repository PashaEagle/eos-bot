package io.crypto.beer.telegram.bot.handler.keyboard.callBack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import io.crypto.beer.telegram.bot.constants.CallBackConstants;
import io.crypto.beer.telegram.bot.handler.keyboard.IKeyboardInline;
import io.crypto.beer.telegram.bot.model.Address;

public abstract class BaseInlineKeyboard implements IKeyboardInline {

    private List<List<InlineKeyboardButton>> rows;
    private List<InlineKeyboardButton> firstRow;
    private List<InlineKeyboardButton> secondRow;
    private List<InlineKeyboardButton> thirdRow;
    private List<InlineKeyboardButton> fourthRow;
    private List<InlineKeyboardButton> fifthRow;
    private List<InlineKeyboardButton> sixthRow;

    public BaseInlineKeyboard() {
        this.rows = new ArrayList<>();
        this.firstRow = new ArrayList<>();
        this.secondRow = new ArrayList<>();
        this.thirdRow = new ArrayList<>();
        this.fourthRow = new ArrayList<>();
        this.fifthRow = new ArrayList<>();
        this.sixthRow = new ArrayList<>();
    }

    public abstract void fillMenuRows(Update update);

    public InlineKeyboardMarkup generateMenu(Update update) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        fillMenuRows(update);

        Stream.of(firstRow, secondRow, thirdRow, fourthRow, fifthRow, sixthRow).forEachOrdered(this::addRow);

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

    protected void fillSixthRow(Map<String, String> button) {
        button.forEach((k, v) -> sixthRow.add(new InlineKeyboardButton().setText(k).setCallbackData(v)));
    }
    
    protected void generateAddressKeyboard(Map<Integer, Address> buttons) {
        Optional.ofNullable(buttons.get(0)).ifPresent(a -> fillFirstRow(Collections.singletonMap(a.toString(),
                String.format("%s%s", CallBackConstants.BTN_TXT_ADDRESS_SELECT_CALL_BACK, a.getId()))));

        Optional.ofNullable(buttons.get(1)).ifPresent(a -> fillSecondRow(Collections.singletonMap(a.toString(),
                String.format("%s%s", CallBackConstants.BTN_TXT_ADDRESS_SELECT_CALL_BACK, a.getId()))));

        Optional.ofNullable(buttons.get(2)).ifPresent(a -> fillThirdRow(Collections.singletonMap(a.toString(),
                String.format("%s%s", CallBackConstants.BTN_TXT_ADDRESS_SELECT_CALL_BACK, a.getId()))));

        Optional.ofNullable(buttons.get(3)).ifPresent(a -> fillFourthRow(Collections.singletonMap(a.toString(),
                String.format("%s%s", CallBackConstants.BTN_TXT_ADDRESS_SELECT_CALL_BACK, a.getId()))));

        Optional.ofNullable(buttons.get(4)).ifPresent(a -> fillFifthRow(Collections.singletonMap(a.toString(),
                String.format("%s%s", CallBackConstants.BTN_TXT_ADDRESS_SELECT_CALL_BACK, a.getId()))));

    }

}
