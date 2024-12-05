<%--************
Create the JSP  For Viewing All of The  Job_Application table
 Created By Jonathan Beck 5/2/2024
**********--%>
<%@include file="/WEB-INF/learnx/top.jsp"%>
<div class = "container">
    <div class="row">
        <div class="col-12">
            <h1>All Learnx Job Applications</h1>
            <p>There ${Job_Applications.size() eq 1 ? "is" : "are"}&nbsp;${Job_Applications.size()} Job Application${Job_Applications.size() ne 1 ? "s" : ""}</p>

            <c:if test="${Job_Applications.size() > 0}">
                <div class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">applicationId</th>
                        <th scope="col">jobId</th>
                        <th scope="col">Applicant Name</th>

                        <th scope="col">email</th>
                        <th scope="col">desiredSalary</th>
                        <th scope="col">earliestStartDate</th>
                        <th scope="col">dateSubmitted</th>
                        <th scope="col">status</th>
                        <th scope="col">Edit</th>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${Job_Applications}" var="job_application">
                        <tr>
                            <td><a href = "editjob_application?job_applicationid=${job_application.applicationId}&mode=view">${fn:escapeXml(job_application.applicationId)}</a></td><td>${fn:escapeXml(job_application.applicationId)}</td>
                            <td>${fn:escapeXml(job_application.firstName)} &nbsp;
                            ${fn:escapeXml(job_application.lastName)}</td>
                            <td>${fn:escapeXml(job_application.email)}</td>
                            <td><fmt:setLocale value = "en_US"/>  <fmt:formatNumber value = "${job_application.desiredSalary}" type = "currency"/>   </td>
                            <td> <fmt:formatDate type = "date"  value = "${job_application.earliestStartDate}" />  </td>
                            <td>${fn:escapeXml(job_application.dateSubmitted)}</td>
                            <td>${fn:escapeXml(job_application.status)}</td>
                            <td><a href = "editjob_application?job_applicationid=${job_application.applicationId}&mode=edit" > Edit </a></td>

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

