package com.simbirsoft.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class DiscountPage {

    private final SelenideElement
            discountCardButton =  $(".js__klp-start"),
            cardNumberInput = $("#klp-num"),
            submitCardNumberButton = $(".js__submit_form"),
            errorMessage = $(".js__card_error"),
            scrollToTop = $(".scrollToTop");

    private final String
            INVALID_LENGTH_ERROR_MESSAGE = "Поле должно содержать 13 символов",
            INVALID_CARD_NUMBER_ERROR_MESSAGE = "Пользователь не авторизован или указан неверный номер карты";

    private static final String URL = "https://www.chitai-gorod.ru/bonus-program/";

    @Step("Открытие страницы Бонусная программа")
    public void openPage() {
        open(URL);
    }

    @Step("Открытие формы ввода номера бонусной карты")
    public void openDiscountCardInputForm() {
        discountCardButton.click();
    }

    @Step("Валидация введенного номера карты на длину символов")
    public void validateLengthCardNumber(String cardNumber) {
        cardNumberInput.setValue(cardNumber);
        errorMessage.shouldHave(Condition.text(INVALID_LENGTH_ERROR_MESSAGE));
    }

    @Step("Валидация введенного номера карты на наличие в БД")
    public void validateWrongCardNumber(String cardNumber) {
        cardNumberInput.setValue(cardNumber);
        submitCardNumberButton.click();
        errorMessage.shouldHave(Condition.text(INVALID_CARD_NUMBER_ERROR_MESSAGE));
        scrollToTop.click();
    }
}
