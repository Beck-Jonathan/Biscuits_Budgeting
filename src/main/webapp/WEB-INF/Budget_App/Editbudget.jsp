<%--************
Create the JSP For Viuw/Edit from the budget table
 Created By Jonathan Beck 2/26/2026
**********--%>
<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/editBudget" id = "editbudget" >
        <!-- budget_id -->
        <div class ="row" id = "row0">
            <h2>user_id  :
                ${fn:escapeXml(Budget.user_id)}</h2>
        </div>

        <!-- name -->
        <div class ="row" id = "row2">
            <label for="inputBudgetname" class="form-label">name</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.budgetnameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="name" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputBudgetname" name="inputBudgetname" value="${fn:escapeXml(Budget.name)}">
                <c:if test="${not empty results.budgetnameerror}">
                    <div class="invalid-feedback">${results.budgetnameerror}</div>
                </c:if>
            </div>
        </div>
        <!-- details -->
        <div class ="row" id = "row3">
            <label for="inputBudgetdetails" class="form-label">details</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.budgetdetailserror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="details" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputBudgetdetails" name="inputBudgetdetails" value="${fn:escapeXml(Budget.details)}">
                <c:if test="${not empty results.budgetdetailserror}">
                    <div class="invalid-feedback">${results.budgetdetailserror}</div>
                </c:if>
            </div>
        </div>
        <!-- start_date -->
        <div class ="row" id = "row4">
            <label for="inputBudgetstart_date" class="form-label">start_date</label>
            <div class="input-group input-group-lg">
                <input type="date" class="<c:if test="${not empty results.budgetstart_dateerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="start_date" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputBudgetstart_date" name="inputBudgetstart_date" value="${fn:escapeXml(Budget.start_date)}">
                <c:if test="${not empty results.budgetstart_dateerror}">
                    <div class="invalid-feedback">${results.budgetstart_dateerror}</div>
                </c:if>
            </div>
        </div>
        <!-- limit_amount -->
        <div class ="row" id = "row5">
            <label for="inputBudgetlimit_amount" class="form-label">limit_amount</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.budgetlimit_amounterror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="limit_amount" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputBudgetlimit_amount" name="inputBudgetlimit_amount" value="${fn:escapeXml(Budget.limit_amount)}">
                <c:if test="${not empty results.budgetlimit_amounterror}">
                    <div class="invalid-feedback">${results.budgetlimit_amounterror}</div>
                </c:if>
            </div>
        </div>
        <!-- currency_code_id -->
        <div class ="row" id = "row6">
            <label for="inputBudgetcurrency_code_id" class="form-label">currency_code_id</label>
            <div class="input-group input-group-lg">
                <select  class="<c:if test="${not empty results.budgetcurrency_code_iderror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1"  <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputBudgetcurrency_code_id" name="inputBudgetcurrency_code_id" value="${fn:escapeXml(Budget.currency_code_id)}">
                    <c:forEach items="${currency_codes}" var="currency_code">
                        <option value="${currency_code}"<c:if test="${Budget.currency_code_id eq currency_code}"> selected </c:if>>${currency_code}   </option>
                    </c:forEach>
                </select>
                <c:if test="${not empty results.budgetcurrency_code_iderror}">
                    <div class="invalid-feedback">${results.budgetcurrency_code_iderror}</div>
                </c:if>
            </div>
        </div>

        <div class="align-items-center mt-0">
            <div class="d-grid"><button id="submitButton" class="btn btn-orange mb-0" type="submit">Edit budget </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>

