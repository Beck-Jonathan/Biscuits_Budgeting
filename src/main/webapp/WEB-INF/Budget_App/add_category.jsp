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
        <c:set var="activeColor" value="${empty results.Color_id ? '#3498db' : results.Color_id}" />

        <div class="mb-4">
            <label class="form-label d-block fw-bold">Category Color</label>
            <div class="d-flex align-items-center gap-3">
                <div class="swatch-container position-relative">
                    <div id="formColorSwatch" class="rounded-circle border"
                         style="background-color: ${activeColor}; width: 45px; height: 45px; cursor: pointer; border-width: 3px !important;">
                    </div>

                    <div id="formColorPickerContainer" class="wheel-picker-container shadow-lg border rounded p-3 bg-white" style="top: 55px; left: 0;">
                        <div id="formWheelCanvas"></div>
                        <button type="button" class="btn btn-sm btn-dark w-100 mt-2" id="btnConfirmColor">Done</button>
                    </div>
                </div>

                <input type="hidden" name="inputcategoryColor_id" id="inputcategoryColor_id" value="${activeColor}">

                <div class="text-muted small">
                    Current: <span id="formHexDisplay" class="fw-bold text-uppercase">${activeColor}</span>
                </div>
            </div>
        </div>
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

