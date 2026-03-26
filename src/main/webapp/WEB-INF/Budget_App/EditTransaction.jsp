<%--************
Updated JSP for View/Edit Transaction
Custom Styled & Vertically Stacked
**********--%>
<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>

<style>

</style>

<div class="ba-main-container">

    <section class="ba-section-card">
        <div class="ba-section-header">
            <h5 class="mb-0">Transaction Information</h5>
            <span class="badge ${transaction.amount < 0 ? 'bg-danger' : 'bg-success'}">
                <fmt:formatNumber value="${transaction.amount}" type="currency"/>
            </span>
        </div>
        <div class="ba-section-body">
            <div class="ba-data-grid mb-4">
                <div><span class="ba-label">ID</span><span
                        class="ba-value">${fn:escapeXml(transaction.transaction_ID)}</span></div>
                <div><span class="ba-label">Post Date</span><span
                        class="ba-value">${fn:escapeXml(transaction.post_Date)}</span></div>
                <div><span class="ba-label">Account</span><span
                        class="ba-value">${fn:escapeXml(transaction.bank_Account_ID)}</span></div>
                <div><span class="ba-label">Type/Status</span><span
                        class="ba-value">${fn:escapeXml(transaction.type)} / ${fn:escapeXml(transaction.status)}</span>
                </div>
                <div style="grid-column: span 2;"><span class="ba-label">Description</span><span
                        class="ba-value">${fn:escapeXml(transaction.description)}</span></div>
            </div>

            <form method="post" action="${appURL}/editTransaction">
                <input type="hidden" name="transaction_ID" value="${transaction.transaction_ID}">
                <div class="row align-items-end g-3">
                    <div class="col-md-7">
                        <label class="ba-label fw-bold mb-1">Budget Category</label>
                        <select class="form-select ${not empty results.transactionCategory_IDerror ? 'is-invalid' : ''}"
                                name="inputtransactionCategory_ID" <c:if test="${mode eq 'view'}">disabled</c:if>>
                            <c:forEach items="${Categorys}" var="cat">
                                <option value="${cat.category_ID}"
                                        <c:if test="${transaction.category_ID eq cat.category_ID}">selected</c:if>>${cat.category_Name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-5 d-flex align-items-center justify-content-between">
                        <div class="form-check form-switch">
                            <input class="form-check-input" type="checkbox" id="lockCheck"
                                   name="inputtransactionIs_Locked" value="true"
                                   <c:if test="${transaction.is_Locked}">checked</c:if>
                                   <c:if test="${mode eq 'view'}">disabled</c:if>>
                            <label class="form-check-label fw-bold" for="lockCheck">Locked</label>
                        </div>
                        <c:if test="${mode eq 'edit'}">
                            <button type="submit" class="btn btn-primary btn-sm px-4">Save Changes</button>
                        </c:if>
                    </div>
                </div>
            </form>
        </div>
    </section>

    <section class="ba-section-card">
        <div class="ba-section-header">
            <h5 class="mb-0">Receipts (${transaction.receipts.size()})</h5>
            <a href="addReceipt?transactionID=${transaction.transaction_ID}" class="btn btn-sm btn-outline-primary">+
                Add New</a>
        </div>
        <div class="ba-section-body">
            <div class="ba-receipt-grid">
                <c:forEach items="${transaction.receipts}" var="receipt">
                    <div class="ba-receipt-thumb">
                        <a href="${receipt.image_Link}" target="_blank">
                            <img src="${receipt.image_Link}" class="ba-receipt-img" alt="Receipt"
                                 referrerPolicy="no-referrer">
                        </a>
                        <div class="p-2 border-top bg-light d-flex justify-content-around">
                            <a href="editReceipt?receiptid=${receipt.receipt_ID}&mode=edit" title="Edit">⚙️</a>
                            <a href="editReceipt?receiptid=${receipt.receipt_ID}&mode=view" title="View">🔎</a>
                            <a href="#" class="text-danger" onclick="confirmDelete('${receipt.receipt_ID}')"
                               title="Delete">❌</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>

    <section class="ba-section-card">
        <div class="ba-section-header">
            <h5 class="mb-0">Comments (${transaction.transaction_Comments.size()})</h5>
        </div>
        <div class="ba-section-body p-0">
            <table class="table table-hover mb-0">
                <thead class="table-light">
                <tr>
                    <th class="ps-4">Comment</th>
                    <th>Date</th>
                    <th class="text-end pe-4">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${transaction.transaction_Comments}" var="comm">
                    <tr>
                        <td class="ps-4">${fn:escapeXml(comm.content)}</td>
                        <td class="small text-muted">${fn:escapeXml(comm.post_Date)}</td>
                        <td class="text-end pe-4">
                            <a href="edittransaction_comment?transaction_commentid=${comm.transaction_Comment_ID}&mode=edit"
                               class="btn btn-link btn-sm p-0 me-2 text-decoration-none">Edit</a>
                            <form method="post" action="${appURL}/deletetransaction_comment" class="d-inline">
                                <input type="hidden" name="transaction_commentid"
                                       value="${comm.transaction_Comment_ID}">
                                <input type="hidden" name="transaction_id" value="${transaction.transaction_ID}">
                                <button class="btn btn-link btn-sm p-0 text-danger text-decoration-none" type="submit"
                                        onclick="return confirm('Delete comment?')">Delete
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                <tr class="table-primary border-top">
                    <form method="post" action="${appURL}/addTransaction_Comment">
                        <input type="hidden" name="inputtransaction_commentTransaction_ID"
                               value="${transaction.transaction_ID}">
                        <td class="ps-4">
                            <input type="text" class="form-control form-control-sm" placeholder="Type a new comment..."
                                   name="inputtransaction_commentContent" required>
                        </td>
                        <td><span class="small text-muted">New</span></td>
                        <td class="text-end pe-4">
                            <button class="btn btn-success btn-sm px-3" type="submit">Post</button>
                        </td>
                    </form>
                </tr>
                </tbody>
            </table>
        </div>
    </section>

</div>

<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>