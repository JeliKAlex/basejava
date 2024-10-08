package com.urise.webapp.model;

import com.urise.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

import static com.urise.webapp.util.DateUtil.NOW;
import static com.urise.webapp.util.DateUtil.of;

@XmlAccessorType(XmlAccessType.FIELD)
public class Period implements Serializable {
    private static final long SERIAL_VERSION_UUID = 1L;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate startDate;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate endDate;
    private String title;
    private String content;

    public Period() {
    }

    public Period(int startYear, Month startMonth, String title, String content) {
        this(of(startYear, startMonth), NOW, title, content);
    }

    public Period(int startYear, Month startMonth, int endYear, Month endMonth, String title, String content) {
        this(of(startYear, startMonth), of(endYear, endMonth), title, content);
    }

    public Period(LocalDate startDate, LocalDate endDate, String title, String content) {
        Objects.requireNonNull(startDate, "startDate cannot be null");
        Objects.requireNonNull(endDate, "endDate cannot be null");
        Objects.requireNonNull(title, "title cannot be null");
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.content = content == null ? "" : content;
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
        Period period = (Period) o;
        return Objects.equals(startDate, period.startDate) &&
                Objects.equals(endDate, period.endDate) &&
                Objects.equals(title, period.title) &&
                Objects.equals(content, period.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, title, content);
    }
}
