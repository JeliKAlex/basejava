package com.urise.webapp.model;

import java.time.Month;


public class ResumeTestData {
    public static Resume fillData(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.setContact(ContactType.MAIL, "mail1@ya.ru");
        resume.setContact(ContactType.PHONE, "11111");
        resume.setSection(SectionType.OBJECTIVE, new StringSection("Objective1"));
        resume.setSection(SectionType.PERSONAL, new StringSection("Personal data"));
        resume.setSection(SectionType.ACHIEVEMENT, new ListSection("Achievement1", "Achievement2", "Achievement3"));
        resume.setSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
        resume.setSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization1", "http://Organization1.ru",
                                new Period(2005, Month.JANUARY, "Period1", "content1"),
                                new Period(2001, Month.MARCH, 2005, Month.JANUARY, "Period2", "content2"))));
        resume.setSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Institute", null,
                                new Period(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Period(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT faculty")),
                        new Organization("Organization12", "http://Organization12.ru")));
        resume.setSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization2", "http://Organization2.ru",
                                new Period(2015, Month.JANUARY, "Period1", "content1"))));
        return resume;
    }

}
