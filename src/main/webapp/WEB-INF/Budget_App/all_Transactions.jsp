<%--************
Create the JSP  For Viewing All of The  Transaction table
 Created By Jonathan Beck 7/22/2024
**********--%>
<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class = "container">
    <div class="row">
        <div class="col-12" id="addr" addr="${appURL}">

            <h1>All ${User_B.email} Transactions</h1>
            <p>There ${Transactions.size() eq 1 ? "is" : "are"}&nbsp;${Transactions.size()} Transaction${Transactions.size() ne 1 ? "s" : ""}</p>
            Add Category   <a href="addTransactionCategory">Add</a> <br/>
            search transactions <a href="search_transaction">search</a> <br/>
            Reverse order <a href="${appURL}/all-Transactions?sort=${sort}&direction=${direction}&category=${category}&year=${year}&reverse=true">reverse</a><br/>
            <c:if test="${Transactions.size() > 0}">

                <div >
                    <table class="table table-hover ">
                        <thead class="thead-dark">
                    <tr>
                        <th scope="col"><a href="${appURL}/all-Transactions?sort=ID&direction=${direction}&category=${category}&year=${year}">Transaction_ID</a></th>
                        <th scope="col">Account_Num</th>
                        <th scope="col"><a href="${appURL}/all-Transactions?sort=Date&direction=${direction}&category=${category}&year=${year}">Post_Date</a></th>
                        <th scope="col"><a href="${appURL}/all-Transactions?sort=Check&direction=${direction}&category=${category}&year=${year}">Check_No</a></th>
                        <th scope="col">Description</th>
                        <th scope="col"><a href="${appURL}/all-Transactions?sort=Amount&direction=${direction}&category=${category}&year=${year}">Amount</a></th>
                        <th scope="col">Type</th>
                        <th scope="col">Status</th>
                        <th scope="col">Category</th>
                        <th scope="col"></th>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${Transactions}" var="transaction">
                        <tr >
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
                            <td><a href = "editTransaction?transactionid=${transaction.transaction_ID}&mode=edit" > Edit </a></td>

                        </tr>

                  </c:forEach>
                  </tbody>
              </table>
                </div>


          </c:if>
      </div>
  </div>
</div>
<%--For displaying Previous link except for the 1st page --%>
<c:if test="${currentPage != 1}">
    <form action="all-Transactions" method="get">
        <input type="hidden" name="sort" value="${sort}">
        <input type="hidden" name="category" value="${category}">
        <input type="hidden" name="year" value="${year}">
        <input type="hidden" name="reverse" value="${reverse}">
        <input type="hidden" name="page" value="${currentPage-1}">
        <br/><br/>
        <input type="submit" value="Previous Page" />
    </form>
</c:if>

<%--For displaying Page numbers.
The when condition does not display a link for the current page--%>
<form action="all-Transactions" method="get" >
    <input type="hidden" name="sort" value="${sort}">
    <input type="hidden" name="category" value="${category}">
    <input type="hidden" name="year" value="${year}">
    <input type="hidden" name="reverse" value="${reverse}">
    Select a page :
    <select name="page" onchange="this.form.submit()">
        <c:forEach var="i" begin="1" end="${noOfPages}">
            <option value=${i}  ${currentPage == i ? ' selected' : ''} >${i}</option>
        </c:forEach>

    </select>
    <br/><br/>
    <input type="submit" value="Submit" />
</form>

<%--For displaying Next link --%>
<c:if test="${currentPage lt noOfPages}">
    <form action="all-Transactions" method="get">
        <input type="hidden" name="sort" value="${sort}">
        <input type="hidden" name="category" value="${category}">
        <input type="hidden" name="year" value="${year}">
        <input type="hidden" name="reverse" value="${reverse}">
        <input type="hidden" name="page" value="${currentPage+1}">
        <br/><br/>
        <input type="submit" value="Next Page" />
    </form>
</c:if>
</main>
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>

