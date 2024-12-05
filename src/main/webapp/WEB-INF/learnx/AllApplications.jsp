<%--************
Create the JSP  For Viewing All of The  Job_Application table
 Created By Jonathan Beck 4/29/2024
**********--%>
<%@include file="/WEB-INF/learnx/top.jsp"%>
<div class = "container">
    <div class="row">
        <div class="col-12">
            <h1>All Roller Job_Applications</h1>
            <p>There ${Job_Applications.size() eq 1 ? "is" : "are"}&nbsp;${Job_Applications.size()} Job_Application${Job_Applications.size() ne 1 ? "s" : ""}</p>
            Add Job_Application   <a href="addJob_Application">Add</a>
            <c:if test="${Job_Applications.size() > 0}">
                <div class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">Job_Applicaiton_ID</th>
                        <th scope="col">job_id</th>
                        <th scope="col">user_id</th>
                        <th scope="col">Status</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${Job_Applications}" var="job_application">
                        <tr>
                            <td><a href = "editjob_application?job_applicationid=${job_application.job_application_ID}&mode=view">${fn:escapeXml(job_application.job_application_ID)}</a></td><td>${fn:escapeXml(job_application.job_id)}</td>
                            <td>${fn:escapeXml(job_application.user_id)}</td>
                            <td>${fn:escapeXml(job_application.status)}</td>
                            <td><a href = "editjob_application?job_applicationid=${job_application.job_application_ID}&mode=edit" > Edit </a></td>
                            <td><a href = "deletejob_application?job_applicationid=${job_application.job_application_ID}&mode=<c:choose><c:when test="${job_application.is_active}">0</c:when>
						<c:otherwise>1</c:otherwise>
						</c:choose>">
                                <c:if test="${!job_application.is_active}">un</c:if>Delete </a></td>
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
<%@include file="/WEB-INF/learnx/bottom.jsp"%>

