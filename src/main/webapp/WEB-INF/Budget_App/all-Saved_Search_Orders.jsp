<%--************
Create the JSP  For Viewing All of The  Saved_Search_Order table
 Created By Jonathan Beck 2/4/2025
**********--%>
<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class = "container">
    <div class="row">
        <div class="col-12">
            <h1>All Roller Saved_Search_Orders</h1>
            <p>There ${Saved_Search_Orders.size() eq 1 ? "is" : "are"}&nbsp;${Saved_Search_Orders.size()} Saved_Search_Order${Saved_Search_Orders.size() ne 1 ? "s" : ""}</p>
            Add Saved_Search_Order   <a href="addSaved_Search_Order">Add</a>
            <c:if test="${Saved_Search_Orders.size() > 0}">
                <div class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">Saved_Search_Order_ID</th>
                        <th scope="col">Owned_User</th>
                        <th scope="col">Nickname</th>
                        <th scope="col">Description</th>
                        <th scope="col">Last_Used</th>
                        <th scope="col">Last_Updated</th>
                        <th scope="col">Times_Ran</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${Saved_Search_Orders}" var="saved_search_order">
                        <tr>
                            <td><a href = "editsaved_search_order?saved_search_orderid=${saved_search_order.saved_search_order_ID}&mode=view">${fn:escapeXml(saved_search_order.saved_search_order_ID)}</a></td><td>${fn:escapeXml(saved_search_order.owned_User)}</td>
                            <td>${fn:escapeXml(saved_search_order.nickname)}</td>
                            <td>${fn:escapeXml(saved_search_order.description)}</td>
                            <td>${fn:escapeXml(saved_search_order.last_Used)}</td>
                            <td>${fn:escapeXml(saved_search_order.last_Updated)}</td>
                            <td>${fn:escapeXml(saved_search_order.times_Ran)}</td>
                            <td><a href = "editsaved_search_order?saved_search_orderid=${saved_search_order.saved_search_order_ID}&mode=edit" > Edit </a></td>
                            <td><a href = "deletesaved_search_order?saved_search_orderid=${saved_search_order.saved_search_order_ID}&mode=<c:choose><c:when test="${saved_search_order.is_active}">0</c:when>
						<c:otherwise>1</c:otherwise>
						</c:choose>">
                                <c:if test="${!saved_search_order.is_active}">un</c:if>Delete </a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </div>
            </c:if>
        </div>
    </div>
</div>
</main>
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>

