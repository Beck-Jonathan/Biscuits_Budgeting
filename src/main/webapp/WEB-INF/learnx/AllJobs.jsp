<%--************
Create the JSP  For Viewing All of The  Job_Listing table
 Created By Jonathan Beck 4/29/2024
**********--%>
<%@include file="/WEB-INF/learnx/top.jsp"%>
<div class = "container">
    <div class="row">
        <div class="col-12">
            <h1>All Roller Job_Listings</h1>
            <p>There ${Job_Listings.size() eq 1 ? "is" : "are"}&nbsp;${Job_Listings.size()} Job_Listing${Job_Listings.size() ne 1 ? "s" : ""}</p>
            Add Job_Listing   <a href="addJob_Listing">Add</a>
            <c:if test="${Job_Listings.size() > 0}">

                <c:forEach items="${Job_Listings}" var="job_listing">
                <div class="container">
                    <div class="row border border-dark my-4 p-2">
                        <div class="col-md-6 d-flex flex-column mt-2">
                            <div class = "mt-2">
                                <strong> ${fn:escapeXml(job_listing.department.department_name)}</strong>
                                <span class="badge rounded-pill bg-success">Badge </span>
                                <span class="badge rounded-pill bg-warning text-light">Badge </span>
                            </div>
                            <h4 class ="my-2"><a href = "apply-learnx?job_id=${job_listing.job_id}" >${fn:escapeXml(job_listing.position)}</a> </h4>
                            <div class = "mb-2">
                                <span class="me-2"> ${fn:escapeXml(job_listing.posted_at)} </span>
                                <span class="me-2">${fn:escapeXml(job_listing.contract)}</span>
                                <span>${fn:escapeXml(job_listing.location)}</span>
                            </div>
                        </div> <!--end col 1-->
                        <div class="col-md-6 d-flex justify-content-md-end align-items-center">
                            <div >
                                <span class ="badge rounded-pill bg-primary"> Tag </span>
                                <span class ="ms-2 badge rounded-pill bg-secondary"> Tag </span>
                                <span class ="ms-2 badge rounded-pill bg-dark"> Tag </span>

                            </div>
                        </div> <!-- end col 2 -->
                    </div>
                </div>
                </c:forEach>
            </c:if>
        </div>
    </div>
</div>
</main>
<%@include file="/WEB-INF/learnx/bottom.jsp"%>

