<%--************
Create the JSP  For adding to The  Receipt table
 Created By Jonathan Beck 10/8/2025
**********--%>
<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/addReceipt" id = "addReceipt" enctype="multipart/form-data" >
        <!-- Transaction_ID -->
        <div class ="row" id = "row0">
            <label for="inputreceiptTransaction_ID" class="form-label">Transaction_ID</label>
            <div class="input-group input-group-lg">
                <select  class="<c:if test="${not empty results.receiptTransaction_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Transaction_ID" id="inputreceiptTransaction_ID" name="inputreceiptTransaction_ID" value="${fn:escapeXml(results.Transaction_ID)}">
                    <c:forEach items="${Transactions}" var="Transaction">
                        <option value="${Transaction.transaction_ID}" <c:if test="${_transaction_ID eq Transaction.transaction_ID}"> selected  </c:if>>${Transaction.description}   </option>
                    </c:forEach>
                </select>
                <c:if test="${not empty results.receiptTransaction_IDerror}">
                    <div class="invalid-feedback">${results.receiptTransaction_IDerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Image_Link -->
        <div class ="row" id = "row1">
            <label for="inputreceiptImage_Link" class="form-label">Image_Link</label>
            <div class="input-group input-group-lg">
                <input type="file" size="50" accept=".jpg,.jpeg,.png" class="<c:if test="${not empty results.pictureWeb_Addresserror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Logo" id="inputreceiptImage_Link" name="inputreceiptImage_Link" value="${fn:escapeXml(results.Web_Address)}" multiple>
                <c:if test="${not empty results.pictureWeb_Addresserror}">
                    <div class="invalid-feedback">${results.teamLogoerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Name -->
        <div class ="row" id = "row2">
            <label for="inputreceiptName" class="form-label">Name</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.receiptNameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Name" id="inputreceiptName" name="inputreceiptName" value="${fn:escapeXml(results.Name)}">
                <c:if test="${not empty results.receiptNameerror}">
                    <div class="invalid-feedback">${results.receiptNameerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Description -->
        <div class ="row" id = "row3">
            <label for="inputreceiptDescription" class="form-label">Description</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.receiptDescriptionerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Description" id="inputreceiptDescription" name="inputreceiptDescription" value="${fn:escapeXml(results.Description)}">
                <c:if test="${not empty results.receiptDescriptionerror}">
                    <div class="invalid-feedback">${results.receiptDescriptionerror}</div>
                </c:if>
            </div>
        </div>
        <div class="align-items-center mt-0">
            <div class="d-grid"><button id="submitButton" class="btn btn-orange mb-0" type="submit">Create Receipt  </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>

