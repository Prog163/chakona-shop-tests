package com.simbirsoft.pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class JobsPage {

    private final SelenideElement
            vacanciesButton =  $$(".header__link").findBy(Condition.text("Вакансии")),
            searchButton = $(".button.search"),
            clearButton = $(".button.clear");

    private final ElementsCollection
            jobList =  $$(".vacancy__panel"),
            jobTypes = $$(".filter_category .control--checkbox"),
            vacancyType = $$(".vacancy__place");

    private static final String URL = "https://job.chitai-gorod.ru/";

    @Step("Открытие страницы с вакансиями")
    public JobsPage openPage() {
        open(URL);

        return this;
    }

    @Step("Открытие списка вакансий")
    public JobsPage openVacancies() {
        vacanciesButton.click();

        return this;
    }

    @Step("Переключение между специальностями")
    public JobsPage switchToJobType(Vacancies type) {
        jobTypes.findBy(Condition.text(type.getDesc())).click();
        searchButton.click();

        return this;
    }

    @Step("Проверка списка вакансий")
    public JobsPage checkVacanciesInJobType(Vacancies type) {
        jobList.shouldHave(CollectionCondition.sizeGreaterThan(0));
        vacancyType.shouldHave(CollectionCondition.itemWithText(type.getDesc()));

        return this;
    }

    @Step("Очистка фильтра поиска по специальностям")
    public JobsPage clearJobType() {
        clearButton.click();

        return this;
    }
}
