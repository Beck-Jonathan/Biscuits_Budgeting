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
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>

