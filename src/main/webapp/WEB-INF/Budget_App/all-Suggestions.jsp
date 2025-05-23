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
                    <form action="all-Suggestions">
                        <input type="text" placeholder="Search.." id="searchBox" name="search">
                        <select   id="inputsuggestionApplication_Name" name="App" >
                            <c:forEach items="${Applications}" var="Application">
                                <option value="${Application}">${Application}   </option>
                            </c:forEach>
                        </select>
                        <button type="submit"><i class="fa fa-search">search & filter</i></button>

                    </form>
                </div>
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
<%--For displaying Previous link except for the 1st page --%>
<c:if test="${suggestion_page_number != 1}">
    <form action="all-Suggestions" method="get">
        <input type="hidden" name="user_id" value="${user_id}">
        <input type="hidden" name="application_name" value="${application_name}">
        <input type="hidden" name="suggestion_page" value="${suggestion_page_number-1}">
        <br/><br/>
        <input type="submit" value="Previous Page" />
    </form>
</c:if>
<%--For displaying Page numbers.
The when condition does not display a link for the current page--%><form action="all-Suggestions" method="get" ><input type="hidden" name="user_id" value="${user_id}">
    <input type="hidden" name="application_name" value="${application_name}">
    Select a page :
    <select name="suggestion_page" onchange="this.form.submit()">
        <c:forEach var="i" begin="1" end="${noOfPages}">
            <option value=${i}  ${suggestion_page_number == i ? ' selected' : ''} >${i}</option>
        </c:forEach>
    </select>
    <br/><br/>
    <input type="submit" value="Submit" />
</form>
<%--For displaying Next link --%>
<c:if test="${suggestion_page_number lt noOfPages}">
    <form action="all-Suggestions" method="get">
        <input type="hidden" name="user_id" value="${user_id}">
        <input type="hidden" name="application_name" value="${application_name}">
        <input type="hidden" name="suggestion_page" value="${suggestion_page_number+1}">
        <br/><br/>
        <input type="submit" value="Next Page" />
    </form>
</c:if>
<div id="dialog" title="Confirmation Required">
    Are you sure about this?
</div>

</main>
<%@include file="/WEB-INF/Budget_App/budget_bottom.jsp"%>

