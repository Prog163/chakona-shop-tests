package com.simbirsoft.tests;

import com.simbirsoft.pages.MenuItems;
import com.simbirsoft.pages.Vacancies;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;


public class ChitayGorodTest extends TestBase {

    @Test
    @Feature("Authorization")
    @Owner("Alexander Dankov")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "chitay-gorod", url= "https://www.chitai-gorod.ru/")
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
    @Owner("Alexander Dankov")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "chitay-gorod", url= "https://www.chitai-gorod.ru/")
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
            "Кинг С., Оно",
            "Роулинг Дж., Гарри Поттер и философский камень",
            "Оруэлл Д., 1984",
            "Толкин Дж., Властелин колец"
    })
    @ParameterizedTest(name = "{0}")
    @Feature("Search")
    @Owner("Alexander Dankov")
    @Severity(SeverityLevel.NORMAL)
    @Link(name = "chitay-gorod", url= "https://www.chitai-gorod.ru/")
    @DisplayName("Проверка корректной выдачи результатов поиска книги: ")
    @Tag("Search")
    void bookSearchTest(String author, String title) {
        mainPage
                .openPage()
                .searchBook(author, title)
                .checkSearchResults(author, title);
    }

    @ValueSource(strings = {"Челябинск", "Москва", "Казань", "Санкт-Петербург"})
    @ParameterizedTest(name = "{0}")
    @Feature("Search")
    @Owner("Alexander Dankov")
    @Severity(SeverityLevel.MINOR)
    @Link(name = "chitay-gorod", url= "https://www.chitai-gorod.ru/")
    @DisplayName("Проверка поиска магазинов в городе: ")
    @Tag("Search")
    void bookStoreSearch(String city) {
        bookStores.openPage();

        String storesCount = bookStores.findStoresInCity(city);

        bookStores.checkStoresCount(storesCount);
    }

    @Test
    @Feature("Order")
    @Owner("Alexander Dankov")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "chitay-gorod", url= "https://www.chitai-gorod.ru/")
    @DisplayName("Успешное оформление заказа на книгу")
    @Tag("Cart")
    void addAndRemoveBookToCart() {
        mainPage.openPage();

        mainPage.loginForm
                    .openLoginForm()
                    .login(credentials.user_login(), credentials.user_password());

        mainPage.cart
                    .addBookToCart("Гарри Поттер и кубок огня")
                    .openCart()
                    .checkBookInCart("Гарри Поттер и кубок огня")
                    .orderBook()
                    .deleteBookFromCart()
                    .checkEmptyCart();

        mainPage.loginForm.logout();
    }

    @Test
    @Feature("Discount card")
    @Owner("Alexander Dankov")
    @Severity(SeverityLevel.MINOR)
    @Link(name = "chitay-gorod", url= "https://www.chitai-gorod.ru/")
    @DisplayName("Проверка сообщений об ошибке при вводе некорректного номера бонусной карты")
    @Tag("Discount-card")
    void checkDiscountCardValidation() {
        discountPage.openPage();

        mainPage.loginForm
                    .openLoginForm()
                    .login(credentials.user_login(), credentials.user_password());

        discountPage
                .openDiscountCardInputForm()
                .validateLengthCardNumber(faker.number().digits(12))
                .validateWrongCardNumber(faker.number().digits(13));

        mainPage.loginForm.logout();
    }

    @Test
    @Feature("Subscribe")
    @Owner("Alexander Dankov")
    @Severity(SeverityLevel.MINOR)
    @Link(name = "chitay-gorod", url= "https://www.chitai-gorod.ru/")
    @DisplayName("Проверка успешной email-подписки на новости компании")
    @Tag("Subscribe")
    void subscribeWithEmailTest() {
        mainPage
                .openPage()
                .setEmailToSubscribe(faker.internet().emailAddress())
                .checkEmailSubscribed();
    }

    @EnumSource(value = Vacancies.class)
    @ParameterizedTest(name = "{0}")
    @Feature("Job search")
    @Owner("Alexander Dankov")
    @Severity(SeverityLevel.NORMAL)
    @Link(name = "chitay-gorod", url= "https://www.chitai-gorod.ru/")
    @DisplayName("Проверка наличия актуальных вакансий в области: ")
    @Tag("Vacancies")
    void searchJobTest(Vacancies type) {
        jobsPage
                .openPage()
                .openVacancies()
                .switchToJobType(type)
                .checkVacanciesInJobType(type)
                .clearJobType();
    }

    @EnumSource(value = MenuItems.class)
    @ParameterizedTest(name = "Проверка на наличие содержимого в разделе: {0}")
    @Feature("Menu")
    @Owner("Alexander Dankov")
    @Severity(SeverityLevel.NORMAL)
    @Link(name = "chitay-gorod", url= "https://www.chitai-gorod.ru/")
    @Tag("Menu-Items")
    void checkMenuItemsContent(MenuItems item) {
        mainPage
                .openPage()
                .switchToMenuItem(item)
                .checkMenuItem(item);
    }
}
