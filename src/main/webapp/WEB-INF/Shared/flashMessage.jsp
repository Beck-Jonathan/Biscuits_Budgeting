<c:choose>
    <c:when test="${not empty flashMessageSuccess}">
        <div class="alert alert-success my-2">
                ${flashMessageSuccess}
        </div>
        <c:remove var="flashMessageSuccess" scope="session"></c:remove>
    </c:when>
    <c:when test="${not empty flashMessageWarning}">
        <div class="alert alert-warning my-2">
                ${flashMessageWarning}
        </div>
        <c:remove var="flashMessageWarning" scope="session"></c:remove>
    </c:when>
    <c:when test="${not empty flashMessageDanger}">
        <div class="alert alert-danger my-2">
                ${flashMessageDanger}
        </div>
        <c:remove var="flashMessageDanger" scope="session"></c:remove>
    </c:when>
</c:choose>

