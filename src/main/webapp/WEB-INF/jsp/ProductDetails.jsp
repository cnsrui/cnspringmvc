<!DOCTYPE html>
<%--由于jsp2.0不再隐性的支持 EL表达式，所以需要在 jsp 网页中添加此句才可以使用EL表达式--%>
<%@ page isELIgnored="false" %>
<html>
<head>
<title>Save Product</title>
<style type="text/css">@import url(css/main.css);</style>
</head>
<body>
<div id="global">
    <h4>The product has been saved.</h4>
    <p>
        <h5>Details:</h5>
        Product Name: ${product.name}<br/>
        Description: ${product.description}<br/>
        Price: $${product.price}
    </p>
</div>
</body>
</html>