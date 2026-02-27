<%--************
Create the JSP  For adding to The  budget table
 Created By Jonathan Beck 2/26/2026
**********--%>
<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/addbudget" id = "addbudget" >

        <!-- name -->
        <div class ="row" id = "row1">
            <label for="inputbudgetname" class="form-label">name</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.budgetnameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="name" id="inputbudgetname" name="inputbudgetname" value="${fn:escapeXml(results.name)}">
                <c:if test="${not empty results.budgetnameerror}">
                    <div class="invalid-feedback">${results.budgetnameerror}</div>
                </c:if>
            </div>
        </div>
        <!-- details -->
        <div class ="row" id = "row2">
            <label for="inputbudgetdetails" class="form-label">details</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.budgetdetailserror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="details" id="inputbudgetdetails" name="inputbudgetdetails" value="${fn:escapeXml(results.details)}">
                <c:if test="${not empty results.budgetdetailserror}">
                    <div class="invalid-feedback">${results.budgetdetailserror}</div>
                </c:if>
            </div>
        </div>
        <!-- start_date -->
        <div class ="row" id = "row3">
            <label for="inputbudgetstart_date" class="form-label">start_date</label>
            <div class="input-group input-group-lg">
                <input type="date" class="<c:if test="${not empty results.budgetstart_dateerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="start_date" id="inputbudgetstart_date" name="inputbudgetstart_date" value="${fn:escapeXml(results.start_date)}">
                <c:if test="${not empty results.budgetstart_dateerror}">
                    <div class="invalid-feedback">${results.budgetstart_dateerror}</div>
                </c:if>
            </div>
        </div>
        <!-- limit_amount -->
        <div class ="row" id = "row4">
            <label for="inputbudgetlimit_amount" class="form-label">limit_amount</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.budgetlimit_amounterror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="limit_amount" id="inputbudgetlimit_amount" name="inputbudgetlimit_amount" value="${fn:escapeXml(results.limit_amount)}">
                <c:if test="${not empty results.budgetlimit_amounterror}">
                    <div class="invalid-feedback">${results.budgetlimit_amounterror}</div>
                </c:if>
            </div>
        </div>
        <!-- currency_code_id -->
        <div class ="row" id = "row5">
            <label for="inputbudgetcurrency_code_id" class="form-label">currency_code_id</label>
            <div class="input-group input-group-lg">
                <select  class="<c:if test="${not empty results.budgetcurrency_code_iderror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="currency_code_id" id="inputbudgetcurrency_code_id" name="inputbudgetcurrency_code_id" value="${fn:escapeXml(results.currency_code_id)}">
                    <c:forEach items="${currency_codes}" var="currency_code">
                        <option value="${currency_code}">${currency_code}   </option>
                    </c:forEach>
                </select>
                <c:if test="${not empty results.budgetcurrency_code_iderror}">
                    <div class="invalid-feedback">${results.budgetcurrency_code_iderror}</div>
                </c:if>
            </div>
        </div>
        <div class="align-items-center mt-0">
            <div class="d-grid"><button id="submitButton" class="btn btn-orange mb-0" type="submit">Create budget  </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>

