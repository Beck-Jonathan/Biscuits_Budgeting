<%--************
Create the JSP  For Viewing All of The  Language table
 Created By Jonathan Beck3/18/2024
**********--%>
<%@include file="/WEB-INF/personal-project/personal_top.jsp"%>
<div class = "container">
    <div class="row">
        <div class="col-12">
            <h1>All Roller Languages</h1>
            <p>There ${Languages.size() eq 1 ? "is" : "are"}&nbsp;${Languages.size()} Language${Languages.size() ne 1 ? "s" : ""}</p>
            Add Language   <a href="addLanguage">Add</a>
            <c:if test="${Languages.size() > 0}">
                <div class="table-responsive"><table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">Language_ID</th>
                        <th scope="col">Name</th>
                        <th scope="col">is_active</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${Languages}" var="language">
                        <tr>
                            <td>${language.language_ID}</td>
                            <td>${language.name}</td>
                            <td><input type="checkbox" disabled <c:if test="${language.is_active}">checked</c:if>></td>
                            <td><a href = "editlanguage?languageid=${language.language_ID}" > Edit </a></td>
                            <td><a href = "deletelanguage?languageid=${language.language_ID}&mode=<c:choose><c:when test="${language.is_active}">0</c:when>
						<c:otherwise>1</c:otherwise>
						</c:choose>">
                                <c:if test="${!language.is_active}">un</c:if>Delete </a></td>
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
<%@include file="/WEB-INF/personal-project/personal_bottom.jsp"%>

