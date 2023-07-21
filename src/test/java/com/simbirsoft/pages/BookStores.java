package com.simbirsoft.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class BookStores {

    private final SelenideElement
            storeListButton = $(".page_content");

    private final ElementsCollection
            storeList = $$(".shops");

    public static final String URL = "https://chaconne.ru/info/shops.php";

    @Step("Открытие страницы с адресами магазинов")
    public void openPage() {
        open(URL);
    }

    @Step("Проверка количества магазинов в городе")
    public BookStores checkStores() {
        storeListButton.shouldHave(Condition.text("Адреса магазинов Чакона"));
        storeList.shouldHave(CollectionCondition
                .texts(
                        "САМАРА " +
                                "ТОЛЬЯТТИ " +
                                "СЫЗРАНЬ " +
                                "НОВОКУЙБЫШЕВСК " +
                                "ЖИГУЛЁВСК " +
                                "БЕЗЕНЧУК"));
        return this;
    }
}

