package com.urise.webapp.model;

public enum ContactType {
    PHONE("Телефон"),
    MESSENGER("Мессенджер"),
    MAIL("Почта"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль StackOverFlow");

    private final String title;


    ContactType(String title) {
        this.title = title;
    }


    public String title() {
        return title;
    }


}
