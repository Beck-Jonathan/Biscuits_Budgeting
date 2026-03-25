<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>

<div class="container-fluid mt-3 px-4">
    <div class="row mb-3">
        <div class="col-12 d-flex justify-content-between align-items-center">
            <div>
                <h1 class="h3 mb-1">All Transactions</h1>
                <span class="badge bg-dark">${transaction_count} Total Records</span>
            </div>
            <div class="btn-group">
                <a href="addTransactionCategory" class="btn btn-sm btn-outline-primary">+ Category</a>
                <a href="?sort=${sort}&direction=${direction}&category=${category}&bankAccountID=${bankAccountID}&showErrors=${showErrors}&year=${year}&reverse=true"
                   class="btn btn-sm btn-outline-warning">⇅ Reverse Sort</a>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-12">
            <div class="filter-toolbar">
                <form method="get" action="all-Transactions">
                    <input type="hidden" name="sort" value="${sort}">
                    <input type="hidden" name="direction" value="${direction}">
                    <input type="hidden" name="year" value="${year}">

                    <div class="filter-group">
                        <div class="filter-item">
                            <label class="small fw-bold text-muted">CATEGORY</label>
                            <select name="category" class="form-select rounded-pill shadow-sm">
                                <option value="">All Categories</option>
                                <c:forEach items="${Categories}" var="cat">
                                    <option value="${cat.category_ID}" ${cat.category_ID == category ? 'selected' : ''}>
                                            ${cat.category_Name}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="filter-item">
                            <label class="small fw-bold text-muted">BANK ACCOUNT</label>
                            <select name="bankAccountID" class="form-select rounded-pill shadow-sm">
                                <option value="">Any Account</option>
                                <c:forEach items="${BankAccounts}" var="ba">
                                    <option value="${ba.bank_Account_ID}" ${ba.bank_Account_ID == bankAccountID ? 'selected' : ''}>
                                            ${ba.account_Nickname}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="filter-switch d-flex justify-content-center">
                            <div class="form-check form-switch">
                                <input class="form-check-input" type="checkbox" name="showErrors" id="showErrors" value="true" ${showErrors ? 'checked' : ''}>
                                <label class="form-check-label small fw-bold text-danger" for="showErrors">Potential Errors</label>
                            </div>
                        </div>

                        <div class="filter-action">
                            <button type="submit" class="btn btn-primary w-100 rounded-pill shadow-sm">Filter Results</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <c:if test="${not empty Transactions}">
        <div class="row">
            <div class="col-12">
                <div class="table-responsive shadow-sm bg-white rounded border">
                    <table class="table table-hover align-middle mb-0 table-fixed">
                        <thead class="table-dark">
                            <%-- Reusable URL params to keep search/filter state while sorting --%>
                        <c:set var="urlBase" value="category=${category}&bankAccountID=${bankAccountID}&showErrors=${showErrors}&year=${year}&page=${currentPage}" />

                        <tr>

                            <th class="col-date text-white">Account</th>
                            <th class="col-date">
                                <a class="text-white text-decoration-none d-block" href="?sort=Date&direction=${direction == 0 ? 1 : 0}&${urlBase}">
                                    Date <i class="bi bi-arrow-down-up small ms-1"></i>
                                </a>
                            </th>
                            <th class="text-white">Check #</th>
                            <th class="text-white">Description</th>
                            <th class="col-amount">
                                <a class="text-white text-decoration-none d-block" href="?sort=Amount&direction=${direction == 0 ? 1 : 0}&${urlBase}">
                                    Amount <i class="bi bi-arrow-down-up small ms-1"></i>
                                </a>
                            </th>
                            <th class="col-category text-white">Category</th>
                            <th class="col-status text-white">Status</th>
                            <th class="text-white text-center">Locked</th>
                            <th class="col-action text-white">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${Transactions}" var="t">
                            <tr>
                                

                                <td><span class="badge bg-light text-dark border">${t.bank_Account_ID}</span></td>

                                <td>${t.post_Date}</td>

                                <td>${empty t.check_No ? '--' : t.check_No}</td>

                                <td>
                                    <div class="text-truncate-custom" title="${fn:escapeXml(t.description)}">
                                            ${fn:escapeXml(t.description)}
                                    </div>
                                </td>

                                <td class="text-end fw-bold ${fn:containsIgnoreCase(t.type, 'credit') ? 'amt-credit' : 'amt-debit'}">
                                        ${fn:containsIgnoreCase(t.type, 'credit') ? '+' : ''}${t.amount}
                                </td>

                                <td>
                                    <select class="form-select form-select-sm shadow-sm" style="min-width: 150px;">
                                        <c:forEach items="${Categories}" var="cat">
                                            <option value="${cat.category_ID}" ${cat.category_ID == t.category_ID ? 'selected' : ''}>
                                                    ${cat.category_Name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </td>

                                <td>
                                    <small class="text-uppercase text-muted fw-bold">${t.status}</small>
                                </td>

                                <td class="text-center">
                                    <input type="checkbox" class="form-check-input" ${t.is_Locked ? 'checked' : ''}>
                                </td>

                                <td>
                                    <a href="editTransaction?transactionid=${t.transaction_ID}&mode=edit" class="btn btn-sm btn-outline-secondary py-0">
                                        Edit
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </c:if>

    <div class="row mt-4 mb-5">
        <div class="col-12 d-flex justify-content-between align-items-center">
            <span class="text-muted small">Page ${currentPage} of ${noOfPages}</span>
            <form action="all-Transactions" method="get" class="d-flex align-items-center">
                <input type="hidden" name="sort" value="${sort}">
                <input type="hidden" name="direction" value="${direction}">
                <input type="hidden" name="category" value="${category}">
                <input type="hidden" name="bankAccountID" value="${bankAccountID}">
                <input type="hidden" name="showErrors" value="${showErrors}">

                <ul class="pagination pagination-sm mb-0 me-3">
                    <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                        <a class="page-link" href="?page=${currentPage - 1}&sort=${sort}&direction=${direction}&category=${category}&bankAccountID=${bankAccountID}&showErrors=${showErrors}">Prev</a>
                    </li>
                </ul>

                <select name="page" class="form-select form-select-sm" style="width: auto;" onchange="this.form.submit()">
                    <c:forEach var="i" begin="1" end="${noOfPages}">
                        <option value="${i}" ${currentPage == i ? 'selected' : ''}>Go to Page ${i}</option>
                    </c:forEach>
                </select>

                <ul class="pagination pagination-sm mb-0 ms-3">
                    <li class="page-item ${currentPage == noOfPages ? 'disabled' : ''}">
                        <a class="page-link" href="?page=${currentPage + 1}&sort=${sort}&direction=${direction}&category=${category}&bankAccountID=${bankAccountID}&showErrors=${showErrors}">Next</a>
                    </li>
                </ul>
            </form>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>