<%--************
Create the JSP For Viuw/Edit from the Saved_Search_Order table
 Created By Jonathan Beck 2/4/2025
**********--%>
<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/editSaved_Search_Order" id = "editSaved_Search_Order" >
        <!-- Saved_Search_Order_ID -->
        <div class ="row" id = "row0">
            <h2>Saved_Search_Order_ID  :
                ${fn:escapeXml(saved_search_order.saved_Search_Order_ID)}</h2>
        </div>

        <!-- Nickname -->
        <div class ="row" id = "row2">
            <label for="inputsaved_search_orderNickname" class="form-label">Nickname</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.saved_search_orderNicknameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Nickname" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputsaved_search_orderNickname" name="inputsaved_search_orderNickname" value="${fn:escapeXml(saved_search_order.nickname)}">
                <c:if test="${not empty results.saved_search_orderNicknameerror}">
                    <div class="invalid-feedback">${results.saved_search_orderNicknameerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Description -->
        <div class ="row" id = "row3">
            <label for="inputsaved_search_orderDescription" class="form-label">Description</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.saved_search_orderDescriptionerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Description" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputsaved_search_orderDescription" name="inputsaved_search_orderDescription" value="${fn:escapeXml(saved_search_order.description)}">
                <c:if test="${not empty results.saved_search_orderDescriptionerror}">
                    <div class="invalid-feedback">${results.saved_search_orderDescriptionerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Last_Used -->
        <div class ="row" id = "row4">
            <label for="inputsaved_search_orderLast_Used" class="form-label">Last_Used</label>
            <div class="input-group input-group-lg">
                <input type="date" class="<c:if test="${not empty results.saved_search_orderLast_Usederror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Last_Used"  disabled  id="inputsaved_search_orderLast_Used" name="inputsaved_search_orderLast_Used" value="${fn:escapeXml(saved_search_order.last_Used)}">
                <c:if test="${not empty results.saved_search_orderLast_Usederror}">
                    <div class="invalid-feedback">${results.saved_search_orderLast_Usederror}</div>
                </c:if>
            </div>
        </div>
        <!-- Last_Updated -->
        <div class ="row" id = "row5">
            <label for="inputsaved_search_orderLast_Updated" class="form-label">Last_Updated</label>
            <div class="input-group input-group-lg">
                <input type="date" class="<c:if test="${not empty results.saved_search_orderLast_Updatederror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Last_Updated"  disabled  id="inputsaved_search_orderLast_Updated" name="inputsaved_search_orderLast_Updated" value="${fn:escapeXml(saved_search_order.last_Updated)}">
                <c:if test="${not empty results.saved_search_orderLast_Updatederror}">
                    <div class="invalid-feedback">${results.saved_search_orderLast_Updatederror}</div>
                </c:if>
            </div>
        </div>
        <!-- Times_Ran -->
        <div class ="row" id = "row6">
            <label for="inputsaved_search_orderTimes_Ran" class="form-label">Times_Ran</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.saved_search_orderTimes_Ranerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Times_Ran" disabled   id="inputsaved_search_orderTimes_Ran" name="inputsaved_search_orderTimes_Ran" value="${fn:escapeXml(saved_search_order.times_Ran)}">
                <c:if test="${not empty results.saved_search_orderTimes_Ranerror}">
                    <div class="invalid-feedback">${results.saved_search_orderTimes_Ranerror}</div>
                </c:if>
            </div>
        </div>
        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Edit Saved_Search_Order </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>

