<%--************
Create the JSP  For Viewing All of The  Suggestion table
 Created By Jonathan Beck 5/1/2025
**********--%>
<%@include file="/WEB-INF/Budget_App/budget_top.jsp"%>
<div class = "container">
    <div class="row">
        <div class="col-12">
            <h1>All schedule_app Suggestions</h1>
            <p>There ${Suggestions.size() eq 1 ? "is" : "are"}&nbsp;${Suggestions.size()} Suggestion${Suggestions.size() ne 1 ? "s" : ""}</p>
            Add Suggestion   <a href="addSuggestion">Add</a>
            <c:if test="${Suggestions.size() > 0}">
                <div class="search-container">
                    <form action="all-Suggestion">
                        <input type="text" placeholder="Search.." id="searchBox" name="search">
                        <button type="submit"><i class="fa fa-search"></i></button>
                    </form>
                </div>
                Export Suggestion   <a href="exportSuggestion?mode=export">Add</a>
                Write To SQL File Suggestion   <a href="exportSuggestion?mode=SQL">Add</a>
                <div class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col"> Details </th>
                        <th scope="col">User_ID</th>
                        <th scope="col">Application_Name</th>
                        <th scope="col">content</th>

                        <th scope="col">Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${Suggestions}" var="suggestion">
                        <tr id="${suggestion.suggestion_ID}row">
                            <td>
                                <button class="detailButton" href="${suggestion.suggestion_ID}" >Details</button>
                            </td>
                            <td>${fn:escapeXml(suggestion.user.user_Name)}</td>
                            <td>${fn:escapeXml(suggestion.application_Name)}</td>
                            <td>${fn:escapeXml(suggestion.content)}</td>
                            <td>
                                <div>
                                <button class="delButton" href="${suggestion.suggestion_ID}" >Delete</button>
                                </div>
                                <div style="display: none;" id="event.event_IDStatus">

                                </div>
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

