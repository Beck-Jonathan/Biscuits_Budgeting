<%--************
Create the JSP  For adding to The  Saved_Search_Order table
 Created By Jonathan Beck 2/4/2025
**********--%>
<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/addSaved_Search_Order" id = "addSaved_Search_Order" >

        <!-- Nickname -->
        <div class ="row" id = "row1">
            <label for="inputsaved_search_orderNickname" class="form-label">Nickname</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.saved_search_orderNicknameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Nickname" id="inputsaved_search_orderNickname" name="inputsaved_search_orderNickname" value="${fn:escapeXml(results.Nickname)}">
                <c:if test="${not empty results.saved_search_orderNicknameerror}">
                    <div class="invalid-feedback">${results.saved_search_orderNicknameerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Description -->
        <div class ="row" id = "row2">
            <label for="inputsaved_search_orderDescription" class="form-label">Description</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.saved_search_orderDescriptionerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Description" id="inputsaved_search_orderDescription" name="inputsaved_search_orderDescription" value="${fn:escapeXml(results.Description)}">
                <c:if test="${not empty results.saved_search_orderDescriptionerror}">
                    <div class="invalid-feedback">${results.saved_search_orderDescriptionerror}</div>
                </c:if>
            </div>
        </div>

        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Create Saved_Search_Order  </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>

