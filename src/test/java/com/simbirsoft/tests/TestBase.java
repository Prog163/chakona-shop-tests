package com.simbirsoft.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import com.simbirsoft.config.CredentialConfig;
import com.simbirsoft.helpers.Attach;
import com.simbirsoft.helpers.DriverSettings;
import com.simbirsoft.pages.BookStores;
import com.simbirsoft.pages.MainPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.simbirsoft.helpers.Attach.attachAsText;

public class TestBase extends DriverSettings {

    public static CredentialConfig credentials = ConfigFactory.create(CredentialConfig.class);

    MainPage mainPage = new MainPage();
    BookStores bookStores = new BookStores();
    static Faker faker = new Faker();

    @BeforeAll
    static void setup() {
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
        Configuration.pageLoadStrategy = "eager";

        SelenideLogger.addListener("AllureListener", new AllureSelenide());

        DriverSettings.configure();
        attachAsText("BrowserRun",System.getProperty("browserName","chrome"));
    }

    @BeforeEach
    public void setupBeforeEach() throws InterruptedException {
        if (System.getProperty("threads") != null) {
            Thread.sleep(10_000);
        }
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
