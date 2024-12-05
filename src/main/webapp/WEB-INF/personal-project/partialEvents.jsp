<h1>All Roller Events for ${User.user_Name}</h1>
<p>There ${Events.size() eq 1 ? "is" : "are"}&nbsp;${Events.size()} Event${Events.size() ne 1 ? "s" : ""}</p>

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



        </tr>
        </thead>
        <tbody>
        <c:forEach items="${Events}" var="event">
        <tr>
            <td><a href = "editevent?eventid=${event.event_ID}&mode=view">${fn:escapeXml(event.event_ID)}</a></td>
            <td>${event.facility.name}</td>
            <td>${event.date}</td>
            <td>${event.type.name}</td>
            <td><input type="checkbox" disabled <c:if test="${event.is_active}">checked</c:if>></td>
            <c:if test="${User.privilege_ID eq 2}">
            <td><a href = "joinevent?eventid=${event.event_ID}&mode=join" > Join </a></td>
                </c:if>



            </c:forEach>
        </tbody>
    </table>
    </div>
</c:if>
