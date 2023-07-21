package com.simbirsoft.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private final SelenideElement
            searchInput = $("#search"),//ОТРЕДАЧИЛ
            subscribeInput = $(".js__subscribe-email"),
            subscribeButton = $(".js__subscribe-btn"),
            subscribeMessage = $(".js_subscribe_mess"),
            category = $(".content h1");

    private final ElementsCollection
            searchResults = $$(".products"),
            menu = $$(".js__nav__item"),
            productList = $$(".js__product_card"),
            productListFooter = $$(".product-card__footer"),
            navigationItems = $$(".navigation__item"),
            navigationMenuHeader = $$(".navigation__title");

    private final String SUBSCRIBE_MESSAGE = "На ваш email было отправлено письмо со ссылкой подтверждения подписки";
    private static final String URL = "https://chaconne.ru/";

    public LoginForm loginForm = new LoginForm();
    public Cart cart = new Cart();

    @Step("Открытие главной страницы")
    public MainPage openPage() {
        open(URL);
        return this;
    }

    @Step("Поиск книги по автору и названию") //ОТРЕДАЧИЛ
    public MainPage searchBook(String author, String title) {
        searchInput.setValue(author + " " + title).pressEnter();
        return this;
    }

    @Step("Проверка результатов поисковой выдачи") //ОТРЕДАЧИЛ
    public MainPage checkSearchResults(String author, String title) {
        searchResults.shouldHave(CollectionCondition.sizeGreaterThan(0));
        return this;
    }

    @Step("Введение email в форму подписки")
    public MainPage setEmailToSubscribe(String email) {
        subscribeInput.shouldHave(Condition.attribute("placeholder", "E-mail"));
        subscribeInput.setValue(email);
        subscribeButton.click();

        return this;
    }

    @Step("Проверка результата подписки по email")
    public MainPage checkEmailSubscribed() {
        subscribeInput.shouldNot(Condition.visible);
        subscribeMessage.shouldBe(Condition.visible);
        subscribeMessage.shouldHave(Condition.text(SUBSCRIBE_MESSAGE));

        return this;
    }

    @Step("Переключение на элемент меню")
    public MainPage switchToMenuItem(MenuItems item) {
        menu.findBy(Condition.text(item.getDesc())).click();

        return this;
    }

    @Step("Проверка содержимого в разделе")
    public MainPage checkMenuItem(MenuItems item) {
        category.shouldHave(Condition.text(item.getDesc()));
        productList.shouldHave(CollectionCondition.sizeGreaterThan(0));
        productListFooter.shouldHave(CollectionCondition.itemWithText("Купить"));
        navigationMenuHeader.shouldHave(CollectionCondition.itemWithText("Категории"));
        navigationItems.shouldHave(CollectionCondition.sizeGreaterThan(0));

        return this;
    }
}
