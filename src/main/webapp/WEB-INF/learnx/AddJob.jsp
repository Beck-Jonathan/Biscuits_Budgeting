<%--************
Create the JSP  For adding to The  Job_Listing table
 Created By Jonathan Beck 4/29/2024
**********--%>
<%@include file="/WEB-INF/learnx/top.jsp"%>
<div class = "container">
    <form method="post" action="${appURL}/addJob_Listing" id = "addJob_Listing" >
        <!-- department_id -->
        <div class ="row" id = "row0">
            <label for="inputjob_listingdepartment_id" class="form-label">department_id</label>
            <div class="input-group input-group-lg">
                <select  class="<c:if test="${not empty results.job_listingdepartment_iderror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="department_id" id="inputjob_listingdepartment_id" name="inputjob_listingdepartment_id" value="${fn:escapeXml(results.department_id)}">
                    <c:forEach items="${departments}" var="department">
                        <option value="${department.department_id}">${department.name}   </option>
                    </c:forEach>
                </select>
                <c:if test="${not empty results.job_listingdepartment_iderror}">
                    <div class="invalid-feedback">${results.job_listingdepartment_iderror}</div>
                </c:if>
            </div>
        </div>
        <!-- featured -->
        <div class ="row" id = "row1">
            <label for="inputjob_listingfeatured" class="form-label">featured</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.job_listingfeaturederror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="featured" id="inputjob_listingfeatured" name="inputjob_listingfeatured" value="${fn:escapeXml(results.featured)}">
                <c:if test="${not empty results.job_listingfeaturederror}">
                    <div class="invalid-feedback">${results.job_listingfeaturederror}</div>
                </c:if>
            </div>
        </div>
        <!-- position -->
        <div class ="row" id = "row2">
            <label for="inputjob_listingposition" class="form-label">position</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.job_listingpositionerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="position" id="inputjob_listingposition" name="inputjob_listingposition" value="${fn:escapeXml(results.position)}">
                <c:if test="${not empty results.job_listingpositionerror}">
                    <div class="invalid-feedback">${results.job_listingpositionerror}</div>
                </c:if>
            </div>
        </div>
        <!-- posted_at -->
        <div class ="row" id = "row3">
            <label for="inputjob_listingposted_at" class="form-label">posted_at</label>
            <div class="input-group input-group-lg">
                <input type="date" class="<c:if test="${not empty results.job_listingposted_aterror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="posted_at" id="inputjob_listingposted_at" name="inputjob_listingposted_at" value="${fn:escapeXml(results.posted_at)}">
                <c:if test="${not empty results.job_listingposted_aterror}">
                    <div class="invalid-feedback">${results.job_listingposted_aterror}</div>
                </c:if>
            </div>
        </div>
        <!-- contract -->
        <div class ="row" id = "row4">
            <label for="inputjob_listingcontract" class="form-label">contract</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.job_listingcontracterror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="contract" id="inputjob_listingcontract" name="inputjob_listingcontract" value="${fn:escapeXml(results.contract)}">
                <c:if test="${not empty results.job_listingcontracterror}">
                    <div class="invalid-feedback">${results.job_listingcontracterror}</div>
                </c:if>
            </div>
        </div>
        <!-- location -->
        <div class ="row" id = "row5">
            <label for="inputjob_listinglocation" class="form-label">location</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.job_listinglocationerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="location" id="inputjob_listinglocation" name="inputjob_listinglocation" value="${fn:escapeXml(results.location)}">
                <c:if test="${not empty results.job_listinglocationerror}">
                    <div class="invalid-feedback">${results.job_listinglocationerror}</div>
                </c:if>
            </div>
        </div>
        <!-- job_description -->
        <div class ="row" id = "row6">
            <label for="inputjob_listingjob_description" class="form-label">job_description</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.job_listingjob_descriptionerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="job_description" id="inputjob_listingjob_description" name="inputjob_listingjob_description" value="${fn:escapeXml(results.job_description)}">
                <c:if test="${not empty results.job_listingjob_descriptionerror}">
                    <div class="invalid-feedback">${results.job_listingjob_descriptionerror}</div>
                </c:if>
            </div>
        </div>
        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Create Job_Listing  </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/learnx/bottom.jsp"%>

