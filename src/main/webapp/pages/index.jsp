<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<jsp:include page="elements/head.jsp" />
<body>
<jsp:include page="elements/header.jsp" />
<div class="content">
    <div class="container d-flex">
        <div class="banner d-flex">
            <button id="left-btn"><i class="arrow"></i></button>
            <div id="carousel">
                <jsp:useBean id="banners" scope="request" type="java.util.List<com.example.online_store.model.Banner>"/>
                <c:forEach var="item" items="${banners}">
                    <a href="${pageContext.request.contextPath}/category?id=${item.categoryId}">
                        <img src="${pageContext.request.contextPath}/${item.picture}" alt="banner">
                    </a>
                </c:forEach>
            </div>
            <button id="right-btn"><i class="arrow"></i></button>
        </div>
    </div>
    <div class="category container d-flex flex-wrap justify-content-between">
        <jsp:useBean id="categories" scope="request" type="java.util.List<com.example.online_store.model.Category>"/>
        <c:forEach var="item" items="${categories}">
            <div class="container card" style="background-image: url(<c:out value="${item.picture}" />)">
                <span class="card__title"><c:out value="${item.name}" /></span>
                <a href="${pageContext.request.contextPath}/category?id=<c:out value="${item.id}" />"><button class="card__button" type="button">Buy</button></a>
            </div>
        </c:forEach>
    </div>
    <div class="products container">
        <span class="products__title">Popular goods</span>
        <div class="cards d-flex flex-wrap justify-content-between">
            <jsp:useBean id="popularGoods" scope="request" type="java.util.List<com.example.online_store.model.Product>" />
            <c:forEach var="item" items="${popularGoods}">
                <div class="card">
                    <div class="container card__image">
                        <img src="${pageContext.request.contextPath}/<c:out value="${item.picture}" />" alt="product">
                    </div>
                    <span class="card__title">${item.name} - ${item.price}</span>
                    <div class="d-flex align-items-center justify-content-between">
                        <a href="${pageContext.request.contextPath}/product?id=<c:out value="${item.id}" />">
                            <button class="card__button" type="button">Buy</button>
                        </a>
                        <div class="card__rating r-<fmt:formatNumber value="${item.rating / item.evaluators}" type="number" maxFractionDigits="0"/>"></div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="products container">
        <span class="products__title">Flash deals</span>
        <div class="cards d-flex flex-wrap justify-content-between">
            <jsp:useBean id="flashDeals" scope="request" type="java.util.List<com.example.online_store.model.Product>" />
            <c:forEach var="item" items="${flashDeals}">
                <div class="card">
                    <div class="container card__image">
                        <img src="<c:out value="${item.picture}" />" alt="product">
                    </div>
                    <span class="card__title">${item.name} - ${item.price}</span>
                    <div class="d-flex align-items-center justify-content-between">
                        <a href="${pageContext.request.contextPath}/product?id=<c:out value="${item.id}" />">
                            <button class="card__button" type="button">Buy</button>
                        </a>
                        <div class="card__rating r-<fmt:formatNumber value="${item.rating / item.evaluators}" type="number" maxFractionDigits="0"/>"></div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="products container">
        <span class="products__title">New arrivals</span>
        <div class="cards d-flex flex-wrap justify-content-between">
            <jsp:useBean id="newArrivals" scope="request" type="java.util.List<com.example.online_store.model.Product>" />
            <c:forEach var="item" items="${newArrivals}">
                <div class="card">
                    <div class="container card__image">
                        <img src="<c:out value="${item.picture}" />" alt="product">
                    </div>
                    <span class="card__title">${item.name} - ${item.price}</span>
                    <div class="d-flex align-items-center justify-content-between">
                        <a href="${pageContext.request.contextPath}/product?id=<c:out value="${item.id}" />">
                            <button class="card__button" type="button">Buy</button>
                        </a>
                        <div class="card__rating r-<fmt:formatNumber value="${item.rating / item.evaluators}" type="number" maxFractionDigits="0"/>"></div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<jsp:include page="elements/footer.jsp" />
<script src="${pageContext.request.contextPath}/js/banner.js"></script>
<script src="${pageContext.request.contextPath}/js/rating.js"></script>
</body>
</html>
