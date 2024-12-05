<%--************
Create the JSP For Viuw/Edit from the Contributor table
 Created By Jonathan Beck 10/15/2024
**********--%>
<%@include file="/WEB-INF/crrg/head.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/editContributor" id = "editContributor" >
        <!-- Contributor_ID -->
        <div class ="row" id = "row0">
            <h2>Contributor_ID  :
                ${fn:escapeXml(contributor.contributor_ID)}</h2>
        </div>
        <div class="row">
            <div class="col-2">
                Associated Pictures (${contributor.album_size})
            </div>
            <div class="col-10">
                <a href="all-Pictures?mode=contributor&contributor=${contributor.contributor_ID}">View</a>
            </div>
        </div>
        <!-- First_Name -->
        <div class ="row" id = "row1">
            <label for="inputcontributorFirst_Name" class="form-label">First_Name</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.contributorFirst_Nameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="First_Name" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputcontributorFirst_Name" name="inputcontributorFirst_Name" value="${fn:escapeXml(contributor.first_Name)}">
                <c:if test="${not empty results.contributorFirst_Nameerror}">
                    <div class="invalid-feedback">${results.contributorFirst_Nameerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Last_Name -->
        <div class ="row" id = "row2">
            <label for="inputcontributorLast_Name" class="form-label">Last_Name</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.contributorLast_Nameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Last_Name" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputcontributorLast_Name" name="inputcontributorLast_Name" value="${fn:escapeXml(contributor.last_Name)}">
                <c:if test="${not empty results.contributorLast_Nameerror}">
                    <div class="invalid-feedback">${results.contributorLast_Nameerror}</div>
                </c:if>
            </div>
        </div>
        <!-- email -->
        <div class ="row" id = "row3">
            <label for="inputcontributoremail" class="form-label">email</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.contributoremailerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="email" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputcontributoremail" name="inputcontributoremail" value="${fn:escapeXml(contributor.email)}">
                <c:if test="${not empty results.contributoremailerror}">
                    <div class="invalid-feedback">${results.contributoremailerror}</div>
                </c:if>
            </div>
        </div>
<c:if test="${mode ne 'view'}">
        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Edit Contributor </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
</c:if>
    </form>
</div>
<%@include file="/WEB-INF/crrg/foot.jsp"%>

