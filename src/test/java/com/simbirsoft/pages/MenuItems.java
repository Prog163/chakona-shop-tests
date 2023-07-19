package com.simbirsoft.pages;

public enum MenuItems {

    OFFER("Родителям и школам"),
    DELIVERY("Доставка"),
    SHOPS("Наши магазины"),
    SALE("Скидки"),
    STOCK("Акции"),
    OFFICE("Мой кабинет");

    private final String desc;

    MenuItems(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
