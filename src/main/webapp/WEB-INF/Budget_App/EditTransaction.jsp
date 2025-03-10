<%--************
Create the JSP For Viuw/Edit from the Transaction table
 Created By Jonathan Beck 1/16/2025
**********--%>
<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/editTransaction" id = "editTransaction" >
        <!-- Transaction_ID -->
        <div class ="row" id = "row0">
            <h2>Transaction_ID  :
                ${fn:escapeXml(transaction.transaction_ID)}</h2>
        </div>

        <!-- Category_ID -->
        <div class ="row" id = "row2">
            <label for="inputtransactionCategory_ID" class="form-label">Category_ID</label>
            <div class="input-group input-group-lg">
                <select  class="<c:if test="${not empty results.transactionCategory_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1"  <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputtransactionCategory_ID" name="inputtransactionCategory_ID" value="${fn:escapeXml(transaction.category_ID)}">
                    <c:forEach items="${Categorys}" var="Category">
                        <option value="${Category.category_ID}"<c:if test="${transaction.category_ID eq Category.category_ID}"> selected </c:if>>${Category.category_ID}   </option>
                    </c:forEach>
                </select>
                <c:if test="${not empty results.transactionCategory_IDerror}">
                    <div class="invalid-feedback">${results.transactionCategory_IDerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Account_Num -->
        <div class ="row" id = "row3">
            <label for="inputtransactionAccount_Num" class="form-label">Account_Num</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.transactionAccount_Numerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Account_Num" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputtransactionAccount_Num" name="inputtransactionAccount_Num" value="${fn:escapeXml(transaction.bank_Account_ID)}">
                <c:if test="${not empty results.transactionAccount_Numerror}">
                    <div class="invalid-feedback">${results.transactionAccount_Numerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Post_Date -->
        <div class ="row" id = "row4">
            <label for="inputtransactionPost_Date" class="form-label">Post_Date</label>
            <div class="input-group input-group-lg">
                <input type="date" class="<c:if test="${not empty results.transactionPost_Dateerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Post_Date" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputtransactionPost_Date" name="inputtransactionPost_Date" value="${fn:escapeXml(transaction.post_Date)}">
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
            <h1>All Transaction Comments</h1>
            <p>There ${transaction.transaction_Comments.size() eq 1 ? "is" : "are"}&nbsp;${transaction.transaction_Comments.size()} Transaction Comment${transaction.transaction_Comments.size() ne 1 ? "s" : ""}</p>
            Add Transaction_Comment   <a href="addTransaction_Comment">Add</a>

                <tr class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>

                        <th scope="col">Content</th>
                        <th scope="col">Post_Date</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${transaction.transaction_Comments}" var="transaction_comment">
                        <tr>

                            <form method="post" action="${appURL}/deletetransaction_comment">

                                <input type="hidden" name="transaction_commentid" value="${transaction_comment.transaction_Comment_ID}">
                                <input type="hidden" name="transaction_id" value="${transaction.transaction_ID}">
                                    
                            <td>${fn:escapeXml(transaction_comment.content)}</td>
                            <td>${fn:escapeXml(transaction_comment.post_Date)}</td>
                            <td><a href = "edittransaction_comment?transaction_commentid=${transaction_comment.transaction_Comment_ID}&mode=edit" > Edit </a></td>
                            <td><button class="btn btn-orange mb-0" type="submit">Delete  </button> </td>
                            </form>

                        </tr>
                    </c:forEach>

                    <tr>
                        <form method="post" action="${appURL}/addTransaction_Comment" id = "addTransaction_Comment" >


                            <!-- Transaction_Comment_ID -->


                                <input type="hidden" name="inputtransaction_commentTransaction_ID" value="${transaction.transaction_ID}">
                                <input type="hidden" name="inputtransaction_commentTransaction_Comment_ID" value="${commentID}">


                            <!-- Content -->
                            <td>
                                <div class="input-group input-group-lg">
                                    <input type="text" class="<c:if test="${not empty results.transaction_commentContenterror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Content" id="inputtransaction_commentContent" name="inputtransaction_commentContent" value="${fn:escapeXml(results.Content)}">
                                    <c:if test="${not empty results.transaction_commentContenterror}">
                                        <div class="invalid-feedback">${results.transaction_commentContenterror}</div>
                                    </c:if>
                                </div>
                            </td>
                            <!-- Post_Date -->
                            <td>

                                <div class="input-group input-group-lg">
                                    <input type="date" value="<fmt:formatDate value="<%= new java.util.Date()%>" pattern="yyyy-MM-dd" />" disabled class="<c:if test="${not empty results.transaction_commentPost_Dateerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Post_Date" id="inputtransaction_commentPost_Date" name="inputtransaction_commentPost_Date" value="${fn:escapeXml(results.Post_Date)}">
                                    <c:if test="${not empty results.transaction_commentPost_Dateerror}">
                                        <div class="invalid-feedback">${results.transaction_commentPost_Dateerror}</div>
                                    </c:if>
                                </div>
                            </td>
                            <td colspan="2" class="align-items-center mt-0">
                                <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Create Transaction Comment  </button></div>
                                <c:if test="${not empty results.dbStatus}"
                                ><p>${results.dbStatus}</p>
                                </c:if>
                            </td>
                        </form>
                    </tr>
                    </tbody>
                </table>
                </div>

        </div>
    </div>
</div>
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>

