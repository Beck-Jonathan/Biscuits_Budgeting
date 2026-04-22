<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>

<div class="container-fluid mt-3 px-4">
    <%-- Header Section --%>
    <div class="row mb-3">
        <div class="col-12 d-flex justify-content-between align-items-center">
            <div>
                <h1 class="h3 mb-1">Schedule App Suggestions</h1>
                <span class="badge bg-dark">${Suggestions.size()} Total Records</span>
            </div>
            <div class="btn-group">
                <a href="addSuggestion" class="btn btn-sm btn-outline-primary">+ Add New Suggestion</a>
            </div>
        </div>
    </div>

    <%-- Filter Toolbar --%>
    <div class="row mb-4">
        <div class="col-12">
            <div class="filter-toolbar p-3 bg-light rounded border shadow-sm">
                <form action="all-Suggestions" method="get" class="row g-3 align-items-end">
                    <div class="col-md-4">
                        <label class="small fw-bold text-muted text-uppercase">Search</label>
                        <input type="text" name="search" class="form-control rounded-pill shadow-sm"
                               placeholder="Search content..." value="${param.search}">
                    </div>
                    <div class="col-md-4">
                        <label class="small fw-bold text-muted text-uppercase">Application</label>
                        <select name="App" class="form-select rounded-pill shadow-sm">
                            <option value="">All Applications</option>
                            <c:forEach items="${Applications}" var="Application">
                                <option value="${Application}" ${param.App == Application ? 'selected' : ''}>${Application}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-2">
                        <button type="submit" class="btn btn-primary w-100 rounded-pill shadow-sm">Filter</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <%-- Table Section --%>
    <div class="row">
        <div class="col-12">
            <c:if test="${not empty Suggestions}">
                <div class="table-responsive shadow-sm bg-white rounded border">
                    <table class="table table-hover align-middle mb-0">
                        <thead class="table-dark">
                        <tr>
                            <th>Details</th>
                            <th>User</th>
                            <th>Application</th>
                            <th>Content</th>
                            <th class="text-center">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${Suggestions}" var="suggestion">
                            <tr id="${suggestion.suggestion_ID}row">
                                <td>
                                    <button class="btn btn-sm btn-outline-info detailButton"
                                            href="${suggestion.suggestion_ID}">Details
                                    </button>
                                </td>
                                <td>${fn:escapeXml(suggestion.user.user_Name)}</td>
                                <td>${fn:escapeXml(suggestion.application_Name)}</td>
                                <td class="text-truncate" style="max-width: 300px;"
                                    title="${fn:escapeXml(suggestion.content)}">
                                        ${fn:escapeXml(suggestion.content)}
                                </td>
                                <td class="text-center">
                                    <button class="btn btn-sm btn-outline-danger delButton"
                                            href="${suggestion.suggestion_ID}">Delete
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
            <c:if test="${empty Suggestions}">
                <div class="alert alert-info">No suggestions found.</div>
            </c:if>
        </div>
    </div>

    <%-- Pagination --%>
    <div class="row mt-4 mb-5">
        <div class="col-12 d-flex justify-content-between align-items-center">
            <span class="text-muted small">Page ${suggestion_page_number} of ${noOfPages}</span>
            <form action="all-Suggestions" method="get" class="d-flex align-items-center">
                <%-- Hidden fields to preserve filters during pagination --%>
                <input type="hidden" name="search" value="${param.search}">
                <input type="hidden" name="App" value="${param.App}">

                <nav aria-label="Page navigation">
                    <ul class="pagination pagination-sm mb-0">
                        <li class="page-item ${suggestion_page_number <= 1 ? 'disabled' : ''}">
                            <button type="submit" name="suggestion_page" value="${suggestion_page_number - 1}"
                                    class="page-link">Prev
                            </button>
                        </li>
                        <li class="page-item mx-2">
                            <select name="suggestion_page" class="form-select form-select-sm"
                                    onchange="this.form.submit()">
                                <c:forEach var="i" begin="1" end="${noOfPages}">
                                    <option value="${i}" ${suggestion_page_number == i ? 'selected' : ''}>${i}</option>
                                </c:forEach>
                            </select>
                        </li>
                        <li class="page-item ${suggestion_page_number >= noOfPages ? 'disabled' : ''}">
                            <button type="submit" name="suggestion_page" value="${suggestion_page_number + 1}"
                                    class="page-link">Next
                            </button>
                        </li>
                    </ul>
                </nav>
            </form>
        </div>
    </div>
</div>

<%-- Modal for JS triggers --%>
<div id="dialog" title="Confirmation Required" style="display:none;">
    Are you sure about this?
</div>

<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>