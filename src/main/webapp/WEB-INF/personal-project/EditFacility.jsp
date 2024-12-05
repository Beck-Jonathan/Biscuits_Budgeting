<%--************
Create the JSP For Viuw/Edit from the Facility table
 Created By Jonathan Beck 5/4/2024
**********--%>
<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/editfacility" id = "editFacility" >
        <!-- Facility_ID -->
        <div class ="row" id = "row0">
            <h2>Facility_ID  :
                ${fn:escapeXml(facility.facility_ID)}</h2>
        </div>
        <!-- Name -->
        <div class ="row" id = "row1">
            <label for="inputfacilityName" class="form-label">Name</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.facilityNameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Name" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputfacilityName" name="inputfacilityName" value="${fn:escapeXml(facility.name)}">
                <c:if test="${not empty results.facilityNameerror}">
                    <div class="invalid-feedback">${results.facilityNameerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Addresss -->
        <div class ="row" id = "row2">
            <label for="inputfacilityAddresss" class="form-label">Addresss</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.facilityAddressserror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Addresss" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputfacilityAddresss" name="inputfacilityAddresss" value="${fn:escapeXml(facility.addresss)}">
                <c:if test="${not empty results.facilityAddressserror}">
                    <div class="invalid-feedback">${results.facilityAddressserror}</div>
                </c:if>
            </div>
        </div>
        <!-- City -->
        <div class ="row" id = "row3">
            <label for="inputfacilityCity" class="form-label">City</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.facilityCityerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="City" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputfacilityCity" name="inputfacilityCity" value="${fn:escapeXml(facility.city)}">
                <c:if test="${not empty results.facilityCityerror}">
                    <div class="invalid-feedback">${results.facilityCityerror}</div>
                </c:if>
            </div>
        </div>
        <!-- State -->
        <div class ="row" id = "row4">
            <label for="inputfacilityState" class="form-label">State</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.facilityStateerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="State" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputfacilityState" name="inputfacilityState" value="${fn:escapeXml(facility.state)}">
                <c:if test="${not empty results.facilityStateerror}">
                    <div class="invalid-feedback">${results.facilityStateerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Zip -->
        <div class ="row" id = "row5">
            <label for="inputfacilityZip" class="form-label">Zip</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.facilityZiperror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Zip" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputfacilityZip" name="inputfacilityZip" value="${fn:escapeXml(facility.zip)}">
                <c:if test="${not empty results.facilityZiperror}">
                    <div class="invalid-feedback">${results.facilityZiperror}</div>
                </c:if>
            </div>
        </div>
        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Edit Facility </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/personal-project/personal_bottom.jsp"%>

