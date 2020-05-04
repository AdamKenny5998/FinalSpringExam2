<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>  
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Products</title>
    </head> 
    <body>
        <security:authorize access="hasRole('SUPERADMIN')"><p>Welcome ${uname}, <a href="logout">Logout</a></p></security:authorize>
        <security:authorize access="hasRole('ADMIN')"><p>Welcome ${uname},<a href="logout">Logout</a></p></security:authorize>
        <security:authorize access="hasRole('USER')"><p>Welcome ${uname},<a href="logout">Logout</a></p></security:authorize>
        <table style="width:100%">
            <tr>
                <th align="left">Code</th>
                <th align="left">Name</th>
                <th align="left">Description</th>
                <th align="left">Buy Price</th>
                <th align="left">Sell Price</th>
                <th align="left">Qty In Stock</th>
                <th align="left">Actions</th>
            </tr>
            <c:forEach items="${productList}" var="product"> 
                <tr>
                    <td>${product.code}</td>
                    <td>${product.name}</td>
                    <td>${product.description}</td>
                    <td><fmt:setLocale value="en_EUR"/>
                        <fmt:formatNumber value="${product.buyPrice}" type="currency"/></td>
                    <td><fmt:formatNumber value="${product.sellPrice}" type="currency"/></td>
                    <td>${product.quantityInStock}</td>
                    <td><security:authorize access="hasRole('SUPERADMIN')">
                            <a href="addProduct">Insert</a>
                            <a href="product/deleteProduct?code=${product.code}">Delete</a>
                        </security:authorize>
                        <security:authorize access="hasRole('ADMIN')">
                            <a href="addProduct">Insert</a>
                        </security:authorize>
                    </td>

                </tr>
            </c:forEach>
        </table>

    </body>
</html>