<%--************
Create the JSP  For Viewing All of The  Mortgage table
 Created By Jonathan Beck 8/6/2024
**********--%>
<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class = "container">
    <div class="row">
        <div class="col-12">
            <h1>All Biscuit Mortgages</h1>
            <p>There ${Mortgages.size() eq 1 ? "is" : "are"}&nbsp;${Mortgages.size()} Mortgage${Mortgages.size() ne 1 ? "s" : ""}</p>
            Add Mortgage   <a href="calculateMortgage">Add</a>
            <c:if test="${Mortgages.size() > 0}">
                <div class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>

                        <th scope="col">Nickname</th>
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
                        <tr id="${mortgage.mortgage_ID}_row">

                            <td>${fn:escapeXml(mortgage.nickname)}</td>
                            <td>${fn:escapeXml(mortgage.present_Value)}</td>
                            <td>${fn:escapeXml(mortgage.future_Value)}</td>
                            <td>${fn:escapeXml(mortgage.interest_Rate)}</td>
                            <td>${fn:escapeXml(mortgage.monthly_Payment)}</td>
                            <td>${fn:escapeXml(mortgage.extra_Payment)}</td>
                            <td>${fn:escapeXml(mortgage.remaining_Term)}</td>
                            <td><a href = "editmortgage?mortgageid=${mortgage.mortgage_ID}&mode=edit" > Edit </a></td>
                            <td>
                                <div>
                                    <button class="delButton" href="${mortgage.mortgage_ID}" >Delete</button> </div>
                                <div style="display: none;" id="mortgage.mortgage_IDStatus"></div>
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
<div id="dialog" title="Confirmation Required">
    Are you sure about this?
</div>
</main>
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>

