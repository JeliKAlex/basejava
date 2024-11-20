<%@ page import="com.urise.webapp.model.Resume" %>
<%@ page import="java.util.List" %>
<%@ page import="com.urise.webapp.model.ContactType" %>
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
    <title>Меню</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<div class="listAdd">
    <a href="resume?action=add"><img src="img/add.png"></a>
</div>
<div class="listMainTable">
    <table border="none" cellpadding="8" cellspacing="0">
        <tr>
            <th>Имя</th>
            <th>Контакты</th>
            <th class="imgColumn">Редактировать</th>
            <th class="imgColumn">Удалить</th>
        </tr>
        <c:forEach items="${resumes}" var="resume">
            <jsp:useBean id="resume" type="com.urise.webapp.model.Resume"/>
            <tr>
                <td class="nameColumn"><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a></td>
                <td><%=ContactType.MAIL.toHtmlString(resume.getContact(ContactType.MAIL))%></td>
                <td class="imgColumn"><a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></td>
                <td class="imgColumn"><a href="resume?uuid=${resume.uuid}&action=delete"><img src="img/delete.png"></a></td>
            </tr>
        </c:forEach>
    </table>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
