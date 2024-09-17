package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class Organization {
    private final Link homePage;
    private final List<Period> period;

    public Organization(Link homePage, List<Period> period) {
        Objects.requireNonNull(homePage, "homePage cannot be null");
        Objects.requireNonNull(period, "startPeriod cannot be null");
        this.homePage = homePage;
        this.period = period;
    }

    @Override
    public String toString() {
        return "OrganizationSection{" +
                "homePage=" + homePage +
                ", period=" + period +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return homePage.equals(that.homePage) && period.equals(that.period);
    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + period.hashCode();
        return result;
    }
}
