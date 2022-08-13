package com.simbirsoft.tests;

import com.github.javafaker.Faker;
import com.simbirsoft.components.MenuItems;
import com.simbirsoft.components.Vacancies;
import com.simbirsoft.pages.BookStores;
import com.simbirsoft.pages.DiscountPage;
import com.simbirsoft.pages.JobsPage;
import com.simbirsoft.pages.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;


public class ChitayGorodTest extends TestBase {

    MainPage mainPage = new MainPage();
    BookStores bookStores = new BookStores();
    DiscountPage discountPage = new DiscountPage();
    JobsPage jobsPage = new JobsPage();
    static Faker faker = new Faker();

    @Test
    @DisplayName("Проверка сообщения об ошибке при вводе несуществующего логина и пароля при авторизации")
    void authorizationValidationTest() {
        mainPage.openPage();

        mainPage.loginForm.openLoginForm();

        mainPage.loginForm.login(faker.internet().emailAddress(), faker.internet().password(6, 20));

        mainPage.loginForm.checkLoginErrorMessage();
    }

    @Test
    @DisplayName("Успешная авторизация зарегистрированным пользователем")
    void successfulAuthorizationTest() {
        mainPage.openPage();

        mainPage.loginForm.openLoginForm();

        mainPage.loginForm.login("dankoffalexander@gmail.com", "Chitay2022");

        mainPage.loginForm.checkSuccessfulLogin("Александр", "dankoffalexander@gmail.com");

        mainPage.loginForm.logout();
    }

    @CsvSource({
            "Кинг С., Оно",
            "Роулинг Дж., Гарри Поттер и философский камень",
            "Оруэлл Д., 1984",
            "Толкин Дж., Властелин колец"
    })
    @ParameterizedTest(name = "{0}")
    @DisplayName("Проверка корректной выдачи результатов поиска книги: ")
    void bookSearchTest(String author, String title) {
        mainPage.openPage();

        mainPage.searchBook(author, title);

        mainPage.checkSearchResults(author, title);
    }

    @ValueSource(strings = {"Челябинск", "Москва", "Казань", "Санкт-Петербург"})
    @ParameterizedTest(name = "{0}")
    @DisplayName("Проверка поиска магазинов в городе: ")
    void bookStoreSearch(String city) {
        bookStores.openPage();

        String storesCount = bookStores.findStoresInCity(city);

        bookStores.checkStoresCount(storesCount);

    }

    @Test
    @DisplayName("Успешное оформление заказа на книгу")
    void addAndRemoveBookToCart() {
        mainPage.openPage();

        mainPage.loginForm.openLoginForm();

        mainPage.loginForm.login("dankoffalexander@gmail.com", "Chitay2022");

        mainPage.cart.addBookToCart("Гарри Поттер и кубок огня");

        mainPage.cart.openCart();

        mainPage.cart.checkBookInCart("Гарри Поттер и кубок огня");

        mainPage.cart.orderBook();

        mainPage.cart.deleteBookFromCart();

        mainPage.cart.checkEmptyCart();

        mainPage.loginForm.logout();
    }

    @Test
    @DisplayName("Проверка сообщений об ошибке при вводе некорректного номера бонусной карты")
    void checkDiscountCardValidation() {
        discountPage.openPage();

        mainPage.loginForm.openLoginForm();

        mainPage.loginForm.login("dankoffalexander@gmail.com", "Chitay2022");

        discountPage.openDiscountCardInputForm();

        discountPage.validateLengthCardNumber(faker.number().digits(12));

        discountPage.validateWrongCardNumber(faker.number().digits(13));

        mainPage.loginForm.logout();
    }

    @Test
    @DisplayName("Проверка успешной email-подписки на новости компании")
    void subscribeWithEmailTest() {
        mainPage.openPage();

        mainPage.setEmailToSubscribe(faker.internet().emailAddress());

        mainPage.checkEmailSubscribed();

    }

    @EnumSource(value = Vacancies.class)
    @ParameterizedTest(name = "{0}")
    @DisplayName("Проверка наличия актуальных вакансий в области: ")
    void searchJobTest(Vacancies type) {
        jobsPage.openPage();

        jobsPage.openVacancies();

        jobsPage.switchToJobType(type);

        jobsPage.checkVacanciesInJobType(type);

        jobsPage.clearJobType();

    }

    @EnumSource(value = MenuItems.class)
    @ParameterizedTest(name = "Проверка на наличие содержимого в разделе: {0}")
    void checkMenuItemsContent(MenuItems item) {
        mainPage.openPage();

        mainPage.switchToMenuItem(item);

        mainPage.checkMenuItem(item);

    }
}
