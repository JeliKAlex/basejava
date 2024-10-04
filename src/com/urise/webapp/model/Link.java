package com.urise.webapp.model;

import java.io.Serializable;
import java.util.Objects;

public class Link implements Serializable {
    private static final long SERIAL_VERSION_UUID = 1L;
    String title;
    String url;

    public Link(String title, String url) {
        Objects.requireNonNull(title, "title cannot be null");
        this.title = title;
        this.url = url;
    }

    public String title() {
        return title;
    }

    public String url() {
        return url;
    }

    @Override
    public String toString() {
        return "Link{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link that = (Link) o;
        if (!title.equals((that.title))) return false;
        return url != null ? url.equals(that.url) : that.url == null;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
