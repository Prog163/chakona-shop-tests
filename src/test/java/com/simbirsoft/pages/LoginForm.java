package com.simbirsoft.pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class LoginForm {

    private final SelenideElement
            loginFormButton = $(".js__showPopupLogin"),
            loginAndPasswordOption = $(byText("Войти с логином и паролем")),
            emailInput = $("#popup-email"),
            passwordInput = $("#popup-password"),
            submitButton = $("#authSubmit"),
            loginErrorMessage = $$("div[data-chg-error-auth]").get(1),
            profileMenu = $(".header-menu__item .header__show-menu"),
            profileHeader = $(".content h1"),
            profileDataHeader = $(".account-group h2"),
            profileEmail = $$(".short-personal__content").get(0),
            logoutButton = $$(".account-menu__text-link").findBy(Condition.text("Выйти")),
            popupCloseButton = $$(".js__popup__close").findBy(Condition.text("Не сейчас"));

    private final ElementsCollection
            profileMenuItems = $$(".container__leftside .account-menu__list .account-menu__item");

    private final String
            LOGIN_ERROR_MESSAGE = "Неверный логин или пароль",
            PROFILE_HEADER = "Мой профиль",
            PROFILE_ITEMS_HEADER = "Личные данные";

    @Step("Открытие формы авторизации")
    public LoginForm openLoginForm() {
        loginFormButton.click();
        loginAndPasswordOption.click();

        return this;
    }

    @Step("Авторизация с помощью логина и пароля")
    public LoginForm login(String email, String password) {
        emailInput.setValue(email);
        passwordInput.setValue(password);
        submitButton.click();
        popupCloseButton.should(Condition.visible);
        popupCloseButton.click();
        return this;
    }

    @Step("Валидация авторизации при вводе некорректного логина и пароля")
    public LoginForm checkLoginErrorMessage() {
        loginErrorMessage.shouldHave(Condition.text(LOGIN_ERROR_MESSAGE));

        return this;
    }

    @Step("Проверка успешной авторизации")
    public LoginForm checkSuccessfulLogin(String email) {
        profileMenu.click();
        profileHeader.shouldHave(Condition.text(PROFILE_HEADER));
        profileDataHeader.shouldHave(Condition.text(PROFILE_ITEMS_HEADER));
        profileEmail.shouldHave(Condition.text(email));
        profileMenuItems.shouldHave(CollectionCondition.texts("Мой профиль", "Личные данные", "Заказы", "Резервы", "Бонусная карта", "Закладки", "Книжный дозор", "Выйти"));

        return this;
    }

    @Step("Выход из профиля")
    public void logout() {
        profileMenu.click();
        logoutButton.click();
        loginFormButton.should(Condition.visible);
        loginFormButton.shouldHave(Condition.text("Войти"));
    }
}
