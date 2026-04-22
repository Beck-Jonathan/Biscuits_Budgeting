<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>

<div class="container-fluid mt-3 px-4">
    <%-- Header Section --%>
    <div class="row mb-3">
        <div class="col-12 d-flex justify-content-between align-items-center">
            <div>
                <h1 class="h3 mb-1">Saved Search Orders</h1>
                <span class="badge bg-dark">${Saved_Search_Orders.size()} Total Records</span>
            </div>
            <div class="btn-group">
                <a href="addSaved_Search_Order" class="btn btn-sm btn-outline-primary">+ Add New Order</a>
            </div>
        </div>
    </div>

    <%-- Data Table --%>
    <div class="row">
        <div class="col-12">
            <c:if test="${not empty Saved_Search_Orders}">
                <div class="table-responsive shadow-sm bg-white rounded border">
                    <table class="table table-hover align-middle mb-0">
                        <thead class="table-dark">
                        <tr>
                            <th>View</th>

                            <th>Nickname</th>
                            <th>Description</th>
                            <th>Last Used</th>
                            <th>Last Updated</th>
                            <th>Times Ran</th>
                            <th class="text-center">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${Saved_Search_Orders}" var="saved_search_order">
                            <tr>
                                <td>
                                    <a href="editSaved_Search_Order?saved_search_orderid=${saved_search_order.saved_Search_Order_ID}&mode=view"
                                       class="btn btn-sm btn-outline-info">
                                        View
                                    </a>
                                </td>

                                <td>${fn:escapeXml(saved_search_order.nickname)}</td>
                                <td class="text-truncate" style="max-width: 200px;"
                                    title="${fn:escapeXml(saved_search_order.description)}">
                                        ${fn:escapeXml(saved_search_order.description)}
                                </td>
                                <td>${fn:escapeXml(saved_search_order.last_Used)}</td>
                                <td>${fn:escapeXml(saved_search_order.last_Updated)}</td>
                                <td><span class="badge bg-light text-dark border">${saved_search_order.times_Ran}</span>
                                </td>
                                <td class="text-center">
                                    <!-- <a href="deletesaved_search_order?saved_search_orderid=${saved_search_order.saved_Search_Order_ID}"
                                       class="btn btn-sm btn-outline-danger"
                                       onclick="return confirm('Are you sure you want to delete this order?');">
                                        <i class="bi bi-trash"></i> Delete
                                    </a> -->
                                    <form method="post" action="${appURL}/apply-Saved_Search_Order">
                                        <input type="hidden" name="saved_search_orderid"
                                               value="${saved_search_order.saved_Search_Order_ID}">
                                        <button class="btn btn-success shadow-sm" type="submit">
                                            <i class="bi bi-play-fill"></i> Apply Sorting Now
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>

            <c:if test="${empty Saved_Search_Orders}">
                <div class="alert alert-info">
                    No saved search orders found.
                </div>
            </c:if>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>