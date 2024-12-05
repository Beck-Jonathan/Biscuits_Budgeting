
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script> >


<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.13.2/jquery-ui.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/nprogress@0.2.0/nprogress.js"></script>

<c:if test="${pageTitle eq 'Image Gallery of CRRD. Photos: Mark Young'}">
    <script src="${appURL}/js/crrg/crrg.js"></script>
</c:if>

<c:if test="${pageTitle ne 'Image Gallery of CRRD. Photos: Mark Young'}">
    <script src="${appURL}/js/crrg/crrg_admin.js"></script>
</c:if>

<c:if test="${pageTitle eq 'All Albums'}">
    <script src="${appURL}/js/crrg/activate.js"></script>
</c:if>

<c:if test="${pageTitle eq 'All Pictures'}">
    <script src="${appURL}/js/crrg/activate.js"></script>
    <script src="${appURL}/js/crrg/approve.js"></script>
</c:if>

<c:if test="${pageTitle eq 'Edit a Album' || pageTitle eq 'Add Album' }">
    <script src="${appURL}/js/crrg/add_edit_album.js"></script>
</c:if>

<c:if test="${pageTitle eq 'Edit a Contributor' || pageTitle eq 'Add Contributor' }">
    <script src="${appURL}/js/crrg/add_edit_contributor.js"></script>
</c:if>

<c:if test="${pageTitle eq 'Edit a Picture' || pageTitle eq 'Add Picture' }">
    <script src="${appURL}/js/crrg/add_edit_picture.js"></script>
</c:if>
<c:if test="${pageTitle eq 'All Users' }">
    <script src="${appURL}/js/crrg/All_users.js"></script>
</c:if>


</body>
</html>

