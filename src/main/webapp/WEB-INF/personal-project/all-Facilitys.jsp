<%--************
Create the JSP  For Viewing All of The  Facility table
 Created By Jonathan Beck3/18/2024
**********--%>
<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>
<div class = "container">
  <div class="row">
    <div class="col-12">
      <h1>All Roller Facilitys</h1>
      <p>There ${Facilitys.size() eq 1 ? "is" : "are"}&nbsp;${Facilitys.size()} Facility${Facilitys.size() ne 1 ? "s" : ""}</p>

      <c:if test="${User.privilege_ID > 2}">
      Add Facility   <a href="addFacility">Add</a>
      </c:if>
      <c:if test="${Facilitys.size() > 0}">
        <div class="table-responsive"><table class="table table-bordered">
          <thead>
          <tr>
            <th scope="col">Facility_ID</th>
            <th scope="col">Name</th>
            <th scope="col">Addresss</th>
            <th scope="col">City</th>
            <th scope="col">State</th>
            <th scope="col">Zip</th>
            <th scope="col">is_active</th>
            <c:if test="${User.privilege_ID > 1}">
            <th scope="col">Edit</th>
            <th scope="col">Delete</th>
            </c:if>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${Facilitys}" var="facility">
            <tr>
              <td><a href = "editfacility?facilityid=${facility.facility_ID}&mode=view">${fn:escapeXml(facility.facility_ID)}</a></td><td>${fn:escapeXml(facility.name)}</td>              <td>${facility.name}</td>
              <td>${facility.addresss}</td>
              <td>${facility.city}</td>
              <td>${facility.state}</td>
              <td>${facility.zip}</td>
              <td><input type="checkbox" disabled <c:if test="${facility.is_active}">checked</c:if>></td>
              <c:if test="${User.privilege_ID > 1}">
              <td><a href = "editfacility?facilityid=${facility.facility_ID}" > Edit </a></td>
              <td><a href = "deletefacility?facilityid=${facility.facility_ID}&mode=<c:choose><c:when test="${facility.is_active}">0</c:when>
						<c:otherwise>1</c:otherwise>
						</c:choose>">
                <c:if test="${!facility.is_active}">un</c:if>Delete </a></td>
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

