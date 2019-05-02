package io.crypto.beer.telegram.bot.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import io.crypto.beer.telegram.bot.constants.ActionConstants;
import io.crypto.beer.telegram.bot.constants.CallBackConstants;
import io.crypto.beer.telegram.bot.constants.KeyboardConstants;
import io.crypto.beer.telegram.bot.handler.action.callBack.BackToCartAction;
import io.crypto.beer.telegram.bot.handler.action.callBack.cart.BackAction;
import io.crypto.beer.telegram.bot.handler.action.callBack.cart.address.CartAddressAction;
import io.crypto.beer.telegram.bot.handler.action.callBack.cart.buyBeer.BuyBeerBreweryAction;
import io.crypto.beer.telegram.bot.handler.action.callBack.cart.buyBeer.BuyBeerClearFiltersAction;
import io.crypto.beer.telegram.bot.handler.action.callBack.cart.buyBeer.BuyBeerVariationAction;
import io.crypto.beer.telegram.bot.handler.action.callBack.cart.buyBeer.CartBuyBeerAction;
import io.crypto.beer.telegram.bot.handler.action.callBack.cart.removeItem.CartRemoveAction;
import io.crypto.beer.telegram.bot.handler.action.response.BackToMenuAction;
import io.crypto.beer.telegram.bot.handler.action.response.LanguageAction;
import io.crypto.beer.telegram.bot.handler.action.response.MainMenuAction;
import io.crypto.beer.telegram.bot.handler.action.response.ProfileAction;
import io.crypto.beer.telegram.bot.handler.action.response.ProfileEditAction;
import io.crypto.beer.telegram.bot.handler.action.response.SettingsAction;
import io.crypto.beer.telegram.bot.handler.action.response.StartAction;
import io.crypto.beer.telegram.bot.handler.action.response.TermsAction;
import io.crypto.beer.telegram.bot.handler.action.response.address.AddressAction;
import io.crypto.beer.telegram.bot.handler.action.response.address.AddressAddAction;
import io.crypto.beer.telegram.bot.handler.action.response.address.AddressEditAction;
import io.crypto.beer.telegram.bot.handler.action.response.address.AddressRemoveAction;
import io.crypto.beer.telegram.bot.handler.action.response.address.AddressSelectAction;
import io.crypto.beer.telegram.bot.handler.action.response.admin.action.AdminAction;
import io.crypto.beer.telegram.bot.handler.action.response.admin.action.AdminBreweryAction;
import io.crypto.beer.telegram.bot.handler.action.response.admin.action.AdminItemAction;
import io.crypto.beer.telegram.bot.handler.action.response.admin.action.AdminMenuAction;
import io.crypto.beer.telegram.bot.handler.action.response.admin.action.AdminVariationAction;
import io.crypto.beer.telegram.bot.handler.action.response.admin.keyboard.AdminBreweryKeyboard;
import io.crypto.beer.telegram.bot.handler.action.response.admin.keyboard.AdminItemKeyboard;
import io.crypto.beer.telegram.bot.handler.action.response.admin.keyboard.AdminKeyboard;
import io.crypto.beer.telegram.bot.handler.action.response.admin.keyboard.AdminVariationKeyboard;
import io.crypto.beer.telegram.bot.handler.action.response.edit.EditCityAction;
import io.crypto.beer.telegram.bot.handler.action.response.edit.EditCountryAction;
import io.crypto.beer.telegram.bot.handler.action.response.edit.EditFullNameAction;
import io.crypto.beer.telegram.bot.handler.action.response.edit.EditPhoneAction;
import io.crypto.beer.telegram.bot.handler.action.response.edit.EditStreetAction;
import io.crypto.beer.telegram.bot.handler.action.response.order.CartAction;
import io.crypto.beer.telegram.bot.handler.action.response.order.OrderAction;
import io.crypto.beer.telegram.bot.handler.keyboard.callBack.cart.CartKeyboard;
import io.crypto.beer.telegram.bot.handler.keyboard.response.LanguageKeyboard;
import io.crypto.beer.telegram.bot.handler.keyboard.response.MainKeyboard;
import io.crypto.beer.telegram.bot.handler.keyboard.response.OrderKeyboard;
import io.crypto.beer.telegram.bot.handler.keyboard.response.ProfileEditKeyboard;
import io.crypto.beer.telegram.bot.handler.keyboard.response.ProfileKeyboard;
import io.crypto.beer.telegram.bot.handler.keyboard.response.SettingsKeyboard;
import io.crypto.beer.telegram.bot.handler.keyboard.response.address.AddressAddKeyboard;
import io.crypto.beer.telegram.bot.handler.keyboard.response.address.AddressEditKeyboard;
import io.crypto.beer.telegram.bot.handler.keyboard.response.address.AddressKeyboard;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class Config {

    @Bean
    public RestTemplate template(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public Map<String, String> actionMap() {
        Map<String, String> keyMap = new HashMap<>();
        keyMap.put(ActionConstants.BTN_TXT_MAIN_MENU, MainMenuAction.BEAN_NAME);
        keyMap.put(ActionConstants.BTN_TXT_START, StartAction.BEAN_NAME);
        keyMap.put(ActionConstants.BTN_TXT_ORDER, OrderAction.BEAN_NAME);
        keyMap.put(ActionConstants.BTN_TXT_TERMS, TermsAction.BEAN_NAME);
        keyMap.put(ActionConstants.BTN_TXT_SETTINGS, SettingsAction.BEAN_NAME);
        keyMap.put(ActionConstants.BTN_TXT_LANGUAGE, LanguageAction.BEAN_NAME);
        keyMap.put(ActionConstants.BTN_TXT_PROFILE, ProfileAction.BEAN_NAME);
        keyMap.put(ActionConstants.BTN_TXT_EDIT_PROFILE, ProfileEditAction.BEAN_NAME);
        keyMap.put(ActionConstants.BTN_TXT_FULL_NAME, EditFullNameAction.BEAN_NAME);
        keyMap.put(ActionConstants.BTN_TXT_PHONE, EditPhoneAction.BEAN_NAME);
        keyMap.put(ActionConstants.BTN_TXT_ADDRESS, AddressAction.BEAN_NAME);
        keyMap.put(ActionConstants.BTN_TXT_COUNTRY, EditCountryAction.BEAN_NAME);
        keyMap.put(ActionConstants.BTN_TXT_CITY, EditCityAction.BEAN_NAME);
        keyMap.put(ActionConstants.BTN_TXT_STREET, EditStreetAction.BEAN_NAME);
        keyMap.put(ActionConstants.BTN_TXT_BACK, BackToMenuAction.BEAN_NAME);
        keyMap.put(ActionConstants.BTN_TXT_EDIT_ADDRESS, AddressEditAction.BEAN_NAME);
        keyMap.put(ActionConstants.BTN_TXT_ADD_ADDRESS, AddressAddAction.BEAN_NAME);
        keyMap.put(ActionConstants.BTN_TXT_REMOVE_ADDRESS, AddressRemoveAction.BEAN_NAME);
        keyMap.put(ActionConstants.BTN_TXT_SELECT, AddressSelectAction.BEAN_NAME);
        keyMap.put(ActionConstants.BTN_TXT_CART, CartAction.BEAN_NAME);
        keyMap.put(ActionConstants.BTN_TXT_ADMIN, AdminAction.BEAN_NAME);
        keyMap.put(ActionConstants.BTN_TXT_ADMIN_VARIATION, AdminVariationAction.BEAN_NAME);
        keyMap.put(ActionConstants.BTN_TXT_ADMIN_BREWERY, AdminBreweryAction.BEAN_NAME);
        keyMap.put(ActionConstants.BTN_TXT_ADMIN_ITEM, AdminItemAction.BEAN_NAME);
        keyMap.put(ActionConstants.BTN_TXT_MAIN_ADMIN_MENU, AdminMenuAction.BEAN_NAME);

        return keyMap;
    }

    @Bean
    public Map<String, String> callBackMap() {
        Map<String, String> keyMap = new HashMap<>();
        keyMap.put(CallBackConstants.BTN_TXT_BUY_BEER_CALL_BACK, CartBuyBeerAction.BEAN_NAME);
        keyMap.put(CallBackConstants.BTN_TXT_ADDRESS_CALL_BACK, CartAddressAction.BEAN_NAME);
        keyMap.put(CallBackConstants.BTN_TXT_REMOVE_CALL_BACK, CartRemoveAction.BEAN_NAME);
        keyMap.put(CallBackConstants.BTN_TXT_VARIATION_CALL_BACK, BuyBeerVariationAction.BEAN_NAME);
        keyMap.put(CallBackConstants.BTN_TXT_BREWERY_CALL_BACK, BuyBeerBreweryAction.BEAN_NAME);
        keyMap.put(CallBackConstants.BTN_TXT_CLEAR_FILTERS_CALL_BACK, BuyBeerClearFiltersAction.BEAN_NAME);
        keyMap.put(CallBackConstants.BTN_TXT_BACK_TO_CART_CALL_BACK, BackToCartAction.BEAN_NAME);
        keyMap.put(CallBackConstants.BTN_TXT_BACK_CALL_BACK, BackAction.BEAN_NAME);
        return keyMap;
    }

    @Bean
    public Map<String, String> keyboardMap() {
        Map<String, String> keyMap = new HashMap<>();
        keyMap.put(KeyboardConstants.KEYBOARD_START, MainKeyboard.BEAN_NAME);
        keyMap.put(KeyboardConstants.KEYBOARD_ORDER, OrderKeyboard.BEAN_NAME);
        keyMap.put(KeyboardConstants.KEYBOARD_SETTINGS, SettingsKeyboard.BEAN_NAME);
        keyMap.put(KeyboardConstants.KEYBOARD_LANGUAGE, LanguageKeyboard.BEAN_NAME);
        keyMap.put(KeyboardConstants.KEYBOARD_PROFILE, ProfileKeyboard.BEAN_NAME);
        keyMap.put(KeyboardConstants.KEYBOARD_EDIT_PROFILE, ProfileEditKeyboard.BEAN_NAME);
        keyMap.put(KeyboardConstants.KEYBOARD_ADDRESS, AddressKeyboard.BEAN_NAME);
        keyMap.put(KeyboardConstants.KEYBOARD_EDIT_ADDRESS, AddressEditKeyboard.BEAN_NAME);
        keyMap.put(KeyboardConstants.KEYBOARD_ADD_ADDRESS, AddressAddKeyboard.BEAN_NAME);
        keyMap.put(KeyboardConstants.KEYBOARD_CART, CartKeyboard.BEAN_NAME);
        keyMap.put(KeyboardConstants.KEYBOARD_ADMIN, AdminKeyboard.BEAN_NAME);
        keyMap.put(KeyboardConstants.KEYBOARD_ADMIN_VARIATION, AdminVariationKeyboard.BEAN_NAME);
        keyMap.put(KeyboardConstants.KEYBOARD_ADMIN_BREWERY, AdminBreweryKeyboard.BEAN_NAME);
        keyMap.put(KeyboardConstants.KEYBOARD_ADMIN_ITEM, AdminItemKeyboard.BEAN_NAME);
        return keyMap;
    }

}
