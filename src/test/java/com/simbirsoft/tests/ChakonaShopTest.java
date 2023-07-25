package com.simbirsoft.tests;

import com.simbirsoft.pages.MenuItems;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;


public class ChakonaShopTest extends TestBase {

    @Test
    @AllureId("24382")
    @Feature("Authorization")
    @Owner("Alexander Zayhikov")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "chakona", url = "https://chaconne.ru/")
    @DisplayName("Проверка сообщения об ошибке при вводе несуществующего логина и пароля при авторизации")
    @Tag("Authorization")
    void authorizationValidationTest() {
        mainPage.openPage();

        mainPage.loginForm
                .openLoginForm()
                .login(faker.internet().emailAddress(), faker.internet().password(6, 20))
                .checkLoginErrorMessage();
    }

    @Test
    @Feature("Authorization")
    @Owner("Alexander Zayhikov")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "chakona", url = "https://chaconne.ru/")
    @DisplayName("Успешная авторизация зарегистрированным пользователем")
    @Tag("Authorization")
    void successfulAuthorizationTest() {
        mainPage.openPage();

        mainPage.loginForm
                .openLoginForm()
                .login(credentials.user_login(), credentials.user_password())
                .checkSuccessfulLogin(credentials.user_login())
                .logout();
    }

    @CsvSource({
            "Роулинг, Гарри Поттер",
            "Толкин, Властелин колец"
    })
    @ParameterizedTest(name = "{0}")
    @Feature("Search")
    @Owner("Alexander Zayhikov")
    @Severity(SeverityLevel.NORMAL)
    @Link(name = "chakona", url = "https://chaconne.ru/")
    @DisplayName("Проверка корректной выдачи результатов поиска книги: ")
    @Tag("Search")
    void bookSearchTest(String author, String title) {
        mainPage
                .openPage()
                .searchBook(author, title)
                .checkSearchResults(author, title);
    }

    @ValueSource(strings = {"Пазлы", "Настенные карты"})
    @ParameterizedTest(name = "{0}")
    @Feature("Search")
    @Owner("Alexander Zayhikov")
    @Severity(SeverityLevel.NORMAL)
    @Link(name = "chakona", url = "https://chaconne.ru/")
    @DisplayName("Проверка поиска товара по категориям: ")
    @Tag("Search")
    void categorySearch(String category) {
        mainPage
                .openPage()
                .searchCategory(category)
                .checkSearchResults(category);
    }

    @Test
    @Feature("Search")
    @Owner("Alexander Zayhikov")
    @Severity(SeverityLevel.MINOR)
    @Link(name = "chakona", url = "https://chaconne.ru/")
    @DisplayName("Проверка списка магазинов по городам ")
    @Tag("Search")
    void bookStoreSearch() {
        bookStores.openPage();
        bookStores.checkStores();
    }

    @Test
    @Feature("Order")
    @Owner("Alexander Zayhikov")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "chakona", url = "https://chaconne.ru/")
    @DisplayName("Успешное оформление заказа на книгу")
    @Tag("Cart")
    void addAndRemoveBookToCart() {
        mainPage.openPage();
        mainPage.loginForm
                .openLoginForm()
                .login(credentials.user_login(), credentials.user_password());
        mainPage.cart
                .addBookToCart("Скотный двор")
                .openCart()
                .checkBookInCart("Скотный двор")
                .orderBook()
                .deleteBookFromCart()
                .checkEmptyCart();
        mainPage.cart.accountForLogout();
        mainPage.loginForm.logout();
    }

    @EnumSource(value = MenuItems.class)
    @ParameterizedTest(name = "Проверка на наличие содержимого в разделе: {0}")
    @Feature("Menu")
    @Owner("Alexander Zayhikov")
    @Severity(SeverityLevel.NORMAL)
    @Link(name = "chakona", url = "https://chaconne.ru/")
    @Tag("Menu-Items")
    void checkMenuItemsContent(MenuItems item) {
        mainPage
                .openPage()
                .checkMenuItem(item);
    }

    @Test
    @Feature("Catalog")
    @Owner("Alexander Zayhikov")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "chakona", url = "https://chaconne.ru/")
    @DisplayName("Проверка перехода по разделам каталога главной страницы")
    @Tag("Menu-Items")
    void takeCatalogElementsHierarchy() {
        mainPage.openPage();
        mainPage.switchToCatalogElements();
    }
}
