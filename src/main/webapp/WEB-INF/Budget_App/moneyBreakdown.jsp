<%--************
Create the JSP For Viewing All of The Category table
Created By Jonathan Beck 7/30/2024
Updated with Bank Filter 12/14/2024
**********--%>
<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class="container">
    <div class="row mb-4">
        <div class="col-12">
            <h1>Budget Breakdown</h1>

            <div class="card p-3 mb-3">
                <div class="row align-items-end">
                    <div class="col-md-3">
                        <label class="form-label font-weight-bold">Bank Account</label>
                        <select id="bankAccountSelect" class="form-select">
                            <option value="">**ANY** </option>
                            <c:forEach var="account" items="${bankAccounts}">
                                <option value="${account.bank_Account_ID}">${account.account_Nickname}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-4" id="yearSelectorContainer" style="display:none;">
                        <label class="form-label font-weight-bold">Year</label>
                        <input type="number" id="inputYear" class="form-control" value="2024">
                    </div>
                </div>
            </div>

            <p>There ${breakdown.size() eq 1 ? "is" : "are"}&nbsp;${breakdown.size()} Year${breakdown.size() ne 1 ? "s" : ""}</p>
            <p id="restore" class="btn btn-sm btn-link" onclick="restorecells()">restore</p>
            Add Category <a href="addTransactionCategory" class="btn btn-sm btn-outline-success">Add</a>

            <c:if test="${breakdown.size() > 0}">
                <div class="table-responsive">
                    <table id="moneyTable" class="table table-bordered">
                        <thead>
                        <tr>
                            <c:forEach var="i" begin="0" end="${breakdown.size()-2}">
                                <th>${breakdown[0][0].year+i}<p class="d-inline ms-2 text-danger" style="cursor:pointer" onclick="hidecol('${i}')">x</p></th>
                            </c:forEach>
                            <th scope="col">Total</th>
                            <th scope="col">Category</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="k" begin="0" end="${breakdown[0].size()-1}">
                            <tr id="${breakdown[0][k].category_ID}Row">
                                <c:forEach var="l" begin="0" end="${breakdown.size()-2}">
                                    <td>
                                        <a href="all-Transactions?category=${breakdown[0][k].category_ID}&year=${breakdown[0][0].year+l}">
                                            $ ${breakdown[l][k].amount} <br/> Count:${breakdown[l][k].count}
                                        </a>
                                    </td>
                                </c:forEach>
                                <td>
                                    <a href="all-Transactions?category=${breakdown[0][k].category_ID}">
                                        $ ${breakdown[breakdown.size()-1][k].amount} <br/> Count:${breakdown[breakdown.size()-1][k].count}
                                    </a>
                                </td>
                                <td>${breakdown[0][k].category_ID}</td>
                                <td class="xbutton text-danger" style="cursor:pointer" onclick="hiderow('${breakdown[0][k].category_ID}Row')">x</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>
    </div>

    <div class="row">
        <div class="col-md-8"><div id="chartContainer"></div></div>
        <div class="col-md-4"><div id="pieContainer"></div></div>
    </div>
</div>
</main>
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>