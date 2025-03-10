<%--************
Create the JSP For Viuw/Edit from the Transaction table
 Created By Jonathan Beck 3/3/2025
**********--%>
<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/editTransaction" id = "editTransaction" >
        <!-- Transaction_ID -->
        <div class ="row" id = "row0">
            <h2>Transaction_ID  :
                ${fn:escapeXml(transaction.transaction_ID)}</h2>
        </div>
        <!-- User_ID -->
        <div class ="row" id = "row1">
            <label for="inputtransactionUser_ID" class="form-label">User_ID</label>
            <div class="input-group input-group-lg">
                <select  class="<c:if test="${not empty results.transactionUser_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1"  <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputtransactionUser_ID" name="inputtransactionUser_ID" value="${fn:escapeXml(transaction.user_ID)}">
                    <c:forEach items="${Users}" var="User">
                        <option value="${User.user_ID}"<c:if test="${transaction.user_ID eq User.user_ID}"> selected </c:if>>${User.name}   </option>
                    </c:forEach>
                </select>
                <c:if test="${not empty results.transactionUser_IDerror}">
                    <div class="invalid-feedback">${results.transactionUser_IDerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Category_ID -->
        <div class ="row" id = "row2">
            <label for="inputtransactionCategory_ID" class="form-label">Category_ID</label>
            <div class="input-group input-group-lg">
                <select  class="<c:if test="${not empty results.transactionCategory_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1"  <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputtransactionCategory_ID" name="inputtransactionCategory_ID" value="${fn:escapeXml(transaction.category_ID)}">
                    <c:forEach items="${Categorys}" var="Category">
                        <option value="${Category.category_ID}"<c:if test="${transaction.category_ID eq Category.category_ID}"> selected </c:if>>${Category.name}   </option>
                    </c:forEach>
                </select>
                <c:if test="${not empty results.transactionCategory_IDerror}">
                    <div class="invalid-feedback">${results.transactionCategory_IDerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Bank_Account_ID -->
        <div class ="row" id = "row3">
            <label for="inputtransactionBank_Account_ID" class="form-label">Bank_Account_ID</label>
            <div class="input-group input-group-lg">
                <select  class="<c:if test="${not empty results.transactionBank_Account_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1"  <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputtransactionBank_Account_ID" name="inputtransactionBank_Account_ID" value="${fn:escapeXml(transaction.bank_Account_ID)}">
                    <c:forEach items="${Bank_Accounts}" var="Bank_Account">
                        <option value="${Bank_Account.bank_Account_ID}"<c:if test="${transaction.bank_Account_ID eq Bank_Account.bank_Account_ID}"> selected </c:if>>${Bank_Account.name}   </option>
                    </c:forEach>
                </select>
                <c:if test="${not empty results.transactionBank_Account_IDerror}">
                    <div class="invalid-feedback">${results.transactionBank_Account_IDerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Post_Date -->
        <div class ="row" id = "row4">
            <label for="inputtransactionPost_Date" class="form-label">Post_Date</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.transactionPost_Dateerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Post_Date" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputtransactionPost_Date" name="inputtransactionPost_Date" value="${fn:escapeXml(transaction.post_Date)}">
                <c:if test="${not empty results.transactionPost_Dateerror}">
                    <div class="invalid-feedback">${results.transactionPost_Dateerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Check_No -->
        <div class ="row" id = "row5">
            <label for="inputtransactionCheck_No" class="form-label">Check_No</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.transactionCheck_Noerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Check_No" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputtransactionCheck_No" name="inputtransactionCheck_No" value="${fn:escapeXml(transaction.check_No)}">
                <c:if test="${not empty results.transactionCheck_Noerror}">
                    <div class="invalid-feedback">${results.transactionCheck_Noerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Description -->
        <div class ="row" id = "row6">
            <label for="inputtransactionDescription" class="form-label">Description</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.transactionDescriptionerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Description" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputtransactionDescription" name="inputtransactionDescription" value="${fn:escapeXml(transaction.description)}">
                <c:if test="${not empty results.transactionDescriptionerror}">
                    <div class="invalid-feedback">${results.transactionDescriptionerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Amount -->
        <div class ="row" id = "row7">
            <label for="inputtransactionAmount" class="form-label">Amount</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.transactionAmounterror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Amount" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputtransactionAmount" name="inputtransactionAmount" value="${fn:escapeXml(transaction.amount)}">
                <c:if test="${not empty results.transactionAmounterror}">
                    <div class="invalid-feedback">${results.transactionAmounterror}</div>
                </c:if>
            </div>
        </div>
        <!-- Type -->
        <div class ="row" id = "row8">
            <label for="inputtransactionType" class="form-label">Type</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.transactionTypeerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Type" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputtransactionType" name="inputtransactionType" value="${fn:escapeXml(transaction.type)}">
                <c:if test="${not empty results.transactionTypeerror}">
                    <div class="invalid-feedback">${results.transactionTypeerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Status -->
        <div class ="row" id = "row9">
            <label for="inputtransactionStatus" class="form-label">Status</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.transactionStatuserror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Status" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputtransactionStatus" name="inputtransactionStatus" value="${fn:escapeXml(transaction.status)}">
                <c:if test="${not empty results.transactionStatuserror}">
                    <div class="invalid-feedback">${results.transactionStatuserror}</div>
                </c:if>
            </div>
        </div>
        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Edit Transaction </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
<div class = "container">
    <div class="row">
        <div class="col-12">
            <h1>All WFTDA_debug Transaction Transaction Comments</h1>
            <p>There ${Transaction.Transaction_Comments.size() eq 1 ? "is" : "are"}&nbsp;${Transaction.Transaction_Comments.size()} Transaction${Transaction.Transaction_Comments.size() ne 1 ? "s" : ""}</p>
            <c:if test="${Transaction.Transaction_Comments.size() > 0}">
                <div class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">User_ID</th>
                        <th scope="col">Transaction_ID</th>
                        <th scope="col">Transaction_Comment_ID</th>
                        <th scope="col">Content</th>
                        <th scope="col">Post_Date</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${Transaction.Transaction_Comments}" var="transaction_comment">
                        <tr>
                            <td>${fn:escapeXml(transaction_comment.user_ID)}</td>
                            <td>${fn:escapeXml(transaction_comment.transaction_ID)}</td>
                            <td>${fn:escapeXml(transaction_comment.transaction_Comment_ID)}</td>
                            <td>${fn:escapeXml(transaction_comment.content)}</td>
                            <td>${fn:escapeXml(transaction_comment.post_Date)}</td>
                            <td><a href = "edittransaction_comment?transactionid=${transaction.transaction_ID}&mode=edit" > Edit </a></td>
                            <td><a href = "deletetransaction_comment?transactionid=${transaction.transaction_ID}&mode=<c:choose><c:when test="${transaction.is_active}">0</c:when>
						<c:otherwise>1</c:otherwise>
						</c:choose>">
                                <c:if test="${!transaction.is_active}">un</c:if>Delete </a></td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <form method="post" action="${appURL}/addTransaction_Comment" id = "addTransaction" >
                            <td><!-- User_ID -->
                                <select  class="<c:if test="${not empty results.transactionUser_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="User_ID" id="inputtransactionUser_ID" name="inputtransactionUser_ID" value="${fn:escapeXml(results.User_ID)}">
                                    <c:forEach items="${Users}" var="User">
                                        <option value="${User.user_ID}">${User.name}   </option>
                                    </c:forEach>
                                </select>
                                <c:if test="${not empty results.transactionUser_IDerror}">
                                    <div class="invalid-feedback">${results.transactionUser_IDerror}</div>
                                </c:if>
                            </td>
                            <td><!-- Transaction_ID -->
                                <select  class="<c:if test="${not empty results.transactionTransaction_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Transaction_ID" id="inputtransactionTransaction_ID" name="inputtransactionTransaction_ID" value="${fn:escapeXml(results.Transaction_ID)}">
                                    <c:forEach items="${Transactions}" var="Transaction">
                                        <option value="${Transaction.transaction_ID}">${Transaction.name}   </option>
                                    </c:forEach>
                                </select>
                                <c:if test="${not empty results.transactionTransaction_IDerror}">
                                    <div class="invalid-feedback">${results.transactionTransaction_IDerror}</div>
                                </c:if>
                            </td>
                            <td><!-- Transaction_Comment_ID -->
                                <input type="text" class="<c:if test="${not empty results.transactionTransaction_Comment_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Transaction_Comment_ID" id="inputtransactionTransaction_Comment_ID" name="inputtransactionTransaction_Comment_ID" value="${fn:escapeXml(results.Transaction_Comment_ID)}">
                                <c:if test="${not empty results.transactionTransaction_Comment_IDerror}">
                                    <div class="invalid-feedback">${results.transactionTransaction_Comment_IDerror}</div>
                                </c:if>
                            </td>
                            <td><!-- Content -->
                                <input type="text" class="<c:if test="${not empty results.transactionContenterror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Content" id="inputtransactionContent" name="inputtransactionContent" value="${fn:escapeXml(results.Content)}">
                                <c:if test="${not empty results.transactionContenterror}">
                                    <div class="invalid-feedback">${results.transactionContenterror}</div>
                                </c:if>
                            </td>
                            <td><!-- Post_Date -->
                                <input type="date" class="<c:if test="${not empty results.transactionPost_Dateerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Post_Date" id="inputtransactionPost_Date" name="inputtransactionPost_Date" value="${fn:escapeXml(results.Post_Date)}">
                                <c:if test="${not empty results.transactionPost_Dateerror}">
                                    <div class="invalid-feedback">${results.transactionPost_Dateerror}</div>
                                </c:if>
                            </td>
                            <td><button class="btn btn-orange mb-0" type="submit">Create Transaction  </button></td>
                        </form>
                    </tr>
                    </tbody>
                </table>
                </div>
            </c:if>
        </div>
    </div>
</div>
</main>
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>

