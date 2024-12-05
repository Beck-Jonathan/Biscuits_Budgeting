<%@include file="/WEB-INF/learnx/top.jsp"%>
<main>
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <h1>All LearnX Users</h1>

                <%--                Add flash message tags--%>
                <jsp:include page="../Shared/flashMessage.jsp"></jsp:include>
                <p><fmt:message key="allUsers.userCount">
                    <fmt:param value="${fn:length(users) == 1 ? 'is' : 'are'}" />
                    <fmt:param value="${fn:length(users)}" />
                    <fmt:param value="${fn:length(users) != 1 ? 's' : ''}" />
                </fmt:message></p>
                <c:if test="${users.size() > 0}">
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th scope="col">Edit</th>
                                <th scope="col">Delete</th>
                                <th scope="col">First name</th>
                                <th scope="col">Last name</th>
                                <th scope="col">Email</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${users}" var="user">

                                    <td><a href="${appURL}/edit-user?id=${user.ID}" class="btn btn-dark">Edit</a> </td>
                                    <td><button type="button" class="btn btn-primary"  data-bs-whatever="${user.ID}" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                        Delete
                                </button></td>


                                    <td>${user.first_name}</td>
                                    <td>${user.last_name}</td>
                                    <td>${user.email}</td>
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

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Really Delete this user? ${victim}
                <form method="post" action="${appURL}/delete-user" id = "delete-user" >
                <input type="hidden" name="userid" id="userid"  value="${victim}"/>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                       <button class="btn btn-orange mb-0" type="submit">delete user </button>
                </form>
            </div>
            </div>
            <div class="modal-footer">



            </div>
        </div>
    </div>
</div>


<%@include file="/WEB-INF/learnx/bottom.jsp"%>