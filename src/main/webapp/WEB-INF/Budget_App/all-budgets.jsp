<%--************
Create the JSP  For Viewing All of The  budget table
 Created By Jonathan Beck 2/26/2026
**********--%>
<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class = "container">
    <div class="row">
        <div class="col-12">
            <h1>All budget_app budgets</h1>
            <p>There ${budgets.size() eq 1 ? "is" : "are"}&nbsp;${count}&nbsp;budgets</p>
            Add budget   <a href="addbudget">Add</a>
            <c:if test="${budgets.size() > 0}">
                <div class="search-container">
                    <form action="all-budgets">
                        <input type="text" placeholder="Search.." id="searchBox" name="search">
                        <label for="inputbudgetuser_id" class="form-label">user_id</label>
                        <div class="input-group input-group-lg">
                            <select  class="<c:if test="${not empty results.budgetuser_iderror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="user_id" id="inputbudgetuser_id" name="inputbudgetuser_id" value="${fn:escapeXml(results.user_id)}">
                                <c:forEach items="${users}" var="user">
                                    <option value="${user.user_ID}">${user.name}   </option>
                                </c:forEach>
                            </select>
                            <c:if test="${not empty results.budgetuser_iderror}">
                                <div class="invalid-feedback">${results.budgetuser_iderror}</div>
                            </c:if>
                        </div>
                        <label for="inputbudgetcurrency_code_id" class="form-label">currency_code_id</label>
                        <div class="input-group input-group-lg">
                            <select  class="<c:if test="${not empty results.budgetcurrency_code_iderror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="currency_code_id" id="inputbudgetcurrency_code_id" name="inputbudgetcurrency_code_id" value="${fn:escapeXml(results.currency_code_id)}">
                                <c:forEach items="${currency_codes}" var="currency_code">
                                    <option value="${currency_code}">${currency_code}   </option>
                                </c:forEach>
                            </select>
                            <c:if test="${not empty results.budgetcurrency_code_iderror}">
                                <div class="invalid-feedback">${results.budgetcurrency_code_iderror}</div>
                            </c:if>
                        </div>
                        <button type="submit"><i class="fa fa-search"search></i></button>
                    </form>
                </div>

            <div class="row row-cols-1 row-cols-md-2 row-cols-xl-3 g-4">
                <c:forEach items="${budgets}" var="b">
                    <div class="col-md-3" id="${b.budget_id}Card">
                        <div class="budget_card card h-100 shadow-sm border-0 position-relative">
                            <div class="card-header bg-white d-flex justify-content-between align-items-center border-0 pt-3">
                                <h5 class="card-title mb-0 fw-bold text-primary">${fn:escapeXml(b.name)}</h5>
                                <span class="badge ${b.is_active ? 'bg-success' : 'bg-secondary'}">
                                        ${b.is_active ? 'Active' : 'Inactive'}
                                </span>
                            </div>

                            <div class="card-body">
                                <p class="text-muted small">${fn:escapeXml(b.details)}</p>

                                <div class="d-flex justify-content-between mb-1">
                                    <span class="small fw-bold">Spent: $${b.totalSpent}</span>
                                    <span class="small text-muted">Limit: $${b.limit_amount} ${b.currency_code_id}</span>
                                </div>

                                <div class="progress mb-3" style="height: 20px; background-color: #e9ecef; border-radius: 8px; overflow: hidden;">
                                    <c:forEach items="${b.lines}" var="line">
                                        <c:set var="itemPercentage" value="${(line.amount / b.limit_amount) * 100}" />

                                        <c:if test="${itemPercentage > 0 && line.budget_line_status_id!='Inactive'}">
                                            <div class="progress-bar progress-bar-item"
                                                 role="progressbar"
                                                 style="width: ${itemPercentage}%; background-color: ${line.category.color_id};"
                                                 title="${fn:escapeXml(line.name)}: $${line.amount}"
                                                 data-bs-toggle="tooltip"
                                                 data-bs-placement="top">
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
                                        <div class="badge bg-light text-dark border"><!--b.user_line.role-!--></div>
                                    </div>
                                </div>
                            </div>

                            <div class="card-footer bg-light border-0 d-flex justify-content-around pb-3">
                                <a href="editBudget?budgetid=${b.budget_id}&mode=view" class="btn btn-sm btn-outline-primary stretched-link" style="z-index: 3;">Details</a>
                                <a href="editBudget?budgetid=${b.budget_id}&mode=edit" class="btn btn-sm btn-outline-secondary" style="z-index: 3;">Edit</a>
                                <button class="btn btn-sm btn-outline-danger delButton" data-id="${b.budget_id}" style="z-index: 3;">Delete</button>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
                </div>
            </c:if>
        </div>
    </div>
</div>
<%--For displaying Previous link except for the 1st page --%>
<c:if test="${currentPage != 1}">
    <form action="all-budgets" method="get">
        <input type="hidden" name="user_id" value="${user_id}">
        <input type="hidden" name="currency_code_id" value="${currency_code_id}">
        <input type = hidden name = "search" value = "${results.search}">
        <input type="hidden" name="page" value="${currentPage-1}">
        <br/><br/>
        <input type="submit" value="Previous Page" />
    </form>
</c:if>
<%--For displaying Page numbers.
The when condition does not display a link for the current page--%>
<form action="all-budgets" method="get" >
    <input type="hidden" name="user_id" value="${user_id}">
    <input type="hidden" name="currency_code_id" value="${currency_code_id}">
    <input type = hidden name = "search" value = "${results.search}">
    Select a page :
    <select name="page" onchange="this.form.submit()">
        <c:forEach var="i" begin="1" end="${noOfPages}">
            <option value=${i}  ${currentPage == i ? ' selected' : ''} >${i}</option>
        </c:forEach>
    </select>
    <br/><br/>
    <input type="submit" value="Submit" />
</form>
<%--For displaying Next link except on the last page --%>
<c:if test="${currentPage lt noOfPages}">
    <form action="all-Transactions" method="get">
        <input type="hidden" name="user_id" value="${user_id}">
        <input type="hidden" name="currency_code_id" value="${currency_code_id}">
        <input type = hidden name = "search" value = "${results.search}">
        <input type="hidden" name="page" value="${currentPage+1}">
        <br/><br/>
        <input type="submit" value="Next Page" />
    </form>
</c:if>
<div id="dialog" title="Confirmation Required">
    Are you sure about this?
</div>
</main>
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>

