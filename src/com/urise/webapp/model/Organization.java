package com.urise.webapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Organization {
    private final Link homePage;
    private final List<Period> periods;

    public Organization(String name, String url, Period... positions) {
        this(new Link(name, url), Arrays.asList(positions));
    }

    public Organization(Link homePage, List<Period> period) {
        Objects.requireNonNull(homePage, "homePage cannot be null");
        Objects.requireNonNull(period, "startPeriod cannot be null");
        this.homePage = homePage;
        this.periods = period;
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<Period> getPeriod() {
        return periods;
    }

    @Override
    public String toString() {
        return "OrganizationSection{" +
                "homePage=" + homePage +
                ", period=" + periods +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return homePage.equals(that.homePage) && periods.equals(that.periods);
    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + periods.hashCode();
        return result;
    }
}
