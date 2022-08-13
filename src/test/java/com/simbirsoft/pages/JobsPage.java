package com.simbirsoft.pages;

import com.codeborne.selenide.*;
import com.simbirsoft.components.Vacancies;
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
    public void openPage() {
        open(URL);
    }

    @Step("Открытие списка вакансий")
    public void openVacancies() {
        vacanciesButton.click();
    }

    @Step("Переключение между специальностями")
    public void switchToJobType(Vacancies type) {
        jobTypes.findBy(Condition.text(type.getDesc())).click();
        searchButton.click();
    }

    @Step("Проверка списка вакансий")
    public void checkVacanciesInJobType(Vacancies type) {
        jobList.shouldHave(CollectionCondition.sizeGreaterThan(0));
        vacancyType.shouldHave(CollectionCondition.itemWithText(type.getDesc()));
    }

    @Step("Очистка фильтра поиска по специальностям")
    public void clearJobType() {
        clearButton.click();
    }
}
