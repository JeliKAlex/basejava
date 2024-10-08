package com.urise.webapp.model;

import java.time.Month;


public class ResumeTestData {
    public static Resume fillData(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.setContacts(ContactType.MAIL, "mail1@ya.ru");
        resume.setContacts(ContactType.PHONE, "11111");
        resume.setSections(SectionType.OBJECTIVE, new StringSection("Objective1"));
        resume.setSections(SectionType.PERSONAL, new StringSection("Personal data"));
        resume.setSections(SectionType.ACHIEVEMENT, new ListSection("Achievement1", "Achievement2", "Achievement3"));
        resume.setSections(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
        resume.setSections(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization1", "http://Organization1.ru",
                                new Period(2005, Month.JANUARY, "Period1", "content1"),
                                new Period(2001, Month.MARCH, 2005, Month.JANUARY, "Period2", "content2"))));
        resume.setSections(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Institute", null,
                                new Period(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", "IO Faculty"),
                                new Period(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT faculty")),
                        new Organization("Organization12", "http://Organization12.ru")));
        resume.setSections(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization2", "http://Organization2.ru",
                                new Period(2015, Month.JANUARY, "Period1", "content1"))));
        return resume;
    }

}
