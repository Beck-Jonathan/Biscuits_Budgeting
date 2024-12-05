<%--************
Create the JSP  For adding to The  Event table
 Created By Jonathan Beck3/18/2024
**********--%>
<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/addEvent" id = "addEvent" >
        <!-- Facility_ID -->
        <div class ="row" id = "row0">
            <label for="inputeventFacility_ID" class="form-label">Facility_ID</label>
            <div class="input-group input-group-lg">
                <select  class="<c:if test="${not empty results.eventFacility_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Facility_ID" id="inputeventFacility_ID" name="inputeventFacility_ID" value="${results.Facility_ID}">
                    <c:forEach items="${Facilitys}" var="Facility">
                        <option value="${Facility.facility_ID}">${Facility.name}   </option>
                    </c:forEach>
                </select>
                <c:if test="${not empty results.eventFacility_IDerror}">
                    <div class="invalid-feedback">${results.eventFacility_IDerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Date -->
        <div class ="row" id = "row1">
            <label for="inputeventDate" class="form-label">Date</label>
            <div class="input-group input-group-lg">
                <input type="date" class="<c:if test="${not empty results.eventDateerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Date" id="inputeventDate" name="inputeventDate" value="${results.Date}">
                <c:if test="${not empty results.eventDateerror}">
                    <div class="invalid-feedback">${results.eventDateerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Type_ID -->
        <div class ="row" id = "row2">
            <label for="inputeventType_ID" class="form-label">Type_ID</label>
            <div class="input-group input-group-lg">
                <select  class="<c:if test="${not empty results.eventType_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Type_ID" id="inputeventType_ID" name="inputeventType_ID" value="${results.Type_ID}">
                    <c:forEach items="${Types}" var="Type">
                        <option value="${Type.type_ID}">${Type.name}   </option>
                    </c:forEach>
                </select>
                <c:if test="${not empty results.eventType_IDerror}">
                    <div class="invalid-feedback">${results.eventType_IDerror}</div>
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

