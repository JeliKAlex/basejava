package com.urise.webapp.model;

public enum ContactType {
    PHONE("Телефон"),
    MESSENGER("Мессенджер"),
    SKYPE("Skype") {
        @Override
        public String toHtmlString0(String value) {
            return "<a href='skype:" + value + "'>" + value + "</a>";
        }
    },
    MAIL("Почта") {
        @Override
        public String toHtmlString0(String value) {
            return "<a href='mail:" + value + "'>" + value + "</a>";
        }
    },
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль StackOverFlow");

    private final String title;


    ContactType(String title) {
        this.title = title;
    }


    public String getTitle() {
        return title;
    }

    public String toHtmlString0(String value) {
        return title + ": " + value;
    }

    public String toHtmlString(String value) {
        return (value == null) ? "" : toHtmlString0(value);
    }
}
