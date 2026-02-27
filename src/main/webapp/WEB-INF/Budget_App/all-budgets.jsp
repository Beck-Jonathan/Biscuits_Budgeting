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
                Export budget   <a href="exportbudget?mode=export">Add</a>
                Write To SQL File budget   <a href="exportbudget?mode=SQL">Add</a>
                <div class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col"> Details </th>
                        <th scope="col">Owner</th>
                        <th scope="col">name</th>
                        <th scope="col">Role</th>

                        <th scope="col">details</th>
                        <th scope="col">start_date</th>
                        <th scope="col">limit_amount</th>
                        <th scope="col">currency_code_id</th>
                        <th scope="col">is_active</th>
                        <th scope="col">created_at</th>
                        <th scope="col">updated_at</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${budgets}" var="budget">
                        <tr id="${budget.budget_id}row">
                            <td><a href = "editBudget?budgetid=${budget.budget_id}&mode=view"> Details </a></td>
                            <td>${fn:escapeXml(budget.user.user_name)}</td>
                            <td>${fn:escapeXml(budget.name)}</td>
                            <td>${fn:escapeXml(budget.user_line.budget_role_id)}</td>
                            <td>${fn:escapeXml(budget.details)}</td>
                            <td>${fn:escapeXml(budget.start_date)}</td>
                            <td>${fn:escapeXml(budget.limit_amount)}</td>
                            <td>${fn:escapeXml(budget.currency_code_id)}</td>
                            <td><input type="checkbox" id="${budget.is_active}" disabled <c:if test="${budget.is_active}">checked</c:if>></td>
                            <td>${fn:escapeXml(budget.created_at)}</td>
                            <td>${fn:escapeXml(budget.updated_at)}</td>
                            <td><a href = "editBudget?budgetid=${budget.budget_id}&mode=edit" > Edit </a></td>
                            <td><a href = "deleteBudget?budgetid=${budget.budget_id}&mode=<c:choose><c:when test="${budget.is_active}">0</c:when>
						<c:otherwise>1</c:otherwise>
						</c:choose>">
                                <c:if test="${!budget.is_active}">un</c:if>Delete </a></td>
                            <td>
                                <div>
                                    <button class="delButton" href="${budget.budget_id}" >Delete</button> </div>
                                <div style="display: none;" id="${budget.budget_id}Status"></div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
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

