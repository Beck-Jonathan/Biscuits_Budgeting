<%--************
Create the JSP  For adding to The  Category table
 Created By Jonathan Beck 7/30/2024
**********--%>
<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class = "container">

    <form method="post" action="${appURL}/addTransactionCategory" id = "addCategory" >
        <!-- Category_ID -->
        <div class ="row" id = "row0">
            <label for="inputcategoryCategory_ID" class="form-label">Category_ID</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.categoryCategory_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Category_ID" id="inputcategoryCategory_ID" name="inputcategoryCategory_ID" value="${fn:escapeXml(results.Category_ID)}">
                <c:if test="${not empty results.categoryCategory_IDerror}">
                    <div class="invalid-feedback">${results.categoryCategory_IDerror}</div>
                </c:if>
            </div>
        </div>
        <!-- end Category_ Id -->
        <!-- start button -->
        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Create Category  </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
        <!-- end button -->
    </form>
</div>
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>

