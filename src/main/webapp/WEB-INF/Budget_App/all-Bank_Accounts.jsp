<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>

<div class="container-fluid mt-3 px-4">
    <%-- Header Section --%>
    <div class="row mb-3">
        <div class="col-12 d-flex justify-content-between align-items-center">
            <div>
                <h1 class="h3 mb-1">Bank Accounts</h1>
                <span class="badge bg-dark">${Bank_Accounts.size()} Total Records</span>
            </div>
            <div class="btn-group">
                <a href="addBank_Account" class="btn btn-sm btn-outline-primary">+ Add New Account</a>
            </div>
        </div>
    </div>

    <%-- Data Table --%>
    <div class="row">
        <div class="col-12">
            <c:if test="${not empty Bank_Accounts}">
                <div class="table-responsive shadow-sm bg-white rounded border">
                    <table class="table table-hover align-middle mb-0">
                        <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Account Nickname</th>
                            <th class="text-end">Balance</th>
                            <th>Balance Date</th>
                            <th class="text-center">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${Bank_Accounts}" var="bank_account">
                            <tr>
                                <td>
                                    <a href="editBank_Account?bank_accountid=${bank_account.bank_Account_ID}&mode=view"
                                       class="fw-bold text-decoration-none">
                                            ${fn:escapeXml(bank_account.bank_Account_ID)}
                                    </a>
                                </td>
                                <td>${fn:escapeXml(bank_account.account_Nickname)}</td>
                                <td class="text-end fw-bold">
                                    <fmt:formatNumber value="${bank_account.balance}" type="currency"/>
                                </td>
                                <td>${fn:escapeXml(bank_account.balance_Date)}</td>
                                <td class="text-center">
                                    <div class="d-flex gap-2 justify-content-center">
                                        <a href="editBank_Account?bank_accountid=${bank_account.bank_Account_ID}&mode=edit"
                                           class="btn btn-sm btn-outline-secondary">
                                            <i class="bi bi-pencil"></i> Edit
                                        </a>
                                        <a href="deletebank_account?bank_accountid=${bank_account.bank_Account_ID}"
                                           class="btn btn-sm btn-outline-danger"
                                           onclick="return confirm('Are you sure you want to delete this bank account?');">
                                            <i class="bi bi-trash"></i>
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>

            <c:if test="${empty Bank_Accounts}">
                <div class="alert alert-info">
                    No bank accounts found.
                </div>
            </c:if>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>