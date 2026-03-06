<%--************
Create the JSP For Viuw/Edit from the Category table
 Created By Jonathan Beck 7/31/2024
**********--%>
<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class = "container">

    <form method="post" action="${appURL}/editCategory" id = "editCategory" >
        <!-- Category_Name -->
        <div class ="row" id = "row0">

            <input type="text" class="<c:if test="${not empty results.categoryCategory_Nameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Category_Name" id="inputcategoryCategory_Name" name="inputcategoryCategory_Name" value="${fn:escapeXml(category.category_Name)}">

        </div>


        <!-- start color -->
        <c:set var="activeColor" value="${empty category.color_id ? '#FF0000' : category.color_id}" />

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

        <div class="align-items-center mt-0">
            <div class="d-grid"><button id="submitButton" class="btn btn-orange mb-0" type="submit">Edit Category </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>

    </form>
</div>
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>

