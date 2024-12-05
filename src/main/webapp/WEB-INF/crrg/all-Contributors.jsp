<%--************
Create the JSP  For Viewing All of The  Contributor table
 Created By Jonathan Beck 10/9/2024
**********--%>
<%@include file="/WEB-INF/crrg/head.jsp"%>
<div class = "container">
    <div class="row">
        <div class="col-12">
            <h1>All Roller Contributors</h1>
            <p>There ${Contributors.size() eq 1 ? "is" : "are"}&nbsp;${Contributors.size()} Contributor${Contributors.size() ne 1 ? "s" : ""}</p>
            Add Contributor   <a href="addContributor">Add</a>
            <c:if test="${Contributors.size() > 0}">
                <div class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">Contributor_ID</th>
                        <th scope="col">First_Name</th>
                        <th scope="col">Last_Name</th>
                        <th scope="col">email</th>
                        <th scope="col">count</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${Contributors}" var="contributor">
                        <tr>
                            <td><a href = "editContributor?contributorid=${contributor.contributor_ID}&mode=view">${fn:escapeXml(contributor.contributor_ID)}</a></td><td>${fn:escapeXml(contributor.first_Name)}</td>
                            <td>${fn:escapeXml(contributor.last_Name)}</td>
                            <td>${fn:escapeXml(contributor.email)}</td>
                            <td>${contributor.album_size}</td>
                            <td><a href = "editContributor?contributorid=${contributor.contributor_ID}&mode=edit" > Edit </a></td>
                            <td><a href = "editContributor?contributorid=${contributor.contributor_ID}&mode=Delete">Delete </a></td>
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
<%@include file="/WEB-INF/crrg/foot.jsp"%>

