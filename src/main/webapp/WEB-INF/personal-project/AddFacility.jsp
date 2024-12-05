<%--************
Create the JSP  For adding to The  Facility table
 Created By Jonathan Beck3/18/2024
**********--%>
<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>
<div class = "container">
  <form method="post" action="${appURL}/addFacility" id = "addFacility" >
    <!-- Name -->
    <div class ="row" id = "row0">
      <label for="inputfacilityName" class="form-label">Name</label>
      <div class="input-group input-group-lg">
        <input type="text" class="<c:if test="${not empty results.facilityNameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Name" id="inputfacilityName" name="inputfacilityName" value="${results.Name}">
        <c:if test="${not empty results.facilityNameerror}">
          <div class="invalid-feedback">${results.facilityNameerror}</div>
        </c:if>
      </div>
    </div>
    <!-- Addresss -->
    <div class ="row" id = "row1">
      <label for="inputfacilityAddresss" class="form-label">Addresss</label>
      <div class="input-group input-group-lg">
        <input type="text" class="<c:if test="${not empty results.facilityAddressserror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Addresss" id="inputfacilityAddresss" name="inputfacilityAddresss" value="${results.Addresss}">
        <c:if test="${not empty results.facilityAddressserror}">
          <div class="invalid-feedback">${results.facilityAddressserror}</div>
        </c:if>
      </div>
    </div>
    <!-- City -->
    <div class ="row" id = "row2">
      <label for="inputfacilityCity" class="form-label">City</label>
      <div class="input-group input-group-lg">
        <input type="text" class="<c:if test="${not empty results.facilityCityerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="City" id="inputfacilityCity" name="inputfacilityCity" value="${results.City}">
        <c:if test="${not empty results.facilityCityerror}">
          <div class="invalid-feedback">${results.facilityCityerror}</div>
        </c:if>
      </div>
    </div>
    <!-- State -->
    <div class ="row" id = "row3">
      <label for="inputfacilityState" class="form-label">State</label>
      <div class="input-group input-group-lg">
        <input type="text" class="<c:if test="${not empty results.facilityStateerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="State" id="inputfacilityState" name="inputfacilityState" value="${results.State}">
        <c:if test="${not empty results.facilityStateerror}">
          <div class="invalid-feedback">${results.facilityStateerror}</div>
        </c:if>
      </div>
    </div>
    <!-- Zip -->
    <div class ="row" id = "row4">
      <label for="inputfacilityZip" class="form-label">Zip</label>
      <div class="input-group input-group-lg">
        <input type="text" class="<c:if test="${not empty results.facilityZiperror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Zip" id="inputfacilityZip" name="inputfacilityZip" value="${results.Zip}">
        <c:if test="${not empty results.facilityZiperror}">
          <div class="invalid-feedback">${results.facilityZiperror}</div>
        </c:if>
      </div>
    </div>
    <div class="align-items-center mt-0">
      <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Sign Up</button></div>
      <c:if test="${not empty results.dbStatus}"
      ><p>${results.dbStatus}</p>
      </c:if>
    </div>
  </form>
</div>
<%@include file="/WEB-INF/personal-project/personal_bottom.jsp"%>

