<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<title>Add Product Form</title>
    <style type="text/css">@import url("<c:url value="/css/main.css"/>");</style>
</head>
<body>

<div id="global">
    <form:form commandName="product" action="product_save" method="post">
        <fieldset>
            <legend>Add a product</legend>
            <p class="errorLine">
                <form:errors path="name" cssClass="error"/>
            </p>
            <p>
                <label for="name">*Product Name: </label>
                <form:input id="name" path="name" tabindex="1"/>
            </p>
            <p>
                <label for="description">Description: </label>
                <form:input id="description" path="description" tabindex="2"/>
            </p>
            <p class="errorLine">
                <form:errors path="price" cssClass="error"/>
            </p>
            <p>
                <label for="price">*Price: </label>
                <form:input id="price" path="price" tabindex="3"/>
            </p>
            <p class="errorLine">
                <form:errors path="productionDate" cssClass="error"/>
            </p>
            <p>
                <label for="productionDate">*Production Date: </label>
                <form:input id="productionDate" path="productionDate" tabindex="4"/>
            </p>
            <p id="buttons">
                <input id="reset" type="reset" tabindex="5">
                <input id="submit" type="submit" tabindex="6"
                       value="Add Product">
            </p>
        </fieldset>
    </form:form>
</div>

<%--<div id="global">
<c:if test="${requestScope.errors != null}">
        <p id="errors">
        Error(s)!
        <ul>
        <c:forEach var="error" items="${requestScope.errors}">
            <li>${error}</li>
        </c:forEach>
        </ul>
        </p>
    &lt;%&ndash;El表达式可以显示对象中的数据哦。&ndash;%&gt;
    <p>${form.name}</p>
    <p>${form.description}</p>
    <p>${form.price}</p>
</c:if>
<form action="product_save.action" method="post">
    <fieldset>
        <legend>Add a product</legend>
            <p>
                <label for="name">Product Name: </label>
                <input type="text" id="name" name="name" 
                    tabindex="1">
            </p>
            <p>
                <label for="description">Description: </label>
                <input type="text" id="description" 
                    name="description" tabindex="2">
            </p>
            <p>
                <label for="price">Price: </label>
                <input type="text" id="price" name="price" 
                    tabindex="3">
            </p>
            <p id="buttons">
                <input id="reset" type="reset" tabindex="4">
                <input id="submit" type="submit" tabindex="5" 
                    value="Add Product">
            </p>
    </fieldset>
</form>
</div>--%>
</body>
</html>
