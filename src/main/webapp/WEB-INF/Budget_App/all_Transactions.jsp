<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>

<%-- Hidden element to provide the context path to your external JavaScript file --%>
<span id="addr" addr="${pageContext.request.contextPath}" style="display:none;"></span>

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

    <%-- Filter Toolbar --%>
    <div class="row">
        <div class="col-12">
            <div class="filter-toolbar mb-4 p-3 bg-light rounded border shadow-sm">
                <form method="get" action="all-Transactions">
                    <input type="hidden" name="sort" value="${sort}">
                    <input type="hidden" name="direction" value="${direction}">
                    <input type="hidden" name="year" value="${year}">

                    <div class="row g-3 align-items-end">
                        <div class="col-md-3">
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

                        <div class="col-md-3">
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

                        <div class="col-md-2">
                            <div class="form-check form-switch mb-2">
                                <input class="form-check-input" type="checkbox" name="showErrors" id="showErrors" value="true" ${showErrors ? 'checked' : ''}>
                                <label class="form-check-label small fw-bold text-danger" for="showErrors">Potential Errors</label>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-check form-switch mb-2">
                                <input class="form-check-input" type="checkbox" name="showLocked" id="showLocked"
                                       value="true" ${showLocked ? 'checked' : ''}>
                                <label class="form-check-label small fw-bold text-danger" for="showLocked">Show
                                    Locked</label>
                            </div>
                        </div>

                        <div class="col-md-2">
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
                    <table class="table table-hover align-middle mb-0">
                        <thead class="table-dark">
                        <c:set var="urlBase" value="category=${category}&bankAccountID=${bankAccountID}&showErrors=${showErrors}&year=${year}&page=${currentPage}" />
                        <tr>
                            <th class="text-white">Account</th>
                            <th>
                                <a class="text-white text-decoration-none d-block" href="?sort=Date&direction=${direction == 0 ? 1 : 0}&${urlBase}">
                                    Date <i class="bi bi-arrow-down-up small ms-1"></i>
                                </a>
                            </th>
                            <th class="text-white">Check #</th>
                            <th class="text-white">Description</th>
                            <th class="text-end">
                                <a class="text-white text-decoration-none d-block" href="?sort=Amount&direction=${direction == 0 ? 1 : 0}&${urlBase}">
                                    Amount <i class="bi bi-arrow-down-up small ms-1"></i>
                                </a>
                            </th>
                            <th class="text-white">Category</th>
                            <th class="text-white">Status</th>
                            <th class="text-white text-center">Locked</th>
                            <th class="text-white text-center">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${Transactions}" var="t">
                            <tr>
                                <td><span class="badge bg-light text-dark border">${t.bank_Account_ID}</span></td>
                                <td>${t.post_Date}</td>
                                <td>${empty t.check_No ? '--' : t.check_No}</td>
                                <td>
                                    <div class="text-truncate" style="max-width: 250px;"
                                         title="${fn:escapeXml(t.description)}">
                                            ${fn:escapeXml(t.description)}
                                    </div>
                                </td>
                                <td class="text-end fw-bold">
                                    <span class="${fn:containsIgnoreCase(t.type, 'credit') ? 'text-success' : 'text-danger'}">
                                            ${fn:containsIgnoreCase(t.type, 'credit') ? '+' : ''}${t.amount}
                                    </span>
                                </td>
                                <td>
                                        <%-- JS TRIGGER: class="category" and id="${t.transaction_ID}" --%>
                                    <select id="${t.transaction_ID}"
                                            class="category form-select form-select-sm shadow-sm"
                                            style="min-width: 160px;">
                                        <c:forEach items="${Categories}" var="cat">
                                            <option value="${cat.category_ID}" ${cat.category_ID == t.category_ID ? 'selected' : ''}>
                                                    ${cat.category_Name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                        <%-- Status indicator for the checkmark animation --%>
                                    <div id="${t.transaction_ID}_status" class="small text-center mt-1"
                                         style="display:none; height: 20px;"></div>
                                </td>
                                <td>
                                    <small class="text-uppercase text-muted fw-bold">${t.status}</small>
                                </td>
                                <td class="text-center">
                                        <%-- JS TRIGGER: class="lock" and id="${t.transaction_ID}_lock" --%>
                                    <div class="d-flex flex-column align-items-center">
                                        <input type="checkbox" id="${t.transaction_ID}_lock"
                                               class="lock form-check-input" ${t.is_Locked ? 'checked' : ''}>
                                            <%-- Re-using the lock ID for the animation container as per your JS logic --%>
                                        <div id="${t.transaction_ID}_lock_anim" class="small mt-1"
                                             style="display:none;"></div>
                                    </div>
                                </td>
                                <td class="text-center">
                                    <a href="editTransaction?transactionid=${t.transaction_ID}&mode=edit"
                                       class="btn btn-sm btn-outline-secondary">
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

    <%-- Pagination --%>
    <div class="row mt-4 mb-5">
        <div class="col-12 d-flex justify-content-between align-items-center">
            <span class="text-muted small">Page ${currentPage} of ${noOfPages}</span>
            <form action="all-Transactions" method="get" class="d-flex align-items-center">
                <input type="hidden" name="sort" value="${sort}">
                <input type="hidden" name="direction" value="${direction}">
                <input type="hidden" name="category" value="${category}">
                <input type="hidden" name="bankAccountID" value="${bankAccountID}">
                <input type="hidden" name="showErrors" value="${showErrors}">
                <input type="hidden" name="showLocked" value="${showLocked}">

                <nav aria-label="Page navigation">
                    <ul class="pagination pagination-sm mb-0 me-3">
                        <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                            <a class="page-link"
                               href="?page=${currentPage - 1}&sort=${sort}&direction=${direction}&category=${category}&bankAccountID=${bankAccountID}&showErrors=${showErrors}&showLocked=${showLocked}">Prev</a>
                        </li>
                        <li class="page-item mx-2">
                            <select name="page" class="form-select form-select-sm" style="width: auto;"
                                    onchange="this.form.submit()">
                                <c:forEach var="i" begin="1" end="${noOfPages}">
                                    <option value="${i}" ${currentPage == i ? 'selected' : ''}>${i}</option>
                                </c:forEach>
                            </select>
                        </li>
                        <li class="page-item ${currentPage == noOfPages ? 'disabled' : ''}">
                            <a class="page-link"
                               href="?page=${currentPage + 1}&sort=${sort}&direction=${direction}&category=${category}&bankAccountID=${bankAccountID}&showErrors=${showErrors}&showLocked=${showLocked}">Next</a>
                        </li>
                    </ul>
                </nav>
            </form>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>