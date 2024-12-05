<%--************
Create the JSP For Viuw/Edit from the Category table
 Created By Jonathan Beck 7/31/2024
**********--%>
<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class = "container">
    <%@include file="/WEB-INF/Budget_App/user_dash_buttons.jsp"%>
    <form method="post" action="${appURL}/editCategory" id = "editCategory" >
        <!-- Category_ID -->
        <div class ="row" id = "row0">
            <h2>Category_ID  : ${fn:escapeXml(category.category_ID)}</h2>
            <input type="text" class="<c:if test="${not empty results.categoryCategory_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Category_ID" id="inputcategoryCategory_ID" name="inputcategoryCategory_ID" value="${fn:escapeXml(category.category_ID)}">

        </div>
        <!-- User_ID -->

        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Edit Category </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>

