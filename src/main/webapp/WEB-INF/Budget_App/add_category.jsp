<%--************
Create the JSP  For adding to The  Category table
 Created By Jonathan Beck 7/30/2024
**********--%>
<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class = "container">

    <form method="post" action="${appURL}/addTransactionCategory" id = "addCategory" >
        <!-- Category_ID -->
        <div class ="row" id = "row0">
            <label for="inputcategoryCategory_Name" class="form-label">Category_ID</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.categoryCategory_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Category Name" id="inputcategoryCategory_Name" name="inputcategoryCategory_Name" value="${fn:escapeXml(results.Category_Name)}">
                <c:if test="${not empty results.categoryCategory_Nameerror}">
                    <div class="invalid-feedback">${results.categoryCategory_Nameerror}</div>
                </c:if>
            </div>
        </div>
        <!-- end Category_ Id -->
        <!-- start color -->
        <c:set var="activeColor" value="${empty results.Color_ID ? '#FF0000' : results.Color_ID}" />

        <div class="custom-color-picker">
            <label for="colorSource">Pick a Category Color:</label>
            <div class="picker-wrapper">
                <input type="color" id="colorSource" value="${activeColor}" class="native-picker">

                <input type="hidden" name="inputcategoryColor_id" id="inputcategoryColor_id" value="${activeColor}">

                <div class="status-text">
                    Selected: <span id="hexDisplay">${activeColor}</span>
                </div>
            </div>
        </div>

        <!-- end  color -->
        <!-- start button -->
        <div class="align-items-center mt-0">
            <div class="d-grid"><button id="submitButton" class="btn btn-orange mb-0" type="submit">Create Category  </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
        <!-- end button -->
    </form>
</div>
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>

