<%--************
Create the JSP For Viuw/Edit from the User_Bio table
 Created By Jonathan Beck 5/5/2024
**********--%>

<div class = "container">
    <form method="post" action="${appURL}/editUser_Bio" id = "editUser_Bio" >
        <!-- User_ID -->
        <div class ="row" id = "row0">
            <h3>Your Profile:</h3>
        </div>
        <!-- Postion -->
        <div class ="row" id = "row1">
            <label for="inputuser_bioposition" class="form-label">Postion</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.user_bioPostionerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Position" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputuser_bioposition" name="inputuser_bioposition" value="${fn:escapeXml(user_bio.position)}">
                <c:if test="${not empty results.user_bioPostionerror}">
                    <div class="invalid-feedback">${results.user_bioPostionerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Bio -->
        <div class ="row" id = "row2">
            <label for="inputuser_bioBio" class="form-label">Bio</label>
            <div class="input-group input-group-lg">
                <input type="text" class="<c:if test="${not empty results.user_bioBioerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Bio" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputuser_bioBio" name="inputuser_bioBio" value="${fn:escapeXml(user_bio.bio)}">
                <c:if test="${not empty results.user_bioBioerror}">
                    <div class="invalid-feedback">${results.user_bioBioerror}</div>
                </c:if>
            </div>
        </div>
        <!-- Began_Skating -->
        <div class ="row" id = "row3">
            <label for="inputuser_bioBegan_Skating" class="form-label">Began_Skating</label>
            <div class="input-group input-group-lg">
                <input type="date" class="<c:if test="${not empty results.user_bioBegan_Skatingerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1" placeholder="Began_Skating" <c:if test="${mode eq 'view'}"> disabled </c:if>  id="inputuser_bioBegan_Skating" name="inputuser_bioBegan_Skating" value="${fn:escapeXml(user_bio.began_Skating)}">
                <c:if test="${not empty results.user_bioBegan_Skatingerror}">
                    <div class="invalid-feedback">${results.user_bioBegan_Skatingerror}</div>
                </c:if>
            </div>
        </div>
        <div class="align-items-center mt-0">
            <div class="d-grid"><button class="btn btn-orange mb-0" type="submit">Edit User_Bio </button></div>
            <c:if test="${not empty results.dbStatus}"
            ><p>${results.dbStatus}</p>
            </c:if>
        </div>
    </form>

    <!-- Delete Account trigger modal -->
    <a href="deleteaccount"><button type="button" class="btn btn-danger" >Delete Account</button>  </a>

    </button>




</div>


