<%--************
Create the JSP  For adding to The  Bank_Account table
 Created By Jonathan Beck 1/22/2025
**********--%>
<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/addBank_Account" id = "addBank_Account" >
        <!-- Bank_Account_ID -->
        <div class ="row" id = "row0">
            <label for="inputbank_accountBank_Account_ID" class="form-label">Bank_Account_ID</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.bank_accountBank_Account_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Bank_Account_ID" id="inputbank_accountBank_Account_ID" name="inputbank_accountBank_Account_ID" value="${fn:escapeXml(results.Bank_Account_ID)}">
                <c:if test="${not empty results.bank_accountBank_Account_IDerror}">
                    <div class="invalid-feedback">${results.bank_accountBank_Account_IDerror}</div>
                </c:if>
            </div>
        </div>

        <!-- Account_Nickname -->
        <div class ="row" id = "row2">
            <label for="inputbank_accountAccount_Nickname" class="form-label">Account_Nickname</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.bank_accountAccount_Nicknameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Account_Nickname" id="inputbank_accountAccount_Nickname" name="inputbank_accountAccount_Nickname" value="${fn:escapeXml(results.Account_Nickname)}">
                <c:if test="${not empty results.bank_accountAccount_Nicknameerror}">
                    <div class="invalid-feedback">${results.bank_accountAccount_Nicknameerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Balance -->
        <div class ="row" id = "row3">
            <label for="inputbank_accountBalance" class="form-label">Balance</label>
            <div class="input-group input-group-lg">
                <input type="number" step="any" class="<c:if test="${not empty results.bank_accountBalanceerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1 input-symbol-dollar" placeholder="Balance" id="inputbank_accountBalance" name="inputbank_accountBalance" value="${fn:escapeXml(results.Balance)}">
                <c:if test="${not empty results.bank_accountBalanceerror}">
                    <div class="invalid-feedback">${results.bank_accountBalanceerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Balance_Date -->
        <div class ="row" id = "row4">
            <label for="inputbank_accountBalance_Date" class="form-label">Balance_Date</label>
            <div class="input-group input-group-lg">
                <input type="date" class="<c:if test="${not empty results.bank_accountBalance_Dateerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Balance_Date" id="inputbank_accountBalance_Date" name="inputbank_accountBalance_Date" value="${fn:escapeXml(results.Balance_Date)}">
                <c:if test="${not empty results.bank_accountBalance_Dateerror}">
                    <div class="invalid-feedback">${results.bank_accountBalance_Dateerror}</div>
                </c:if>
            </div>
        </div>
        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Create Bank_Account  </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>

