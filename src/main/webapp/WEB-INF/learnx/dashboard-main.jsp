<div class="col-xl-9">
    <div class="card">
        <div class="card-header bg-dark">
            <h3 class="card-header-title text-light">Edit Profile</h3>
        </div>
        <div class="card-body">
            <jsp:include page="../Shared/flashMessage.jsp"></jsp:include>
            <form action="${appURL}/edit-profile" method="post" class="row">
                <%-- First Name--%>
                <div class="col-md-6">
                    <label class="form-label" for="firstNameInput">First Name</label>
                    <input class="form-control" type="text" id="firstNameInput" name="firstNameInput"
                           value="${fn:escapeXml(activeUser.first_name)}">
                </div>
                <%-- Last Name--%>
                <div class="col-md-6">
                    <label class="form-label" for="lastNameInput">Last Name</label>
                    <input class="form-control" type="text" id="lastNameInput" name="lastNameInput"
                           value="${fn:escapeXml(activeUser.last_name)}">
                </div>

                <!-- Email id -->
                <div class="col-md-6">
                    <label class="form-label" for="emailInput">Email</label>
                    <input readonly class="form-control" type="text" id="emailInput" name="emailInput" value="${fn:escapeXml(activeUser.email)}">
                </div>

                <!-- Phone number -->
                <div class="col-md-6">
                    <label class="form-label" for="phoneInput">Phone number</label>
                    <input  type="text" class="form-control" id="phoneInput" name="phoneInput" value="${fn:escapeXml(activeUser.phone)}">
                </div>
                    <!-- Language -->
                <div class="col-md-6">
                    <label class="form-label" for="languageInput">Language</label>
                    <select class="form-select <c:if test="${not empty results.languageError}"> is-invalid </c:if>"  name="languageInput" id="languageInput">
                        <option value="en" <c:if test="${activeUser.language eq 'en'}">selected</c:if>>English</option>
                        <option value="sk" <c:if test="${activeUser.language eq 'sk'}">selected</c:if>>Slovak</option>
                        <option value="ar" <c:if test="${activeUser.language eq 'ar'}">selected</c:if>>Arabic</option>
                        <option value="fr" <c:if test="${activeUser.language eq 'fr'}">selected</c:if>>French</option>
                    </select>
                    <c:if test="${not empty results.languageError}"> ${results.languageError} </c:if>

                </div>


                <!-- Save button -->
                <div class="d-sm-flex justify-content-end">
                    <button type="submit" class="btn btn-primary mb-0">Save changes</button>
                </div>
            </form>
        </div>
    </div>
</div>
