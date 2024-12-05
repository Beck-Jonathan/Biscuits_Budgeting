<%--************
Create the JSP  For Viewing All of The  Event table
 Created By Jonathan Beck3/22/2024
**********--%>
<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>
<div class = "container">
    <div class="row">
        <div class="col-12">
            <h1>All Roller Events</h1>
            <p>There ${Events.size() eq 1 ? "is" : "are"}&nbsp;${Events.size()} Event${Events.size() ne 1 ? "s" : ""}</p>
            Add Event   <a href="addEvent">Add</a>
            <c:if test="${Events.size() > 0}">
                <div class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">Event_ID</th>
                        <th scope="col">Facility</th>
                        <th scope="col">Date</th>
                        <th scope="col">Type</th>
                        <th scope="col">is_active</th>
                <c:if test="${User.privilege_ID eq 2}">
                    <th scope="col">Join</th>
                </c:if>
                <c:if test="${User.privilege_ID > 2}">
                    <th scope="col">Edit</th>
                    <th scope="col">Delete</th>
                </c:if>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${Events}" var="event">
                        <tr>
                            <td><a href = "editevent?eventid=${event.event_ID}&mode=view">${event.event_ID}</a></td>
                            <td>${event.facility.name}</td>
                            <td>${event.date}</td>
                            <td>${event.type.name}</td>
                            <td><input type="checkbox" disabled <c:if test="${event.is_active}">checked</c:if>></td>
                            <c:if test="${User.privilege_ID eq 2}">
                            <td><a href = "joinevent?eventid=${event.event_ID}&mode=join" > Join</a>
                            </c:if>
                            <c:if test="${User.privilege_ID > 2}">
                            <td><a href = "editevent?eventid=${event.event_ID}&mode=edit" > Edit </a></td>
                            <td><a href = "deleteevent?eventid=${event.event_ID}&mode=<c:choose><c:when test="${event.is_active}">0</c:when>
						<c:otherwise>1</c:otherwise>
						</c:choose>">
                                <c:if test="${!event.is_active}">un</c:if>Delete </a></td>
                            </c:if>
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
<%@include file="/WEB-INF/personal-project/personal_bottom.jsp"%>

