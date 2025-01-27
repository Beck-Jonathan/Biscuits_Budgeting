<%--************
Create the JSP  For Viewing All of The  Mortgage table
 Created By Jonathan Beck 8/6/2024
**********--%>
<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class = "container">
    <div class="row">
        <div class="col-12">
            <h1>All Roller Mortgages</h1>
            <p>There ${Mortgages.size() eq 1 ? "is" : "are"}&nbsp;${Mortgages.size()} Mortgage${Mortgages.size() ne 1 ? "s" : ""}</p>
            Add Mortgage   <a href="calculateMortgage">Add</a>
            <c:if test="${Mortgages.size() > 0}">
                <div class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">Mortgage_ID</th>
                        <th scope="col">User_ID</th>
                        <th scope="col">Present_Value</th>
                        <th scope="col">Future_Value</th>
                        <th scope="col">Interest_Rate</th>
                        <th scope="col">Monthly_Payment</th>
                        <th scope="col">Extra_Payment</th>
                        <th scope="col">Remaining_Term</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${Mortgages}" var="mortgage">
                        <tr>
                            <td><a href = "editmortgage?mortgageid=${mortgage.mortgage_ID}&mode=view">${fn:escapeXml(mortgage.mortgage_ID)}</a></td><td>${fn:escapeXml(mortgage.user_ID)}</td>
                            <td>${fn:escapeXml(mortgage.present_Value)}</td>
                            <td>${fn:escapeXml(mortgage.future_Value)}</td>
                            <td>${fn:escapeXml(mortgage.interest_Rate)}</td>
                            <td>${fn:escapeXml(mortgage.monthly_Payment)}</td>
                            <td>${fn:escapeXml(mortgage.extra_Payment)}</td>
                            <td>${fn:escapeXml(mortgage.remaining_Term)}</td>
                            <td><a href = "editmortgage?mortgageid=${mortgage.mortgage_ID}&mode=edit" > Edit </a></td>
                            <td><a href = "deletemortgage?mortgageid=${mortgage.mortgage_ID}&mode=<c:choose><c:when test="${mortgage.is_active}">0</c:when>
						<c:otherwise>1</c:otherwise>
						</c:choose>">
                                <c:if test="${!mortgage.is_active}">un</c:if>Delete </a></td>
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

