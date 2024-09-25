package com.urise.webapp.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

import static com.urise.webapp.util.DateUtil.NOW;
import static com.urise.webapp.util.DateUtil.of;

public class Period {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;
    private final String content;

    public Period(int startYear, Month startMonth, String title, String description) {
        this(of(startYear, startMonth), NOW, title, description);
    }

    public Period(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
        this(of(startYear, startMonth), of(endYear, endMonth), title, description);
    }

    public Period(LocalDate startDate, LocalDate endDate, String title, String content) {
        Objects.requireNonNull(startDate, "startDate cannot be null");
        Objects.requireNonNull(endDate, "endDate cannot be null");
        Objects.requireNonNull(title, "title cannot be null");
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.content = content;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Period{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period that = (Period) o;
        if (!startDate.equals((that.startDate))) return false;
        if (!endDate.equals((that.endDate))) return false;
        if (!title.equals((that.title))) return false;
        return content != null ? content.equals(that.content) : that.content == null;
    }

    @Override
    public int hashCode() {
        int result = startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
