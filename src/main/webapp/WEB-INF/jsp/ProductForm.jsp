<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE HTML>
<html>
<head>
    <%--使用spring的message标签，读取message.properties中的属性值--%>
    <title><spring:message code="page.productform.title"/></title>
    <style type="text/css">@import url("<c:url value="/css/main.css"/>");</style>
</head>
<body>
Current Locale " ${pageContext.response.locale}
<br/>
<%-- 必须这样写 ${header["accept-language"]} 其他写法不起作用--%>
header accetp-language : ${header["accept-language"]}

<h5><spring:message code="page.h5.title"/></h5>
    <div id="global">
    <form:form commandName="product" action="product_save" method="post">
        <fieldset>
            <legend><spring:message code="form.name"/></legend>
            <p class="errorLine">
                <form:errors path="name" cssClass="error"/>
            </p>
            <p>
                <label for="name">* <spring:message code="label.productName" text="default text"/> : </label>
                <form:input id="name" path="name" tabindex="1"/>
            </p>
            <p>
                <label for="description"><spring:message code="label.description"/> : </label>
                <form:input id="description" path="description" tabindex="2"/>
            </p>
            <p class="errorLine">
                <form:errors path="price" cssClass="error"/>
            </p>
            <p>
                <label for="price">* <spring:message code="label.price" text="default text"/> : </label>
                <form:input id="price" path="price" tabindex="3"/>
            </p>
            <p class="errorLine">
                <form:errors path="productionDate" cssClass="error"/>
            </p>
            <p>
                <label for="productionDate">* <spring:message code="label.productDate"/> : </label>
                <form:input id="productionDate" path="productionDate" tabindex="4"/>
            </p>
            <p id="buttons">
                <input id="reset" type="reset" tabindex="5" value="<spring:message code="button.reset"/>">
                <input id="submit" type="submit" tabindex="6" value="<spring:message code="button.submit"/>">
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