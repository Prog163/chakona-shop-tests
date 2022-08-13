package com.simbirsoft.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.simbirsoft.components.Cart;
import com.simbirsoft.components.LoginForm;
import com.simbirsoft.components.MenuItems;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private final SelenideElement
            searchInput = $(".search-form__input"),
            searchResultHeader = $(".js__search_info h1"),
            subscribeInput = $(".js__subscribe-email"),
            subscribeButton = $(".js__subscribe-btn"),
            subscribeMessage = $(".js_subscribe_mess"),
            category = $(".content h1"),
            navigationMenuHeader = $(".navigation__title");

    private final ElementsCollection
            searchResults = $$(".product-card.js_product.js__product_card"),
            bookTitles = $$(".product-card__info .product-card__title"),
            bookAuthors = $$(".product-card__info .product-card__author"),
            menu = $$(".js__nav__item"),
            productList = $$(".js__product_card"),
            productListFooter = $$(".product-card__footer"),
            navigationItems = $$(".navigation__item");

    private final String SUBSCRIBE_MESSAGE = "На ваш email было отправлено письмо со ссылкой подтверждения подписки";
    private static final String URL = "https://www.chitai-gorod.ru/";

    public LoginForm loginForm = new LoginForm();
    public Cart cart = new Cart();

    @Step("Открытие главной страницы")
    public void openPage() {
        open(URL);
    }

    @Step("Поиск книги по автору и названию")
    public void searchBook(String author, String title) {
        searchInput.setValue(author + " " + title).pressEnter();

        searchResultHeader.shouldHave(Condition.text("Результаты поиска  «" + author + " " + title + "»"));
    }

    @Step("Проверка результатов поисковой выдачи")
    public void checkSearchResults(String author, String title) {
        searchResults.shouldHave(CollectionCondition.sizeGreaterThan(0));
        bookTitles.shouldHave(CollectionCondition.itemWithText(title));
        bookAuthors.shouldHave(CollectionCondition.itemWithText(author));
    }

    @Step("Введение email в форму подписки")
    public void setEmailToSubscribe(String email) {
        subscribeInput.shouldHave(Condition.attribute("placeholder", "E-mail"));

        subscribeInput.setValue(email);
        subscribeButton.click();
    }

    @Step("Проверка результата подписки по email")
    public void checkEmailSubscribed() {
        subscribeInput.shouldNot(Condition.visible);
        subscribeMessage.shouldBe(Condition.visible);
        subscribeMessage.shouldHave(Condition.text(SUBSCRIBE_MESSAGE));
    }

    @Step("Переключение на элемент меню")
    public void switchToMenuItem(MenuItems item) {
        menu.findBy(Condition.text(item.getDesc())).click();
    }

    @Step("Проверка содержимого в разделе")
    public void checkMenuItem(MenuItems item) {
        category.shouldHave(Condition.text(item.getDesc()));
        productList.shouldHave(CollectionCondition.sizeGreaterThan(0));
        productListFooter.shouldHave(CollectionCondition.itemWithText("Купить"));
        navigationMenuHeader.shouldHave(Condition.text("Категории"));
        navigationItems.shouldHave(CollectionCondition.sizeGreaterThan(0));
    }
}
