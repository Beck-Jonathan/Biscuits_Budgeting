<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>

<div class="container-fluid mt-3 px-4">
    <%-- Header Section --%>
    <div class="row mb-3">
        <div class="col-12 d-flex justify-content-between align-items-center">
            <div>
                <h1 class="h3 mb-1">Budgets</h1>
                <span class="badge bg-dark">${count} Total Budgets</span>
            </div>
            <div class="btn-group">
                <a href="addbudget" class="btn btn-sm btn-outline-primary">+ Add New Budget</a>
            </div>
        </div>
    </div>

    <%-- Filter Toolbar --%>
    <div class="row mb-4">
        <div class="col-12">
            <div class="filter-toolbar p-3 bg-light rounded border shadow-sm">
                <form action="all-budgets" method="get" class="row g-3 align-items-end">
                    <div class="col-md-3">
                        <label class="small fw-bold text-muted text-uppercase">Search</label>
                        <input type="text" name="search" class="form-control rounded-pill shadow-sm"
                               placeholder="Search budgets..." value="${fn:escapeXml(param.search)}">
                    </div>
                    <div class="col-md-3">
                        <label class="small fw-bold text-muted text-uppercase">User ID</label>
                        <select name="inputbudgetuser_id" class="form-select rounded-pill shadow-sm">
                            <option value="">All Users</option>
                            <c:forEach items="${users}" var="user">
                                <option value="${user.user_ID}" ${param.inputbudgetuser_id == user.user_ID ? 'selected' : ''}>
                                        ${fn:escapeXml(user.name)}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-3">
                        <label class="small fw-bold text-muted text-uppercase">Currency</label>
                        <select name="inputbudgetcurrency_code_id" class="form-select rounded-pill shadow-sm">
                            <option value="">All Currencies</option>
                            <c:forEach items="${currency_codes}" var="currency_code">
                                <option value="${currency_code}" ${param.inputbudgetcurrency_code_id == currency_code ? 'selected' : ''}>
                                        ${fn:escapeXml(currency_code)}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-3">
                        <button type="submit" class="btn btn-primary w-100 rounded-pill shadow-sm">
                            <i class="bi bi-search me-1"></i> Filter Results
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <%-- Budget Card Grid --%>
    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 row-cols-xl-4 g-4 mb-5">
        <c:forEach items="${budgets}" var="b">
            <div class="col" id="${b.budget_id}Card">
                <div class="budget_card card h-100 shadow-sm border-0 position-relative">
                    <div class="card-header bg-white d-flex justify-content-between align-items-center border-0 pt-3">
                        <h5 class="card-title mb-0 fw-bold text-primary">${fn:escapeXml(b.name)}</h5>

                            <%-- Restored data attributes and pointer style for your JS logic --%>
                        <span class="badge status-badge ${b.is_active ? 'bg-success' : 'bg-secondary'}"
                              data-id="${b.budget_id}"
                              data-active="${b.is_active}"
                              style="cursor: pointer; z-index: 5; position: relative; min-width: 65px;">
                                ${b.is_active ? 'Active' : 'Inactive'}
                        </span>
                    </div>

                    <div class="card-body">
                        <p class="text-muted small">${fn:escapeXml(b.details)}</p>

                        <div class="d-flex justify-content-between mb-1">
                            <span class="small fw-bold">Spent: $${b.totalSpent}</span>
                            <span class="small text-muted">Limit: $${b.limit_amount} ${b.currency_code_id}</span>
                        </div>

                        <div class="progress mb-3"
                             style="height: 20px; background-color: #e9ecef; border-radius: 8px; overflow: hidden;">
                            <c:forEach items="${b.lines}" var="line">
                                <c:set var="itemPercentage" value="${(line.amount / b.limit_amount) * 100}"/>
                                <c:if test="${itemPercentage > 0 && line.budget_line_status_id != 'Inactive'}">
                                    <div class="progress-bar" role="progressbar"
                                         style="width: ${itemPercentage}%; background-color: ${line.category.color_id};"
                                         title="${fn:escapeXml(line.name)}: $${line.amount}">
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>

                        <div class="row g-2 text-center small mb-3">
                            <div class="col-6 border-end">
                                <div class="text-muted">Start Date</div>
                                <div>${b.start_date}</div>
                            </div>
                            <div class="col-6">
                                <div class="text-muted">Role</div>
                                <div class="badge bg-light text-dark border">User</div>
                            </div>
                        </div>
                    </div>

                    <div class="card-footer bg-light border-0 d-flex justify-content-around pb-3">
                        <a href="editBudget?budgetid=${b.budget_id}&mode=view" class="btn btn-sm btn-outline-primary"
                           style="z-index: 3;">Details</a>
                        <a href="editBudget?budgetid=${b.budget_id}&mode=edit" class="btn btn-sm btn-outline-secondary"
                           style="z-index: 3;">Edit</a>
                        <button class="btn btn-sm btn-outline-danger delButton" data-id="${b.budget_id}"
                                style="z-index: 3;">Delete
                        </button>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <%-- Pagination --%>
    <div class="row mt-4 mb-5">
        <div class="col-12 d-flex justify-content-between align-items-center">
            <span class="text-muted small">Page ${currentPage} of ${noOfPages}</span>
            <form action="all-budgets" method="get" class="d-flex align-items-center">
                <input type="hidden" name="user_id" value="${user_id}">
                <input type="hidden" name="currency_code_id" value="${currency_code_id}">
                <input type="hidden" name="search" value="${fn:escapeXml(results.search)}">

                <nav aria-label="Page navigation">
                    <ul class="pagination pagination-sm mb-0 me-3">
                        <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                            <button type="submit" name="page" value="${currentPage - 1}" class="page-link">Prev</button>
                        </li>
                        <li class="page-item mx-2">
                            <select name="page" class="form-select form-select-sm" onchange="this.form.submit()">
                                <c:forEach var="i" begin="1" end="${noOfPages}">
                                    <option value="${i}" ${currentPage == i ? 'selected' : ''}>${i}</option>
                                </c:forEach>
                            </select>
                        </li>
                        <li class="page-item ${currentPage == noOfPages ? 'disabled' : ''}">
                            <button type="submit" name="page" value="${currentPage + 1}" class="page-link">Next</button>
                        </li>
                    </ul>
                </nav>
            </form>
        </div>
    </div>
</div>

<div id="dialog" title="Confirmation Required" style="display:none;">
    Are you sure about this?
</div>

<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>