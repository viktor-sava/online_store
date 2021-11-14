<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<jsp:include page="elements/head.jsp" />
<body>
<jsp:include page="elements/header.jsp" />
<div class="content">
    <div class="container">
        <c:forEach var="item" items="${categories}">
            <div class="container card" style="background-image: url(<c:out value="${item.picture}" />)">
                <span class="card__title"><c:out value="${item.name}" /></span>
                <a href="${pageContext.request.contextPath}/catalog?id=<c:out value="${item.id}" />"><button class="card__button" type="button">Buy</button></a>
            </div>
        </c:forEach>
    </div>
</div>
<jsp:include page="elements/footer.jsp" />
</body>
</html>