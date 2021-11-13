<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <div class="container">
        <div class="row header__top">
            <div class="col-2 d-flex align-items-center">
                <a href="${pageContext.request.contextPath}/">
                    <span class="header__title">Online Store</span>
                </a>
            </div>
            <div class="col-3 d-flex align-items-center">
                <img src="${pageContext.request.contextPath}/img/phone_icon.svg" alt="tel">
                <a href="tel:+38066002233">
                    <span>+380 66 000 22 33</span>
                </a>
            </div>
            <div class="col-3 d-flex align-items-center">
                <form class="search d-flex" action="#" method="post">
                    <input type="text" placeholder="Search..." name="search">
                    <button type="submit">
                        <img src="${pageContext.request.contextPath}/img/search_icon.svg" alt="search">
                    </button>
                </form>
            </div>
            <div class="col-4 d-flex align-items-center justify-content-end">
                <c:if test="${not empty pageContext.session.getValue('user_id')}">
                    <div class="logout d-flex flex-column align-items-center">
                        <img src="${pageContext.request.contextPath}/img/logout_icon.svg" alt="logout" class="logout_icon">
                        <a href="${pageContext.request.contextPath}/logout">Logout</a>
                    </div>
                </c:if>
                <div class="login d-flex flex-column align-items-center">
                    <img class="account_icon" src="${pageContext.request.contextPath}/img/account_icon.svg" alt="account">
                    <c:if test="${empty pageContext.session.getValue('user_id')}">
                        <a href="${pageContext.request.contextPath}/login">Sign in</a>
                    </c:if>
                    <c:if test="${not empty pageContext.session.getValue('user_id')}">
                        <a href="${pageContext.request.contextPath}#">Account</a>
                    </c:if>
                </div>
                <div class="cart d-flex flex-column align-items-center">
                    <img class="shopping_cart" src="${pageContext.request.contextPath}/img/shopping_cart_icon.svg" alt="cart">
                    <a href="">Cart</a>
                </div>
                <div class="language">
                    <div class="dropdown">
                        <button class="dropdown__button">
                            <img src="${pageContext.request.contextPath}/img/language_icon.svg" alt="lang">
                        </button>
                        <div class="dropdown-content">
                            <form method="post" action="${pageContext.request.contextPath}/">
                                <c:forEach items="${applicationScope['languages']}" var="item">
                                        <button type="submit" name="lang" value="<c:out value="${item.getShortName()}"/>"><c:out value="${item.getFullName()}" /></button>
                                </c:forEach>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <hr class="header__separator">
    <div class="container">
        <nav class="row header__bottom menu">
            <div class="col d-flex align-items-center first-menu">
                <ul class="d-flex">
                    <li class="selected"><a href="#">Home</a></li>
                    <li><a href="#">Catalog</a></li>
                    <li><a href="#">Flash deals</a></li>
                    <li><a href="#">New Arrivals</a></li>
                </ul>
            </div>
            <div class="col d-flex align-items-center justify-content-end second-menu">
                <ul class="d-flex">
                    <li><a href="#">Delivery options</a></li>
                    <li><a href="#">Contact us</a></li>
                </ul>
            </div>
        </nav>
    </div>
</header>
