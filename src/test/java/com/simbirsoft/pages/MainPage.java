package com.simbirsoft.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private final SelenideElement
            searchInput = $("#search"),
            category = $(".header-nav");

    private final ElementsCollection
            searchResults = $$(".products"),
            catalog = $$(".cat4654");

    private static final String URL = "https://chaconne.ru/";

    public LoginForm loginForm = new LoginForm();
    public Cart cart = new Cart();

    @Step("Открытие главной страницы")
    public MainPage openPage() {
        open(URL);
        return this;
    }

    @Step("Поиск книги по автору и названию")
    public MainPage searchBook(String author, String title) {
        searchInput.setValue(author + " " + title).pressEnter();
        return this;
    }

    @Step("Поиск по категории")
    public MainPage searchCategory(String category) {
        searchInput.setValue(category).pressEnter();
        return this;
    }

    @Step("Проверка результатов поисковой выдачи")
    public MainPage checkSearchResults(String author, String title) {
        searchResults.shouldHave(CollectionCondition.sizeGreaterThan(0));
        return this;
    }

    @Step("Проверка результатов поисковой выдачи")
    public MainPage checkSearchResults(String category) {
        searchResults.shouldHave(CollectionCondition.sizeGreaterThan(0));
        return this;
    }

    @Step("Переключение на элемент меню")
    public MainPage switchToCatalogElements() {
        catalog.findBy(Condition.text("Товары для творчества")).click();
        $(".cat4655").click();
        $(".cat5779").click();
        $(".cat7335").click();
        $(".catalog_page_title").shouldHave(Condition.text("Аппликация из фетра"));
        return this;
    }

    @Step("Проверка содержимого в разделе")
    public MainPage checkMenuItem(MenuItems item) {
        category.shouldHave(Condition.text(item.getDesc()));
        return this;
    }
}
