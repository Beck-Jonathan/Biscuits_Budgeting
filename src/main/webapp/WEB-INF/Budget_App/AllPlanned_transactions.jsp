<%--************
Create the JSP For Viewing All of The planned_transaction table
Created By Jonathan Beck 4/02/2026
**********--%>

<%@include file="/WEB-INF/Budget_App/budget_top.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-12">
            <%-- Header Section --%>
            <div class="d-flex justify-content-between align-items-end mb-4 border-bottom pb-3">
                <div>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb mb-1">
                            <li class="breadcrumb-item"><a href="home">Dashboard</a></li>
                            <li class="breadcrumb-item active">Planned Transactions</li>
                        </ol>
                    </nav>
                    <h1 class="h2 mb-0">Future Projections</h1>
                    <p class="text-muted small mb-0">
                        You have <strong>${not empty planned_transactions ? planned_transactions.size() : 0}</strong>
                        active
                        planned transaction${planned_transactions.size() ne 1 ? 's' : ''} in the architect.
                    </p>
                </div>

                <div class="d-flex gap-2">
                    <div class="btn-group">
                        <button type="button" class="btn btn-outline-secondary btn-sm dropdown-toggle"
                                data-bs-toggle="dropdown">
                            <i class="bi bi-download"></i> Export
                        </button>
                        <ul class="dropdown-menu shadow">
                            <li><a class="dropdown-item" href="exportplanned_transaction?mode=export">CSV / Excel</a>
                            </li>
                            <li><a class="dropdown-item" href="exportplanned_transaction?mode=SQL">SQL Script</a></li>
                        </ul>
                    </div>
                    <button class="btn btn-primary btn-sm" onclick="$('#new_nickname').focus()">
                        <i class="bi bi-plus-lg"></i> Quick Add
                    </button>
                </div>
            </div>

            <%-- Search & Filter Bar --%>
            <div class="card bg-light border-0 mb-4">
                <div class="card-body p-3">
                    <form action="all-planned_transactions" method="get" class="row g-2 align-items-center">
                        <div class="col-md-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-text bg-white border-end-0"><i class="bi bi-search"></i></span>
                                <input type="text" name="search" class="form-control border-start-0"
                                       placeholder="Search nicknames..." value="${fn:escapeXml(results.search)}">
                            </div>
                        </div>

                        <div class="col-md-3">
                            <select name="subcategory_id" class="form-select form-select-sm">
                                <option value="">All Categories</option>
                                <c:forEach items="${subcategorys}" var="sub">
                                    <option value="${sub.category_ID}" ${sub.category_ID == results.category_id ? 'selected' : ''}>
                                            ${fn:escapeXml(sub.category_Name)}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-3">
                            <select name="budget_id" class="form-select form-select-sm">
                                <option value="">All Budgets</option>
                                <c:forEach items="${budgets}" var="budget">
                                    <option value="${budget.budget_id}" ${budget.budget_id == results.budget_id ? 'selected' : ''}>
                                            ${fn:escapeXml(budget.name)}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-auto">
                            <button type="submit" class="btn btn-dark btn-sm">Apply Filters</button>
                            <a href="all-planned_transactions" class="btn btn-link btn-sm text-secondary">Clear</a>
                        </div>
                    </form>
                </div>
            </div>

            <%-- Planned Transactions Table --%>
            <div class="line_card shadow-sm p-3 mt-4">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h3>Planned Transactions</h3>
                </div>

                <div class="table-responsive">
                    <table class="table table-hover align-middle" id="plannedTransactionTable">
                        <thead class="table-light">
                        <tr style="cursor: pointer;">
                            <th style="width: 40px;"></th>
                            <th onclick="sortTable(0, 'text')">Nickname <i
                                    class="bi bi-arrows-vertical small text-muted"></i></th>
                            <th onclick="sortTable(1, 'select')">Subcategory <i
                                    class="bi bi-arrows-vertical small text-muted"></i></th>
                            <th onclick="sortTable(2, 'select')">Budget <i
                                    class="bi bi-arrows-vertical small text-muted"></i></th>
                            <th onclick="sortTable(3, 'number')">Amount <i
                                    class="bi bi-arrows-vertical small text-muted"></i></th>
                            <th onclick="sortTable(4, 'date')">Start Date <i
                                    class="bi bi-arrows-vertical small text-muted"></i></th>
                            <th onclick="sortTable(5, 'select')">Frequency <i
                                    class="bi bi-arrows-vertical small text-muted"></i></th>
                            <th onclick="sortTable(6, 'number')">Occurrences <i
                                    class="bi bi-arrows-vertical small text-muted"></i></th>
                            <th onclick="sortTable(7, 'status')">Status <i
                                    class="bi bi-arrows-vertical small text-muted"></i></th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody id="plannedLineBody">
                        <c:forEach items="${planned_transactions}" var="item">
                            <tr data-id="${item.planned_transaction_ID}"
                                class="${item.is_active == false ? 'row-inactive' : ''}"
                                style="${item.is_active == false ? 'opacity: 0.5;' : ''}">

                                    <%-- Detail Toggle Icon --%>
                                <td>
                                    <button class="btn btn-sm btn-link p-0 text-decoration-none"
                                            onclick="toggleDetails(this)">
                                        <i class="bi bi-chevron-right toggle-icon"></i>
                                    </button>
                                </td>

                                    <%-- Nickname --%>
                                <td class="editable" data-field="nickname">
                                    <input type="text" class="form-control form-control-sm border-0 bg-transparent"
                                           value="${fn:escapeXml(item.nickname)}"
                                           onblur="updatePlannedItem($(this).closest('tr'))">
                                </td>

                                    <%-- Subcategory --%>
                                    <%-- inplment item.subcategory.color_id here in this section --%>
                                <td class="editable-select d-flex align-items-center" data-field="subcategory_ID">
                                        <%-- The Color Indicator --%>
                                    <span class="subcategory-color-pill me-2"
                                          style="width: 12px; height: 12px; border-radius: 50%; display: inline-block; background-color: ${fn:escapeXml(item.subcategory.color_id)}; border: 1px solid rgba(0,0,0,0.1);">
                                            </span>

                                    <select class="form-select form-select-sm table-edit-input border-0 bg-transparent flex-grow-1"
                                            onchange="updateSubcategoryColor(this); updatePlannedItem($(this).closest('tr'))">
                                        <c:forEach items="${subcategorys}" var="sub">
                                            <%-- Store the color in a data attribute so JS can update the dot instantly --%>
                                            <option value="${sub.category_ID}"
                                                    data-color="${sub.color_id}"
                                                ${sub.category_ID == item.subcategory.category_ID ? 'selected' : ''}>
                                                    ${fn:escapeXml(sub.category_Name)}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </td>

                                    <%-- Budget (Triggers details fetch) --%>
                                <td class="editable-select" data-field="budget_ID">
                                    <select class="form-select form-select-sm table-edit-input border-0 bg-transparent"
                                            onchange="updatePlannedItem($(this).closest('tr'), 'budget')">
                                        <option value="">None</option>
                                        <c:forEach items="${budgets}" var="b">
                                            <option value="${b.budget_id}" ${(not empty item.budget_id and b.budget_id == item.budget_id) ? 'selected' : ''}>
                                                    ${fn:escapeXml(b.name)}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </td>

                                    <%-- Amount --%>
                                <td class="editable" data-field="amount">
                                    <div class="input-group input-group-sm">
                                        <span class="input-group-text bg-transparent border-0 pe-0">$</span>
                                        <input type="number" step="0.01"
                                               class="form-control form-control-sm border-0 bg-transparent fw-bold"
                                               value="${item.amount}"
                                               onblur="updatePlannedItem($(this).closest('tr'))"
                                               onchange="updatePlannedItem($(this).closest('tr'))">
                                    </div>
                                </td>

                                    <%-- Start Date --%>
                                <td class="editable" data-field="start_date">
                                    <input type="date" class="form-control form-control-sm border-0 bg-transparent"
                                           value="${item.start_date}"
                                           onchange="updatePlannedItem($(this).closest('tr'))">
                                </td>

                                    <%-- Frequency --%>
                                <td data-field="times_per_year">
                                    <select class="form-select form-select-sm table-edit-input border-0 bg-transparent"
                                            onchange="updatePlannedItem($(this).closest('tr'))">
                                        <option value="12" ${item.times_per_year == 12 ? 'selected' : ''}>Monthly
                                        </option>
                                        <option value="4" ${item.times_per_year == 4 ? 'selected' : ''}>Quarterly
                                        </option>
                                        <option value="2" ${item.times_per_year == 2 ? 'selected' : ''}>Semi-Annual
                                        </option>
                                        <option value="1" ${item.times_per_year == 1 ? 'selected' : ''}>Annual</option>
                                        <option value="0" ${item.times_per_year == 0 ? 'selected' : ''}>One-Time
                                        </option>
                                    </select>
                                </td>

                                    <%-- Occurrences (Infinite Toggle) --%>
                                <td class="editable" data-field="occurrences">
                                    <div class="occurrence-input-container ${item.occurrences == -1 ? 'd-none' : ''}">
                                        <div class="input-group input-group-sm">
                                            <input type="number" class="form-control border-0 bg-transparent"
                                                   value="${item.occurrences}"
                                                ${item.times_per_year == 0 ? 'disabled' : ''}
                                                   onblur="updatePlannedItem($(this).closest('tr'))"
                                                   onchange="updatePlannedItem($(this).closest('tr'))">

                                            <button class="btn btn-outline-secondary border-0 p-1" type="button"
                                                    onclick="setIndefinite(this)" title="Make Indefinite">
                                                <i class="bi bi-arrow-repeat"></i>
                                            </button>
                                        </div>
                                    </div>
                                    <div class="occurrence-infinite-container ${item.occurrences == -1 ? '' : 'd-none'}">
                                        <button class="btn btn-sm btn-link text-primary p-0 text-decoration-none fw-bold"
                                                onclick="setIndefinite(this)">
                                            <i class="bi bi-infinity"></i> Indefinite
                                        </button>
                                    </div>
                                </td>

                                    <%-- Status --%>
                                <td data-field="is_active">
                                    <div class="form-check form-switch">
                                        <input class="form-check-input" type="checkbox"
                                               onchange="togglePlannedActive(this, '${item.planned_transaction_ID}')"
                                            ${item.is_active ? 'checked' : ''}>
                                    </div>
                                </td>

                                <td>
                                    <button class="btn btn-sm btn-outline-danger border-0"
                                            onclick="deletePlannedItem(this, '${item.planned_transaction_ID}')">
                                        <i class="bi bi-trash"></i>
                                    </button>
                                </td>
                            </tr>
                            <%-- Hidden Details Row --%>
                            <tr id="details-${item.planned_transaction_ID}" class="details-row d-none bg-light">
                                <td colspan="10"> <%-- Adjust colspan to match your total number of columns --%>
                                    <div class="p-3 details-content">
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>

                        <%-- Quick Add Row --%>

                        <tr id="plannedInputRow" class="table-info">
                            <td></td>
                            <td><input type="text" id="new_nickname" class="form-control form-control-sm"
                                       placeholder="e.g. Netflix"></td>
                            <%-- Quick Add Row - Subcategory Cell --%>
                            <td class="editable-select d-flex align-items-center" data-field="subcategory_ID">
                                <%-- The Dynamic Dot for the Input Row --%>
                                <span id="new_subcategory_color_pill" class="subcategory-color-pill me-2"
                                      style="width: 12px; height: 12px; border-radius: 50%; display: inline-block; background-color: #cccccc; border: 1px solid rgba(0,0,0,0.1);">
                                 </span>

                                <select id="new_subcategory" class="form-select form-select-sm"
                                        onchange="updateSubcategoryColor(this)">
                                    <c:forEach items="${subcategorys}" var="sub">
                                        <option value="${sub.category_ID}"
                                                data-color="${sub.color_id}"
                                            ${fn:toLowerCase(sub.category_Name) == "uncategorized_expense" ? 'selected' : ''}>
                                                ${fn:escapeXml(sub.category_Name)}
                                        </option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <select id="new_budget" class="form-select form-select-sm">
                                    <option value="" selected>None</option>
                                    <c:forEach items="${budgets}" var="b">
                                        <option value="${b.budget_id}">${fn:escapeXml(b.name)}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td><input type="number" id="new_amount" class="form-control form-control-sm"
                                       placeholder="0.00"></td>
                            <td><input type="date" id="new_start_date" class="form-control form-control-sm"
                                       value="2026-03-31"></td>
                            <td>
                                <select id="new_frequency" class="form-select form-select-sm">
                                    <option value="12">Monthly</option>
                                    <option value="4">Quarterly</option>
                                    <option value="1">Annual</option>
                                    <option value="0">One-Time</option>
                                </select>
                            </td>
                            <td>
                                <div id="new_occurrence_input_container">
                                    <div class="input-group input-group-sm">
                                        <input type="number" id="new_occurrences" class="form-control form-control-sm"
                                               value="1">
                                        <button class="btn btn-outline-secondary border-start-0" type="button"
                                                onclick="setNewIndefinite(this)" title="Make Indefinite">
                                            <i class="bi bi-arrow-repeat"></i>
                                        </button>
                                    </div>
                                </div>
                                <div id="new_occurrence_infinite_container" class="d-none text-center">
                                    <button class="btn btn-sm btn-link text-primary p-0 text-decoration-none fw-bold"
                                            onclick="setNewIndefinite(this)">
                                        <i class="bi bi-infinity"></i> Indefinite
                                    </button>
                                </div>
                            </td>
                            <td><span class="badge bg-primary">New</span></td>
                            <td>
                                <button type="button" class="btn btn-sm btn-success w-100" id="addPlannedButton">Add
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <%-- Pagination Section --%>
            <div class="mt-4 d-flex justify-content-center align-items-center gap-3">
                <c:if test="${currentPage != 1}">
                    <form action="all-planned_transactions" method="get">
                        <input type="hidden" name="user_id" value="${user_id}">
                        <input type="hidden" name="subcategory_id" value="${subcategory_id}">
                        <input type="hidden" name="search" value="${results.search}">
                        <input type="hidden" name="page" value="${currentPage-1}">
                        <input type="submit" class="btn btn-outline-primary btn-sm" value="&laquo; Previous"/>
                    </form>
                </c:if>

                <form action="all-planned_transactions" method="get" class="d-flex align-items-center gap-2">
                    <input type="hidden" name="user_id" value="${user_id}">
                    <input type="hidden" name="subcategory_id" value="${subcategory_id}">
                    <input type="hidden" name="search" value="${results.search}">
                    <span>Page:</span>
                    <select name="page" class="form-select form-select-sm" style="width: auto;"
                            onchange="this.form.submit()">
                        <c:forEach var="i" begin="1" end="${noOfPages}">
                            <option value="${i}" ${currentPage == i ? 'selected' : ''}>${i}</option>
                        </c:forEach>
                    </select>
                </form>

                <c:if test="${currentPage lt noOfPages}">
                    <form action="all-planned_transactions" method="get">
                        <input type="hidden" name="user_id" value="${user_id}">
                        <input type="hidden" name="subcategory_id" value="${subcategory_id}">
                        <input type="hidden" name="search" value="${results.search}">
                        <input type="hidden" name="page" value="${currentPage+1}">
                        <input type="submit" class="btn btn-outline-primary btn-sm" value="Next &raquo;"/>
                    </form>
                </c:if>
            </div>
        </div>
    </div>
</div>

<%-- Modals and Toasts --%>


<div class="modal fade" id="deleteConfirmModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header border-0">
                <h5 class="modal-title">Confirm Deletion</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body text-muted">
                Are you sure you want to delete <strong id="deleteTargetName">this transaction</strong>?
            </div>
            <div class="modal-footer border-0">
                <button type="button" class="btn btn-light" data-bs-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-danger" id="confirmDeleteBtn">Delete Transaction</button>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp" %>