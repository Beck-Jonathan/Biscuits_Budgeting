<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class="container mt-4">
    <form method="post" action="${appURL}/editBudget" id="editbudget">
        <p hidden id="budgetID">${Budget.budget_id}</p>
        <div class="row mb-3">
            <h2>User ID: ${fn:escapeXml(Budget.user_id)}</h2>
        </div>

        <div class="row g-3">
            <div class="col-md-6">
                <label for="inputBudgetname" class="form-label">Name</label>
                <input type="text" class="form-control ${not empty results.budgetnameerror ? 'is-invalid' : ''}"
                       id="inputBudgetname" name="inputBudgetname" value="${fn:escapeXml(Budget.name)}">
            </div>
            <div class="col-md-6">
                <label for="inputBudgetdetails" class="form-label">Details</label>
                <input type="text" class="form-control ${not empty results.budgetdetailserror ? 'is-invalid' : ''}"
                       id="inputBudgetdetails" name="inputBudgetdetails" value="${fn:escapeXml(Budget.details)}">
            </div>
            <div class="col-md-4">
                <label for="inputBudgetstart_date" class="form-label">Start Date</label>
                <input type="date" class="form-control" id="inputBudgetstart_date" name="inputBudgetstart_date" value="${Budget.start_date}">
            </div>
            <div class="col-md-4">
                <label for="inputBudgetlimit_amount" class="form-label">Limit Amount</label>
                <input type="number" step="0.01" class="form-control" id="inputBudgetlimit_amount" name="inputBudgetlimit_amount" value="${Budget.limit_amount}">
            </div>
            <div class="col-md-4">
                <label for="inputBudgetcurrency_code_id" class="form-label">Currency</label>
                <select class="form-select" id="inputBudgetcurrency_code_id" name="inputBudgetcurrency_code_id">
                    <c:forEach items="${currency_codes}" var="cc">
                        <option value="${cc}" ${Budget.currency_code_id eq cc ? 'selected' : ''}>${cc}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="mt-4 d-grid">
            <button id="submitButton" class="btn btn-primary" type="submit">Update Budget Settings</button>
        </div>
    </form>

    <hr class="my-5">

    <div class="row">
        <div class="col-md-4">
            <div class="line_card p-3 shadow-sm mb-3">
                <h4 class="mb-3">Summary</h4>
                <p><strong>Total Items:</strong> <span id="totalCount">0</span></p>
                <p><strong>Limit:</strong> <span>$<span id="budgetLimit">${Budget.limit_amount}</span></span></p>
                <p><strong>Total Used:</strong> <span>$<span id="totalUsed">0.00</span></span></p>
                <p><strong>Total Remaining:</strong> <span>$<span id="totalRemaining">0.00</span></span></p>

            </div>
        </div>
        <div class="col-md-8">
            <div class="line_card p-3 shadow-sm mb-3" style="height: 300px;">
                <canvas id="budgetChart"></canvas>
            </div>
        </div>
    </div>

    <div class="line_card shadow-sm p-3">
        <h3>Line Items</h3>
        <div class="table-responsive">
            <table class="table table-hover align-middle" id="lineItemTable">
                <thead class="table-light">
                <tr>
                    <th>Name</th>
                    <th>Details</th>
                    <th>Amount</th>
                    <th>Date</th>
                    <th>Type</th>
                    <th>Status</th>
                    <th>Category</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody id="lineItemBody">
                <c:forEach items="${Budget.lines}" var="item">
                    <tr data-id="${item.budget_Line_Item_id}"
                        class="${item.budget_line_status_id eq 'inactive' ? 'row-inactive' : ''}">
                        <td class="editable" data-field="name">${fn:escapeXml(item.name)}</td>
                        <td class="editable" data-field="details">${fn:escapeXml(item.details)}</td>
                        <td class="editable" data-field="amount">${item.amount}</td>
                        <td class="editable" data-field="date">${item.line_item_date}</td>
                        <td data-field="type">
                            <select class="form-select form-select-sm table-edit-input" onchange="updateLineItem($(this).closest('tr'))">
                                <c:forEach items="${budget_line_types}" var="t">
                                    <option value="${t}" ${t == item.budget_line_type_id ? 'selected' : ''}>${t}</option>
                                </c:forEach>
                            </select>
                        </td>

                        <td data-field="status">
                            <select class="form-select form-select-sm table-edit-input" onchange="updateLineItem($(this).closest('tr'))">
                                <c:forEach items="${budget_line_status}" var="s">
                                    <option value="${s}" ${s == item.budget_line_status_id ? 'selected' : ''}>${s}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td class="editable-select"
                            data-field="color"
                            data-hex="${item.category.color_id}"
                            data-categoryid="${item.category.category_ID}">

                            <span style="color: ${item.category.color_id}; font-size: 1.5rem; line-height: 1; vertical-align: middle;">●</span>

                            <span class="color-label">${fn:escapeXml(item.category.category_Name)}</span>

                        </td>
                        <td>
                            <button class="btn btn-sm btn-outline-danger" type="button"
                                    onclick="deleteLineItem(this, '${item.budget_Line_Item_id}')">Delete</button>
                        </td>
                    </tr>



                </c:forEach>

                <tr id="inputRow" class="table-info">
                    <td><input type="text" id="name" class="form-control form-control-sm" placeholder="Name"></td>
                    <td><input type="text" id="details" class="form-control form-control-sm" placeholder="Details"></td>
                    <td><input type="number" id="amount" class="form-control form-control-sm" placeholder="0.00"></td>
                    <td><input type="date" id="date" class="form-control form-control-sm" value="${Budget.start_date}" /></td>
                    <td>
                        <select id="type" class="form-select form-select-sm">
                            <c:forEach items="${budget_line_types}" var="t">
                                <option value="${t}" ${t == 'Expense' ? 'selected' : ''}>${t}</option></c:forEach>
                        </select>
                    </td>
                    <td>
                        <select id="status" class="form-select form-select-sm">
                            <c:forEach items="${budget_line_status}"
                                       var="s"><option value="${s}" ${s == 'Pending' ? 'selected' : ''}> ${s}</option></c:forEach>
                        </select>
                    </td>
                    <td class="d-flex align-items-center">
                        <span id="colorPreview" style="font-size: 1.5rem; line-height: 1; margin-right: 10px; vertical-align: middle;">●</span>

                        <select id="color" name="inputcategory_id" class="form-select form-select-sm">
                            <c:forEach items="${categories}" var="category">
                                <option value="${category.category_ID}" data-color="${category.color_id}">
                                        ${fn:escapeXml(category.category_Name)}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                    <td><button type="button" class="btn btn-sm btn-success w-100" id="addButton">Add</button></td>
                </tr>
                </tbody>
                <tfoot>
                <div class="d-flex justify-content-end mb-3">
                    <button type="button" class="btn btn-info" onclick="saveTheRest()">
                        <i class="bi bi-piggy-bank"></i> Save the Rest
                    </button>
                </div>
                </tfoot>
            </table>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>