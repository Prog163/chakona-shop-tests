package com.simbirsoft.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    static void setup() {

        Configuration.startMaximized = true;
        Configuration.baseUrl = "https://www.chitai-gorod.ru";
    }
}
