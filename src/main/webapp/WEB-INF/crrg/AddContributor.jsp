<%--************
Create the JSP  For adding to The  Contributor table
 Created By Jonathan Beck 10/9/2024
**********--%>
<%@include file="/WEB-INF/crrg/head.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/addContributor" id = "addContributor" >
        <!-- First_Name -->
        <div class ="row" id = "row0">
            <label for="inputcontributorFirst_Name" class="form-label">First_Name</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.contributorFirst_Nameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="First_Name" id="inputcontributorFirst_Name" name="inputcontributorFirst_Name" value="${fn:escapeXml(results.First_Name)}">
                <c:if test="${not empty results.contributorFirst_Nameerror}">
                    <div class="invalid-feedback">${results.contributorFirst_Nameerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Last_Name -->
        <div class ="row" id = "row1">
            <label for="inputcontributorLast_Name" class="form-label">Last_Name</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.contributorLast_Nameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Last_Name" id="inputcontributorLast_Name" name="inputcontributorLast_Name" value="${fn:escapeXml(results.Last_Name)}">
                <c:if test="${not empty results.contributorLast_Nameerror}">
                    <div class="invalid-feedback">${results.contributorLast_Nameerror}</div>
                </c:if>
            </div>
        </div>
        <!-- email -->
        <div class ="row" id = "row2">
            <label for="inputcontributoremail" class="form-label">email</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.contributoremailerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="email" id="inputcontributoremail" name="inputcontributoremail" value="${fn:escapeXml(results.email)}">
                <c:if test="${not empty results.contributoremailerror}">
                    <div class="invalid-feedback">${results.contributoremailerror}</div>
                </c:if>
            </div>
        </div>
        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Create Contributor  </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/crrg/foot.jsp"%>

