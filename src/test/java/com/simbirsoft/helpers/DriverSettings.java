package com.simbirsoft.helpers;

import com.codeborne.selenide.Configuration;
import com.simbirsoft.config.CredentialConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import static java.lang.String.format;

public class DriverSettings {

    public static CredentialConfig credentials = ConfigFactory.create(CredentialConfig.class);

    public static void configure() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        String login = credentials.remote_login();
        String password = credentials.remote_password();
        String remoteURL = System.getProperty("remoteUrl");
        String browserName = System.getProperty("browserName", "chrome");
        String browserSize = System.getProperty("browserSize", "1600x900");
        String browserVersion = System.getProperty("browserVersion");

        Configuration.browserCapabilities = capabilities;
        Configuration.browser = browserName;
        Configuration.browserSize = browserSize;
        Configuration.browserVersion = browserVersion;
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://chaconne.ru/";

        if (System.getProperty("remoteUrl") != null) {
            Configuration.remote = format("https://%s:%s@%s", login, password, remoteURL);
        }
    }
}
