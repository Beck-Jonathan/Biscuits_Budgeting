<%--************
Create the JSP  For Viewing All of The  Transaction table
 Created By Jonathan Beck 7/22/2024
**********--%>
<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class = "container">
    <div class="row">
        <div class="col-12" id="addr" addr="${appURL}">

            <h1>All Biscuit Transactions</h1>
            <p>There ${Transactions.size() eq 1 ? "is" : "are"}&nbsp;${Transactions.size()} Transaction${Transactions.size() ne 1 ? "s" : ""}</p>
            Search
            <form method="get" action="${appURL}/search_transaction" id = "search">

                <label for="query">query:</label><br>
                <input type="text" id="query" name="query" value =${search}><br>
                <input type="submit" value="Submit">

            </form>


            </form>
            <c:if test="${Transactions.size() > 0}">

                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">Transaction_ID</th>
                            <th scope="col">Account_Num</th>
                            <th scope="col">Post_Date</th>
                            <th scope="col">Check_No</th>
                            <th scope="col">Description</th>
                            <th scope="col">Amount</th>
                            <th scope="col">Type</th>
                            <th scope="col">Status</th>
                            <th scope="col">Category</th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${Transactions}" var="transaction">
                        <tr>
                            <td><a href = "editTransaction?transactionid=${transaction.transaction_ID}&mode=view">${fn:escapeXml(transaction.transaction_ID)}</a></td>
                            <td>${fn:escapeXml(transaction.bank_Account_ID)}</td>
                            <td>${fn:escapeXml(transaction.post_Date)}</td>
                            <td>${fn:escapeXml(transaction.check_No)}</td>
                            <td>${fn:escapeXml(transaction.description)}</td>
                            <td>${fn:escapeXml(transaction.amount)}</td>
                            <td>${fn:escapeXml(transaction.type)}</td>
                            <td>${fn:escapeXml(transaction.status)}</td>
                            <td id ="change+${transaction.transaction_ID}" >


                                <input type="hidden" name="t_id" value =${transaction.transaction_ID}>
                                <select  class="category" name="category" onchange="" id="${transaction.transaction_ID}">
                                    <c:forEach items="${Categories}" var="category" >
                                        <option  value="${category.category_ID}"  ${category.category_ID == transaction.category_ID ? 'selected' : ''}>${category.category_ID}</option>
                                    </c:forEach>
                                </select>


                            </td>
                            <td style="width:50px">

                                <div id="${transaction.transaction_ID}_status"  style="border:none; display:none;"  > xx </div>

                            </td>








                                <%--************  <td><a href = "deletetransaction?transactionid=${transaction.transaction_ID}&mode=<c:choose><c:when test="${transaction.is_active}">0</c:when>
                              <c:otherwise>1</c:otherwise>
                              </c:choose>">
                                      <c:if test="${!transaction.is_active}">un</c:if>Delete </a></td>
                              </tr>
                              **********--%>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>


            </c:if>

        </div>
    </div>
<c:if test="${Transactions.size()>2}">
<form method="post" action="${appURL}/search_transaction" id = "apply">
    <input type="hidden" name="s_id" value =${search}>
    <select name="category">
        <c:forEach items="${Categories}" var="category">
            <option value="${category.category_ID}"  ${category.category_ID == transaction.category_ID ? 'selected' : ''}>${category.category_ID}</option>
        </c:forEach>

    </select>
    <td><input type="submit" value="Submit" /></td>
</form>
</c:if>
</div>



<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>