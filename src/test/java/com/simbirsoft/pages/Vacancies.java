package com.simbirsoft.pages;

public enum Vacancies {

    CONSULTANT("Консультант с обязанностями кассира"),
    SORTER("Сотрудник отдела сортировки");

    private final String desc;

    Vacancies(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
