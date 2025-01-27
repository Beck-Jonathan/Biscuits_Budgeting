<%--************
Create the JSP  For Viewing All of The  Bank_Account table
 Created By Jonathan Beck 1/22/2025
**********--%>
<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class = "container">
    <div class="row">
        <div class="col-12">
            <h1>All Biscuit Bank_Accounts</h1>
            <p>There ${Bank_Accounts.size() eq 1 ? "is" : "are"}&nbsp;${Bank_Accounts.size()} Bank_Account${Bank_Accounts.size() ne 1 ? "s" : ""}</p>
            Add Bank_Account   <a href="addBank_Account">Add</a>
            <c:if test="${Bank_Accounts.size() > 0}">
                <div class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">Bank_Account_ID</th>
                        <th scope="col">User_ID</th>
                        <th scope="col">Account_Nickname</th>
                        <th scope="col">Balance</th>
                        <th scope="col">Balance_Date</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${Bank_Accounts}" var="bank_account">
                        <tr>
                            <td><a href = "editBank_Account?bank_accountid=${bank_account.bank_Account_ID}&mode=view">${fn:escapeXml(bank_account.bank_Account_ID)}</a></td>
                            <td>${fn:escapeXml(bank_account.user_ID)}</td>
                            <td>${fn:escapeXml(bank_account.account_Nickname)}</td>
                            <td>${fn:escapeXml(bank_account.balance)}</td>
                            <td>${fn:escapeXml(bank_account.balance_Date)}</td>
                            <td><a href = "editBank_Account?bank_accountid=${bank_account.bank_Account_ID}&mode=edit" > Edit </a></td>
                            <td><a href = "deletebank_account?bank_accountid=${bank_account.bank_Account_ID}&mode=0"> Delete </a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </div>
            </c:if>
        </div>
    </div>
</div>
</main>
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>

