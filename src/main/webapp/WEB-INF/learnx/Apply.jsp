<%--************
Create the JSP  For adding to The  Job_Application table
 Created By Jonathan Beck 5/2/2024
**********--%>
<%@include file="/WEB-INF/learnx/top.jsp"%>
<div class = "container">
    <%@include file="/WEB-INF/Shared/flashMessage.jsp"%>

    <c:if test="${not empty results.jobNotFound}"> ${results.jobNotFound}</c:if>
    <c:if test="${empty results.jobNotFound}">
    <form method="post" action="${appURL}/apply-learnx" id = "apply" >
        <h4>${job_listing.position}</h4>
        <!-- jobId -->
        <div class ="row" id = "row0">

            <div class="input-group input-group-lg">
                <input type="hidden" class="form-control border-0 bg-light rounded-end ps-1 hidden" name="inputjob_applicationjobId" value="${fn:escapeXml(job_listing.job_id)}">

            </div>
        </div>
        <!-- firstName -->
        <div class ="row" id = "row1">
            <label for="inputjob_applicationfirstName" class="form-label">First Name</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.job_applicationfirstNameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="First Name" id="inputjob_applicationfirstName" name="inputjob_applicationfirstName" value="${fn:escapeXml(results.firstName)}">
                <c:if test="${not empty results.job_applicationfirstNameerror}">
                    <div class="invalid-feedback">${results.job_applicationfirstNameerror}</div>
                </c:if>
            </div>
        </div>
        <!-- lastName -->
        <div class ="row" id = "row2">
            <label for="inputjob_applicationlastName" class="form-label">Last Name</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.job_applicationlastNameerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Last Name" id="inputjob_applicationlastName" name="inputjob_applicationlastName" value="${fn:escapeXml(results.lastName)}">
                <c:if test="${not empty results.job_applicationlastNameerror}">
                    <div class="invalid-feedback">${results.job_applicationlastNameerror}</div>
                </c:if>
            </div>
        </div>
        <!-- email -->
        <div class ="row" id = "row3">
            <label for="inputjob_applicationemail" class="form-label">email</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.job_applicationemailerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="email" id="inputjob_applicationemail" name="inputjob_applicationemail" value="${fn:escapeXml(results.email)}">
                <c:if test="${not empty results.job_applicationemailerror}">
                    <div class="invalid-feedback">${results.job_applicationemailerror}</div>
                </c:if>
            </div>
        </div>
        <!-- desiredSalary -->
        <div class ="row" id = "row4">
            <label for="inputjob_applicationdesiredSalary" class="form-label">desiredSalary</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.job_applicationdesiredSalaryerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Desired Salary" id="inputjob_applicationdesiredSalary" name="inputjob_applicationdesiredSalary" value="${fn:escapeXml(results.desiredSalary)}">
                <c:if test="${not empty results.job_applicationdesiredSalaryerror}">
                    <div class="invalid-feedback">${results.job_applicationdesiredSalaryerror}</div>
                </c:if>
            </div>
        </div>
        <!-- earliestStartDate -->
        <div class ="row" id = "row5">
            <label for="inputjob_applicationearliestStartDate" class="form-label">earliestStartDate</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.job_applicationearliestStartDateerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="MM-DD-YYYY" id="inputjob_applicationearliestStartDate" name="inputjob_applicationearliestStartDate" value="${fn:escapeXml(results.earliestStartDate)}">
                <c:if test="${not empty results.job_applicationearliestStartDateerror}">
                    <div class="invalid-feedback">${results.job_applicationearliestStartDateerror}</div>
                </c:if>
            </div>
        </div>


        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Create Job_Application  </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
    </c:if>
</div>
<%@include file="/WEB-INF/learnx/bottom.jsp"%>

