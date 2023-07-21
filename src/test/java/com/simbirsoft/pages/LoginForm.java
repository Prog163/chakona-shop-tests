package com.simbirsoft.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class LoginForm {

    private final SelenideElement
            emailInput = $(By.name("email")),
            passwordInput = $(By.name("pass")),
            submitButton = $(".btn-primary"),
            loginErrorMessage = $(".alert-danger"),
            profileOrder = $(".alert"),
            logoutButton = $$(".nav-tabs").findBy(Condition.text("Выход"));

    private final ElementsCollection
            profileMenuItems = $$(".nav-tabs");

    private final String
            LOGIN_ERROR_MESSAGE = "Такого пользователя нет в базе данных!",
            PROFILE_ORDER = "Заказов не найдено";

    @Step("Открытие формы авторизации")
    public LoginForm openLoginForm() {
        open("https://chaconne.ru/enter.php");
        return this;
    }

    @Step("Авторизация с помощью логина и пароля")
    public LoginForm login(String email, String password) {
        emailInput.setValue(email);
        passwordInput.setValue(password);
        submitButton.click();
        return this;
    }

    @Step("Валидация авторизации при вводе некорректного логина и пароля")
    public LoginForm checkLoginErrorMessage() {
        loginErrorMessage.shouldHave(Condition.text(LOGIN_ERROR_MESSAGE));
        return this;
    }

    @Step("Проверка успешной авторизации")
    public LoginForm checkSuccessfulLogin(String email) {
        profileOrder.shouldHave(Condition.text(PROFILE_ORDER));
        profileMenuItems.shouldHave(CollectionCondition
                .texts(
                "МОИ ЗАКАЗЫ " +
                "ОТЛОЖЕННЫЕ В ФИЛИАЛЕ " +
                "ЗАКЛАДКИ " +
                "УВЕДОМЛЕНИЯ " +
                "КАРТА КЛИЕНТА " +
                "НАСТРОЙКИ " +
                "ВЫХОД"));
        return this;
    }

    @Step("Выход из профиля")
    public void logout() {
        logoutButton.click();
    }
}
