<%--************
Create the JSP For Viuw/Edit from the Mortgage table
 Created By Jonathan Beck 10/14/2025
**********--%>
<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/editMortgage" id = "editMortgage" >
        <!-- Mortgage_ID -->
        <div class ="row" id = "row0">
            <h2>Name  :
                ${fn:escapeXml(mortgage.nickname)}</h2>
        </div>

        <!-- Nickname -->
        <div class ="row" id = "row2">
            <label for="inputmortgageNickname" class="form-label">Nickname</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.mortgageNicknameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Nickname" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputmortgageNickname" name="inputmortgageNickname" value="${fn:escapeXml(mortgage.nickname)}">
                <c:if test="${not empty results.mortgageNicknameerror}">
                    <div class="invalid-feedback">${results.mortgageNicknameerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Present_Value -->
        <div class ="row" id = "row3">
            <label for="inputmortgagePresent_Value" class="form-label">Present_Value</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.mortgagePresent_Valueerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Present_Value" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputmortgagePresent_Value" name="inputmortgagePresent_Value" value="${fn:escapeXml(mortgage.present_Value)}">
                <c:if test="${not empty results.mortgagePresent_Valueerror}">
                    <div class="invalid-feedback">${results.mortgagePresent_Valueerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Future_Value -->
        <div class ="row" id = "row4">
            <label for="inputmortgageFuture_Value" class="form-label">Future_Value</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.mortgageFuture_Valueerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Future_Value" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputmortgageFuture_Value" name="inputmortgageFuture_Value" value="${fn:escapeXml(mortgage.future_Value)}">
                <c:if test="${not empty results.mortgageFuture_Valueerror}">
                    <div class="invalid-feedback">${results.mortgageFuture_Valueerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Interest_Rate -->
        <div class ="row" id = "row5">
            <label for="inputmortgageInterest_Rate" class="form-label">Interest_Rate</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.mortgageInterest_Rateerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Interest_Rate" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputmortgageInterest_Rate" name="inputmortgageInterest_Rate" value="${fn:escapeXml(mortgage.interest_Rate)}">
                <c:if test="${not empty results.mortgageInterest_Rateerror}">
                    <div class="invalid-feedback">${results.mortgageInterest_Rateerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Monthly_Payment -->
        <div class ="row" id = "row6">
            <label for="inputmortgageMonthly_Payment" class="form-label">Monthly_Payment</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.mortgageMonthly_Paymenterror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Monthly_Payment" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputmortgageMonthly_Payment" name="inputmortgageMonthly_Payment" value="${fn:escapeXml(mortgage.monthly_Payment)}">
                <c:if test="${not empty results.mortgageMonthly_Paymenterror}">
                    <div class="invalid-feedback">${results.mortgageMonthly_Paymenterror}</div>
                </c:if>
            </div>
        </div>
        <!-- Extra_Payment -->
        <div class ="row" id = "row7">
            <label for="inputmortgageExtra_Payment" class="form-label">Extra_Payment</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.mortgageExtra_Paymenterror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Extra_Payment" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputmortgageExtra_Payment" name="inputmortgageExtra_Payment" value="${fn:escapeXml(mortgage.extra_Payment)}">
                <c:if test="${not empty results.mortgageExtra_Paymenterror}">
                    <div class="invalid-feedback">${results.mortgageExtra_Paymenterror}</div>
                </c:if>
            </div>
        </div>
        <!-- Remaining_Term -->
        <div class ="row" id = "row8">
            <label for="inputmortgageRemaining_Term" class="form-label">Remaining_Term</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.mortgageRemaining_Termerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Remaining_Term" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputmortgageRemaining_Term" name="inputmortgageRemaining_Term" value="${fn:escapeXml(mortgage.remaining_Term)}">
                <c:if test="${not empty results.mortgageRemaining_Termerror}">
                    <div class="invalid-feedback">${results.mortgageRemaining_Termerror}</div>
                </c:if>
            </div>
        </div>
        <div class="align-items-center mt-0">
            <div class="d-grid"><button id="submitButton" class="btn btn-orange mb-0" type="submit">Edit Mortgage </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>

