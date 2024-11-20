package com.urise.webapp.model;

public enum ContactType {
    PHONE("Телефон"),
    MESSENGER("Мессенджер"),
    SKYPE("Skype") {
        @Override
        public String toHtmlString0(String value) {
            return getTitle() + ": " + toLink("skype:" + value, value);
        }
    },
    MAIL("Почта") {
        @Override
        public String toHtmlString0(String value) {
            return getTitle() + ": " + toLink("mailto:" + value, value);
        }
    },
    LINKEDIN("Профиль LinkedIn") {
        @Override
        public String toHtmlString0(String value) {
            return toLink(value);
        }
    },

    GITHUB("Профиль GitHub") {
        @Override
        public String toHtmlString0(String value) {
            return toLink(value);
        }
    },
    STACKOVERFLOW("Профиль StackOverFlow") {
        @Override
        public String toHtmlString0(String value) {
            return toLink(value);
        }
    };

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

    public String toLink(String href) {
        return toLink(href, title);
    }

    public static String toLink(String href, String title) {
        return "<a href='" + href + "'>" + title + "</a>";
    }
}
