<%@include file="/WEB-INF/learnx/top.jsp" %>
<main>

    <div class="container">
        <div class="row">
            <%@include file="/WEB-INF/learnx/dashboard-sidebar.jsp" %>

            <div class="col-xl-9">
                <div class="card">
                    <div class="card-header bg-dark">
                        <h3 class="card-header-title text-light">${pageTitle}</h3>
                    </div>
                    <div class="card-body">

                        <jsp:include page="../Shared/flashMessage.jsp"></jsp:include>


                        <form action="${appURL}/edit-user?id=${user.ID}" method="post" class="row">
                            <%-- First Name--%>
                            <div class="col-md-6">
                                <label class="form-label" for="firstNameInput">First Name</label>
                                <input class="form-control" type="text" id="firstNameInput" name="firstNameInput"
                                       value="${fn:escapeXml(user.first_name)}">
                            </div>
                            <%-- Last Name--%>
                            <div class="col-md-6">
                                <label class="form-label" for="lastNameInput">Last Name</label>
                                <input class="form-control" type="text" id="lastNameInput" name="lastNameInput"
                                       value="${fn:escapeXml(user.last_name)}">
                            </div>
                                <%-- Email--%>
                                <div class="col-md-6">
                                    <label class="form-label" for="emailInput">Last Name</label>
                                    <input class="form-control" type="text" id="emailInput" name="emailInput"
                                           value="${fn:escapeXml(user.email)}">
                                </div>
                                <%-- Phone--%>
                                <div class="col-md-6">
                                    <label class="form-label" for="phoneInput">Phone</label>
                                    <input class="form-control" type="text" id="phoneInput" name="phoneInput"
                                           value="${fn:escapeXml(user.phone)}">
                                </div>
                                <!-- language -->

                            <div class="col-md-6">
                                <label class="form-label" for="languageInput">Language</label>
                                <select class="form-select <c:if test="${not empty results.languageError}">is-invalid</c:if>" name="languageInput" id="languageInput">
                                    <option value="en" <c:if test="${user.language eq 'en'}">selected</c:if>>English</option>
                                    <option value="fr" <c:if test="${user.language eq 'fr'}">selected</c:if>>French</option>
                                    <option value="ar" <c:if test="${user.language eq 'ar'}">selected</c:if>>Arabic</option>
                                </select>
                                <c:if test="${not empty results.languageError}">
                                    <div class="invalid-feedback">${results.languageError}</div>
                                </c:if>
                            </div>

                                <!-- status -->

                                <div class="col-md-6">
                                    <label class="form-label" for="statusInput">Status</label>
                                    <select class="form-select <c:if test="${not empty results.languageError}">is-invalid</c:if>" name="statusInput" id="statusInput">
                                        <option value="inactive" <c:if test="${user.status eq 'inactive'}">selected</c:if>>Inactive</option>
                                        <option value="active" <c:if test="${user.status eq 'active'}">selected</c:if>>Active</option>
                                        <option value="locked" <c:if test="${user.status eq 'locked'}">selected</c:if>>Locked</option>
                                    </select>
                                    <c:if test="${not empty results.statusError}">
                                        <div class="invalid-feedback">${results.statusError}</div>
                                    </c:if>
                                </div>

                                <!-- priv -->

                                <div class="col-md-6">
                                    <label class="form-label" for="privInput">privileges</label>
                                    <select class="form-select <c:if test="${not empty results.languageError}">is-invalid</c:if>" name="privInput" id="privInput">
                                        <option value="student" <c:if test="${user.privileges eq 'student'}">selected</c:if>>Student</option>
                                        <option value="teacher" <c:if test="${user.privileges eq 'teach'}">selected</c:if>>Teacher</option>
                                        <option value="admin" <c:if test="${user.privileges eq 'admin'}">selected</c:if>>Administrator</option>
                                    </select>
                                    <c:if test="${not empty results.privError}">
                                        <div class="invalid-feedback">${results.privError}</div>
                                    </c:if>
                                </div>

                            <!-- Save button -->
                            <div class="d-sm-flex justify-content-end">
                                <button type="submit" class="btn btn-primary mb-0">Save changes</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>
</main>
<%@include file="/WEB-INF/learnx/bottom.jsp" %>