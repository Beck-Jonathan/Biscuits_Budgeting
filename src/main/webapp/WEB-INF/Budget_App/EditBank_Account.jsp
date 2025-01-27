<%--************
Create the JSP For Viuw/Edit from the Bank_Account table
 Created By Jonathan Beck 1/22/2025
**********--%>
<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/editBank_Account" id = "editBank_Account" >
        <!-- Bank_Account_ID -->
        <div class ="row" id = "row0">
            <h2>Bank_Account_ID  :
                ${fn:escapeXml(bank_account.bank_Account_ID)}</h2>
        </div>

        <!-- Account_Nickname -->
        <div class ="row" id = "row2">
            <label for="inputbank_accountAccount_Nickname" class="form-label">Account_Nickname</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.bank_accountAccount_Nicknameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Account_Nickname" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputbank_accountAccount_Nickname" name="inputbank_accountAccount_Nickname" value="${fn:escapeXml(bank_account.account_Nickname)}">
                <c:if test="${not empty results.bank_accountAccount_Nicknameerror}">
                    <div class="invalid-feedback">${results.bank_accountAccount_Nicknameerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Balance -->
        <div class ="row" id = "row3">
            <label for="inputbank_accountBalance" class="form-label">Balance</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.bank_accountBalanceerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Balance" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputbank_accountBalance" name="inputbank_accountBalance" value="${fn:escapeXml(bank_account.balance)}">
                <c:if test="${not empty results.bank_accountBalanceerror}">
                    <div class="invalid-feedback">${results.bank_accountBalanceerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Balance_Date -->
        <div class ="row" id = "row4">
            <label for="inputbank_accountBalance_Date" class="form-label">Balance_Date</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.bank_accountBalance_Dateerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Balance_Date" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputbank_accountBalance_Date" name="inputbank_accountBalance_Date" value="${fn:escapeXml(bank_account.balance_Date)}">
                <c:if test="${not empty results.bank_accountBalance_Dateerror}">
                    <div class="invalid-feedback">${results.bank_accountBalance_Dateerror}</div>
                </c:if>
            </div>
        </div>
        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Edit Bank_Account </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>

