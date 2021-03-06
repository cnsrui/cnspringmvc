<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<title>Save Product</title>
    <style type="text/css">@import url("<c:url value="/css/main.css"/>");</style>
</head>
<body>
<div id="global">
    <h4>The product has been saved.</h4>
    <p>
        <h5>Details:</h5>
        Product Name: ${product.name}<br/>
        Description: ${product.description}<br/>
        Price: $${product.price}<br/>
        Product Date : ${product.productionDate}
    <ol>
        <c:forEach items="${product.images}" var="image">
            <li>${image.originalFilename}
                <img width="100" src="<c:url value="/files/"/>
            ${image.originalFilename}"/>
            </li>
        </c:forEach>
    </ol>
    </p>
</div>
</body>
</html>