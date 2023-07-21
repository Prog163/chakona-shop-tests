package com.simbirsoft.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class BookStores {

    private final SelenideElement
            cityHeader = $(".container h1"),
            storeListButton = $(".js__shop__title");

    private final ElementsCollection
            bookStoreCity = $$(".js__alphabet-container .shops li"),
            breadCrumbs = $$(".breadcrumbs__item"),
            storeList = $$(".js__shop-list-item");

    public static final String URL = "https://chaconne.ru/info/shops.php";

    @Step("Открытие страницы с адресами магазинов")
    public void openPage() {
        open(URL);
    }

    @Step("Поиск магазина в городе")
    public String findStoresInCity(String city) {
        String storesCount = bookStoreCity.findBy(Condition.text(city)).$("span").getOwnText();
        bookStoreCity.findBy(Condition.text(city)).click();

        breadCrumbs.shouldHave(CollectionCondition.itemWithText(city));
        cityHeader.shouldHave(Condition.text("Книжные магазины в городе Город " + city));
        return storesCount;
    }

    @Step("Проверка количества магазинов в городе")
    public void checkStoresCount(String expectedCount) {
        storeListButton.click();
        storeList.shouldHave(CollectionCondition.sizeGreaterThan(0));
        storeList.shouldHave(CollectionCondition.size(Integer.parseInt(expectedCount)));
    }
}
