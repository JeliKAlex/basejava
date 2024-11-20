package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.*;
import com.urise.webapp.storage.Storage;
import com.urise.webapp.util.DateUtil;
import com.urise.webapp.util.HtmlUtil;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.getInstance().getStorage();
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");

        final boolean isCreate = (uuid == null || uuid.length() == 0);
        Resume resume;
        if (isCreate) {
            resume = new Resume(fullName);
        } else {
            resume = storage.get(uuid);
            resume.setFullName(fullName);
        }

        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (HtmlUtil.isEmpty(value)) {
                resume.getContacts().remove(type);
            } else {
                resume.setContacts(type, value);
            }
        }
        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            String[] values = request.getParameterValues(type.name());
            if (HtmlUtil.isEmpty(value) && values.length < 2) {
                resume.getSections().remove(type);
            } else {
                switch (type) {
                    case PERSONAL, OBJECTIVE -> {
                        resume.setSections(type, new StringSection(value));
                        break;
                    }
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        resume.setSections(type, new ListSection(value.split("\n")));
                        break;
                    }
                    case EDUCATION, EXPERIENCE -> {
                        List<Organization> organizations = new ArrayList<>();
                        String[] urls = request.getParameterValues(type.name() + "url");
                        for (int i = 0; i < values.length; i++) {
                            List<Period> periods = new ArrayList<>();
                            String name = values[i];
                            if (!HtmlUtil.isEmpty(name)) {
                                String prefix = type.name() + i;
                                String[] startDate = request.getParameterValues(prefix + "startDate");
                                String[] endDate = request.getParameterValues(prefix + "endDate");
                                String[] title = request.getParameterValues(prefix + "title");
                                String[] content = request.getParameterValues(prefix + "content");
                                for (int j = 0; j < title.length; j++) {
                                    if (!HtmlUtil.isEmpty(title[j])) {
                                        periods.add(new Period(DateUtil.parse(startDate[j]), DateUtil.parse(endDate[j]),
                                                title[j], content[j]));
                                    }
                                }
                                organizations.add(new Organization(new Link(name, urls[i]), periods));
                            }
                        }
                        resume.setSections(type, new OrganizationSection(organizations));
                        break;
                    }
                }
            }
        }
        if (isCreate) {
            storage.save(resume);
        } else {
            storage.update(resume);
        }
        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");

        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        } else {
            Resume resume;
            switch (action) {
                case "delete":
                    storage.delete(uuid);
                    response.sendRedirect("resume");
                    return;
                case "view":
                    resume = storage.get(uuid);
                    break;
                case "add":
                    resume = Resume.EMPTY;
                    break;
                case "edit":
                    resume = storage.get(uuid);
                    for (SectionType type : new SectionType[]{SectionType.EXPERIENCE, SectionType.EDUCATION}) {
                        OrganizationSection section = (OrganizationSection) resume.getSection(type);
                        List<Organization> emptyFirstOrganization = new ArrayList<>();
                        emptyFirstOrganization.add(Organization.EMPTY);
                        if (section != null) {
                            for (Organization organization : section.getOrganizations()) {
                                List<Period> emptyFirstPeriod = new ArrayList<>();
                                emptyFirstPeriod.add(Period.EMPTY);
                                emptyFirstPeriod.addAll(organization.getPeriods());
                                emptyFirstOrganization.add(new Organization(organization.getHomePage(),
                                        emptyFirstPeriod));
                            }
                        }
                        resume.setSections(type, new OrganizationSection(emptyFirstOrganization));
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unknown action: " + action);
            }
            request.setAttribute("resume", resume);
            request.getRequestDispatcher(
                            "view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
                    .forward(request, response);
        }
    }
}
