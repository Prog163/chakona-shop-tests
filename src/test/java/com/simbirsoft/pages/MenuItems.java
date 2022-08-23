package com.simbirsoft.pages;

public enum MenuItems {

    BOOKS("Книги"),
    STATIONERY("Канцтовары"),
    SOUVENIRS("Сувениры"),
    TOYS("Игры и игрушки"),
    CREATION("Творчество"),
    SALE("Распродажа");

    private final String desc;

    MenuItems(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
