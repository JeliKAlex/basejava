package com.urise.webapp.model;

import java.util.Objects;

public class StringSection extends Section {
    private static final long SERIAL_VERSION_UUID = 1L;
    private String content;

    public StringSection() {
    }

    public StringSection(String content) {
        Objects.requireNonNull(content, "content cannot be null");
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringSection that = (StringSection) o;
        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }
}
