package com.simbirsoft.components;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Cart {

    private final SelenideElement
            searchInput = $(".search-form__input"),
            addToCardButton = $(".trade-container .js__product_card_button"),
            cardItemsCount = $(".js__basket_count"),
            anotherCardItemsCount = $(".js__item-counter__value"),
            cardLink = $$(".basket__item-title").findBy(Condition.text("Корзина")),
            cardBookTitle = $(".basket-item__main"),
            purchaseButton = $(".basket__btn-buy"),
            orderPageHeader = $("#order-page h1");

    private final ElementsCollection
            bookTitles = $$(".product-card__info .product-card__title"),
            cardItemsList = $$(".basket-item.js__basket_item.js_product"),
            orderStepsList = $$(".titlebox__content .titlebox__title");

    private final String
            ORDER_HEADER_MESSAGE = "Оформление заказа",
            DELETE_ITEM_MESSAGE = "Товар удален из корзины.";

    @Step("Добавление книги в корзину")
    public void addBookToCart(String title) {
        searchInput.setValue(title).pressEnter();
        bookTitles.findBy(Condition.exactText(title)).click();
        addToCardButton.click();

        cardItemsCount.shouldHave(Condition.text("1"));
    }

    @Step("Открытие корзины")
    public void openCart() {
        cardLink.click();
    }

    @Step("Проверка наличия добавленной книги в корзине")
    public void checkBookInCart(String title) {
        anotherCardItemsCount.shouldHave(Condition.attribute("value", "1"));
        cardItemsList.shouldHave(CollectionCondition.sizeGreaterThan(0));
        cardBookTitle.shouldHave(Condition.text(title));
        purchaseButton.shouldBe(Condition.enabled);
    }

    @Step("Оформление заказа")
    public void orderBook()  {
        purchaseButton.click();

        orderPageHeader.shouldHave(Condition.text(ORDER_HEADER_MESSAGE));
        orderStepsList.shouldHave(CollectionCondition.texts("Доставка в", "Оплата", "Контактные данные", "Подтверждение"));
    }

    @Step("Удаление книги из корзины")
    public void deleteBookFromCart() {
        cardLink.click();

        $(".js__delete-item-button").click();
    }

    @Step("Проверка корзины на отсутствие элементов")
    public void checkEmptyCart() {
        $(".js_basket_remove_text").shouldHave(Condition.text(DELETE_ITEM_MESSAGE));
        cardItemsCount.shouldHave(Condition.text("0"));
    }
}
