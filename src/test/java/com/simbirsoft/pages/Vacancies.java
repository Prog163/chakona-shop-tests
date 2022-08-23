package com.simbirsoft.pages;

public enum Vacancies {

    IT("IT"),
    ANALYTICS("Аналитика и управление данными"),
    MARKETING("Маркетинг и реклама"),
    TRADE("Розничная торговля"),
    STOREHOUSE("Склад"),
    FINANCES("Финансы и бухгалтерия");

    private final String desc;

    Vacancies(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
