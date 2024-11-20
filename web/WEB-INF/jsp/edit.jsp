<%@ page import="java.util.List" %>
<%@ page import="com.urise.webapp.model.*" %>
<%@ page import="com.urise.webapp.util.DateUtil" %>
<%@ page import="static java.util.Arrays.stream" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 06.11.2024
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css?v=8.0">
    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<div class="edit">
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <h1>Имя:</h1>
        <dl>
            <input type="text" name="fullName" size="50" value="${resume.fullName}">
        </dl>
        <h2>Контакты:</h2>
        <p>
            <c:forEach var="type" items="<%=ContactType.values()%>">
        <dl>
            <dt>${type.title}</dt>
            <dd><input type="text" name="${type.name()}" size="30" value="${resume.getContact(type)}"></dd>
        </dl>
        </c:forEach>
        <hr>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <c:set var="section" value="${resume.getSection(type)}"/>
            <jsp:useBean id="section" type="com.urise.webapp.model.Section"/>
            <h2>${type.title}</h2>
            <c:choose>
                <c:when test="${type=='PERSONAL'}">
                    <textarea name="${type}" cols="75" rows="5">${section}</textarea>
                </c:when>
                <c:when test="${type=='OBJECTIVE'}">
                    <input type="text" name="${type}" size="70" value="${section}">
                </c:when>
                <c:when test="${type=='ACHIEVEMENT' || type=='QUALIFICATIONS'}">
                    <textarea name="${type}" cols="75" rows="5"><%=String.join("\n",
                            ((ListSection) section).getItems())%></textarea>
                </c:when>
                <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                    <c:forEach var="organization" items="<%=((OrganizationSection) section).getOrganizations()%>"
                               varStatus="counter">
                        <dl>
                            <dt>Название учреждения:</dt>
                            <dd>
                                <input type="text" name="${type}" size="100"
                                       value="${organization.homePage.title}">
                            </dd>
                        </dl>
                        <dl>
                            <dt>Сайт учреждения:</dt>
                            <dd>
                                <input type="text" name="${type}url" size="100"
                                       value="${organization.homePage.url}">
                            </dd>
                        </dl>
                        <br>
                        <div style="margin-left: 30px">
                            <c:forEach var="period" items="${organization.periods}">
                                <jsp:useBean id="period" type="com.urise.webapp.model.Period"/>
                                <dl>
                                    <dt>Начальная дата:</dt>
                                    <dd>
                                        <input type="text" name="${type}${counter.index}startDate" size="10"
                                               value="<%=DateUtil.format(period.getStartDate())%>"
                                               placeholder="MM/yyyy">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>Конечная дата:</dt>
                                    <dd>
                                        <input type="text" name="${type}${counter.index}endDate" size="10"
                                               value="<%=DateUtil.format(period.getEndDate())%>" placeholder="MM/yyyy">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>Должность:</dt>
                                    <dd>
                                        <input type="text" name="${type}${counter.index}title" size="75"
                                               value="${period.title}">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>Описание:</dt>
                                    <dd>
                                        <textarea name="${type}${counter.index}content"
                                                  rows="5" cols="75">${period.content}</textarea>
                                    </dd>
                                </dl>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </c:when>
            </c:choose>
        </c:forEach>
        <hr>
        <button class="edit" type="submit"><img class="edit" src="img/save.png"></button>
        <button class="edit" onclick="window.history.back()" type="reset"
                title="Все несохраненные данные будут потеряны">Отменить</button>
    </form>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
