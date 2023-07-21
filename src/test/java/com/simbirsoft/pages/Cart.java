package com.simbirsoft.pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class Cart {

    private final SelenideElement
            searchInput = $("#search"),
            addToCardButton = $(".gtm_buybtn"),
            anotherCardItemsCount = $(".site_title "),
            cardLink = $$("#cart").findBy(text("В корзине ")),
            cardBookTitle = $(".title"),
            purchaseButton = $("#order-btn"),
            deleteButton = $(".cart-clear-btn"),
            approveDelete = $$("#cart_clear_btn").findBy(text("Очистить")),
            orderPageHeader = $(".site_title ");

    private final ElementsCollection
            bookTitles = $$(".title"),
            orderStepsList = $$("#order_page_navs");

    private final String
            ORDER_HEADER_MESSAGE = "Оформление заказа",
            DELETE_ITEM_MESSAGE = "Корзина пуста!";

    @Step("Добавление книги в корзину")
    public Cart addBookToCart(String title) {
        searchInput.setValue(title).pressEnter();
        bookTitles.findBy(Condition.exactText(title)).click();
        addToCardButton.click();
        return this;
    }

    @Step("Открытие корзины")
    public Cart openCart() {
        cardLink.click();
        return this;
    }

    @Step("Проверка наличия добавленной книги в корзине")
    public Cart checkBookInCart(String title) {
        anotherCardItemsCount.shouldHave(text("Ваша корзина"));
        cardBookTitle.shouldHave(text(title));
        purchaseButton.shouldBe(Condition.enabled);
        return this;
    }

    @Step("Оформление заказа")
    public Cart orderBook()  {
        purchaseButton.click();
        orderPageHeader.shouldHave(text(ORDER_HEADER_MESSAGE));
        orderStepsList.shouldHave(CollectionCondition
                .texts(
                        "1СПОСОБ ДОСТАВКИ " +
                        "2СПОСОБ ОПЛАТЫ " +
                        "3ПЕРСОНАЛЬНАЯ ИНФОРМАЦИЯ"));
        return this;
    }

    @Step("Удаление книги из корзины")
    public Cart deleteBookFromCart() {
        cardLink.click();
        deleteButton.click();
        approveDelete.click();
        return this;
    }

    @Step("Проверка корзины на отсутствие элементов")
    public Cart checkEmptyCart() {
        $(".alert-warning").shouldHave(text(DELETE_ITEM_MESSAGE));
        return this;
    }

    @Step("Переход в ЛК и выход")
    public Cart accountForLogout() {
        open("https://chaconne.ru/user/");
        return this;
    }
}
